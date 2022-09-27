package vn.sapo.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecificationUtils {
    public static class Criteria{
        private Operator operator;
        private List<Operand> operands;

        public Criteria(){

        }
        public Criteria(Class<?> clazz, Operator operator, Operand... operands) throws Exception {
            if(operator == null)
                throw new Exception("operator must not be null");
            if(operands == null || operands.length == 0)
                throw new Exception("operator must not be blank");

            this.operator = operator;
            this.operands = Arrays.asList(operands);

            if(RelationalOperators.contains(this.operator)){
                Operand fieldOperand = getFieldOperand();
                if(fieldOperand == null)
                    throw new Exception(
                        String.format(
                            "Operator %s need a FieldOperand",
                            this.operator.name()
                        )
                    );
                Operand valueOperand = getValueOperand();
                if(valueOperand == null)
                    throw new Exception(
                        String.format(
                            "Operator %s need a ValueOperand",
                            this.operator.name()
                        )
                    );
                if(this.operands.size() > 2)
                    throw new Exception("Too many operands");
            }

            if(this.operator.equals(Operator.AND) || this.operator.equals(Operator.OR)){
                for(Operand operand : this.operands)
                    if(!(operand instanceof CriteriaOperand))
                        throw new Exception(
                            String.format(
                                "Operator %s need an array/a list of CriteriaOperand",
                                this.operator.name()
                            )
                        );
            }
            if(this.operator.equals(Operator.ANY) || this.operator.equals(Operator.ALL)){
                FieldOperand fieldOperand = getFieldOperand();
                if(fieldOperand == null)
                    throw new Exception(
                        String.format(
                            "Operator %s need a FieldOperand",
                            this.operator.name()
                        )
                    );
                if(this.operands.size() > 2)
                    throw new Exception("Too many operands");

                if(clazz != null){
                    Field field = ObjectUtils.getField(fieldOperand.getFieldName(), clazz);
                    Class<?> fieldType = field.getType();
                    if(!fieldType.getName().equals(List.class.getName()))
                        throw new Exception(String.format(
                            "Operator %s need a FieldOperand with Array/List type",
                            this.operator.name()
                        ));
                }

                Operand criteriaOperand = this.operands.get(1);
                if(!(criteriaOperand instanceof CriteriaOperand))
                    throw new Exception(String.format(
                        "Operator %s need a CriteriaOperand",
                        this.operator.name()
                    ));
            }
        }

        private FieldOperand getFieldOperand() throws Exception {
            Operand fieldOperand = this.operands.stream()
                .filter(o -> o instanceof FieldOperand)
                .findAny()
                .orElse(null);
            return (FieldOperand) fieldOperand;
        }
        private ValueOperand getValueOperand() throws Exception {
            Operand valueOperand = this.operands.stream()
                .filter(o -> o instanceof ValueOperand)
                .findAny()
                .orElse(null);
            return (ValueOperand) valueOperand;
        }

        public boolean match(Object obj) throws Exception {
            if(RelationalOperators.contains(this.operator)){
                FieldOperand fieldOperand = getFieldOperand();
                String fieldName = fieldOperand.getFieldName();
                ValueOperand valueOperand = getValueOperand();
                Object value = valueOperand.getValue();

                Field field = ObjectUtils.getField(fieldName, obj.getClass());
                if(field == null)
                    return false;
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object fieldValue = field.get(obj);

                switch (this.operator.name()){
                    case "EQ":
                        return ObjectUtils.equalsInString(fieldValue, value);
                    case "NEQ":
                        return !ObjectUtils.equalsInString(fieldValue, value);
                    case "GT":
                        return ObjectUtils.compare(fieldValue, value) > 0;
                    case "GTE":
                        return ObjectUtils.compare(fieldValue, value) >= 0;
                    case "LT":
                        return ObjectUtils.compare(fieldValue, value) < 0;
                    case "LTE":
                        return ObjectUtils.compare(fieldValue, value) <= 0;
                    case "IN":
                        return ObjectUtils.in(fieldValue, value);
                    case "CONTAIN":
                        return ObjectUtils.contains(fieldType, fieldValue, value);
                    default:
                        return false;
                }
            }
            if(this.operator.equals(Operator.OR)){
                boolean matched = false;
                for(Operand operand : this.operands){
                    if(!(operand instanceof Criteria))
                        throw new Exception("toán tử or phải đi với list toán hàng kiểu Criteria");
                    matched = matched || ((Criteria) operand).match(obj);
                }
                return matched;
            }

            if(this.operator.equals(Operator.AND)){
                boolean matched = true;
                for(Operand operand : this.operands){
                    if(!(operand instanceof Criteria))
                        throw new Exception("toán tử or phải đi với list toán hàng kiểu Criteria");
                    matched = matched && ((CriteriaOperand) operand).match(obj);
                }
                return matched;
            }

            if(this.operator.equals(Operator.ALL)){
                FieldOperand fieldOperand = getFieldOperand();
                Field field = ObjectUtils.getField(fieldOperand.getFieldName(), obj.getClass());

                List fieldValueElements = (List) field.get(obj);
                boolean matched = true;
                for(Object element : fieldValueElements){
                    CriteriaOperand criteriaOperand = (CriteriaOperand) this.operands.stream()
                        .filter(o -> o.getClass().equals(CriteriaOperand.class))
                        .findFirst().get();
                    matched = matched && criteriaOperand.match(element);
                }
                return matched;
            }

            if(this.operator.equals(Operator.ANY)){
                FieldOperand fieldOperand = getFieldOperand();
                Field field = ObjectUtils.getField(fieldOperand.getFieldName(), obj.getClass());

                List fieldValueElements = (List) field.get(obj);
                boolean matched = false;
                for(Object element : fieldValueElements){
                    matched = matched || ((CriteriaOperand) this.operands.get(1)).match(element);
                }
                return matched;
            }

            return false;
        }

        public Operator getOperator() {
            return operator;
        }

        public void setOperator(Operator operator) {
            this.operator = operator;
        }

        public List<Operand> getOperands() {
            return operands;
        }

        public void setOperands(List<Operand> operands) {
            this.operands = operands;
        }
    }


    public static Criteria parseCriteriaNode(JsonNode node) throws Exception {
        ObjectMapper jsonConverter = new ObjectMapper();

        Criteria criteria = new Criteria();
        Operator operator = Operator.valueOf(node.get("operator").asText());

        ArrayNode operandNodes = (ArrayNode) node.get("operands");
        List<Operand> operands = new ArrayList<>();

        for(JsonNode operandNode : operandNodes){
            Operand operand;
            if(operandNode.get("fieldName") != null){
                operand = jsonConverter.convertValue(operandNode, FieldOperand.class);
            }
            else if(operandNode.get("value") != null){
                operand = jsonConverter.convertValue(operandNode, ValueOperand.class);
            }
            else if(operandNode.get("operator") != null){
                Criteria criteriaOperand = parseCriteriaNode(operandNode);
                Operand[] currentOperands = new Operand[criteriaOperand.getOperands().size()];
                operand = new CriteriaOperand(
                    null,
                    criteriaOperand.getOperator(),
                    criteriaOperand.getOperands().toArray(currentOperands)
                );
            }
            else
                continue;

            operands.add(operand);
        }

        criteria.setOperator(operator);
        criteria.setOperands(operands);;
        return criteria;
    }

    public static class CriteriaOperand extends Criteria implements Operand{
        public CriteriaOperand(){

        }
        public CriteriaOperand(Class<?> clazz, Operator operator, Operand... operands) throws Exception {
            super(clazz, operator, operands);
        }
    }

    public static class ValueOperand implements Operand{
        private Object value;

        public ValueOperand() {
        }

        public ValueOperand(Object value){
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public static class FieldOperand implements Operand{


        private String fieldName;
        public FieldOperand() {
        }

        public FieldOperand(String fieldName){
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
    }

//    @JsonDeserialize(using = OperandDeserializer.class)
    public interface Operand{

    }

    public static class OperandDeserializer extends StdDeserializer<Operand> {

        public OperandDeserializer() {
            this(null);
        }

        public OperandDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Operand deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
            JsonNode node = jp.getCodec().readTree(jp);

            if(node == null)
                return null;

            if(node.get("operator") != null)
                return jp.readValueAs(CriteriaOperand.class);
            else if(node.get("fieldName") != null)
                return jp.readValueAs(FieldOperand.class);
            else if(node.get("value") != null)
                return jp.readValueAs(ValueOperand.class);

            return null;
        }
    }

    public enum Operator{
        AND,
        OR,
        EQ,
        NEQ,
        GT,
        GTE,
        LT,
        LTE,
        IN,
        CONTAIN,
        INTERSECT,
        ANY,
        ALL
    }
    public static List<Operator> RelationalOperators = Arrays.asList(
        Operator.EQ,
        Operator.NEQ,
        Operator.GT,
        Operator.GTE,
        Operator.LT,
        Operator.LTE,
        Operator.IN,
        Operator.CONTAIN,
        Operator.INTERSECT
    );
    public static List<Operator> LogicalOperators = Arrays.asList(
        Operator.AND,
        Operator.OR
    );
}
