package vn.sapo.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecificationUtilsUT {
    private static ObjectMapper jsonConverter = new ObjectMapper();
    @Test
    public void testCriteriaMatching() throws Exception {
        Order order = new Order();
        order.setName("hoinx");
        order.setAge(30);
        order.setAdministrativeUnit("134343");
        order.setTags(Arrays.asList("1", "2", "t1", "t2"));
        order.setLineItems(new ArrayList<>());
        order.getLineItems().add(new OrderLineItem(1L, new BigDecimal(1), 1));
        order.getLineItems().add(new OrderLineItem(2L, new BigDecimal(2), 2));

        SpecificationUtils.Criteria criteria1 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.EQ,
            new SpecificationUtils.FieldOperand("name"),
            new SpecificationUtils.ValueOperand("hoinx")
        );
        boolean result1 = criteria1.match(order);
        assert(result1 == true);

        SpecificationUtils.Criteria criteria2 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.EQ,
            new SpecificationUtils.FieldOperand("name"),
            new SpecificationUtils.ValueOperand("hoinx1")
        );
        boolean result2 = criteria2.match(order);
        assert(result2 == false);

        SpecificationUtils.Criteria criteria3 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.GT,
            new SpecificationUtils.FieldOperand("age"),
            new SpecificationUtils.ValueOperand("1")
        );
        boolean result3 = criteria3.match(order);
        assert(result3 == true);

        SpecificationUtils.Criteria criteria4 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.GTE,
            new SpecificationUtils.FieldOperand("age"),
            new SpecificationUtils.ValueOperand(30)
        );
        boolean result4 = criteria4.match(order);
        assert(result4 == true);

        boolean result5 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.GTE,
            new SpecificationUtils.FieldOperand("age"),
            new SpecificationUtils.ValueOperand(31)
        ).match(order);
        assert(result5 == false);

        boolean result6 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.IN,
            new SpecificationUtils.FieldOperand("name"),
            new SpecificationUtils.ValueOperand(Arrays.asList("hoinx", "hoinx01", "hoinx02"))
        ).match(order);
        assert(result6 == true);

        boolean result7 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.IN,
            new SpecificationUtils.FieldOperand("name"),
            new SpecificationUtils.ValueOperand(Arrays.asList("hoinx01", "hoinx02"))
        ).match(order);
        assert(result7 == false);

        boolean result8 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.CONTAIN,
            new SpecificationUtils.FieldOperand("tags"),
            new SpecificationUtils.ValueOperand("t1")
        ).match(order);
        assert(result8 == true);

        boolean result9 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.CONTAIN,
            new SpecificationUtils.FieldOperand("tags"),
            new SpecificationUtils.ValueOperand("t3")
        ).match(order);
        assert(result9 == false);

        boolean result10 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.CONTAIN,
            new SpecificationUtils.FieldOperand("tags"),
            new SpecificationUtils.ValueOperand("1")
        ).match(order);
        assert(result10 == true);

        boolean result11 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.ANY,
            new SpecificationUtils.FieldOperand("lineItems"),
            new SpecificationUtils.CriteriaOperand(
                OrderLineItem.class,
                SpecificationUtils.Operator.EQ,
                new SpecificationUtils.FieldOperand("variantId"),
                new SpecificationUtils.ValueOperand(1L)
            )
        ).match(order);
        assert(result11 == true);

        boolean result12 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.ANY,
            new SpecificationUtils.FieldOperand("lineItems"),
            new SpecificationUtils.CriteriaOperand(
                OrderLineItem.class,
                SpecificationUtils.Operator.GT,
                new SpecificationUtils.FieldOperand("quantity"),
                new SpecificationUtils.ValueOperand(new BigDecimal(-1))
            )
        ).match(order);
        assert(result12 == true);

        boolean result13 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.ANY,
            new SpecificationUtils.FieldOperand("lineItems"),
            new SpecificationUtils.CriteriaOperand(
                OrderLineItem.class,
                SpecificationUtils.Operator.GTE,
                new SpecificationUtils.FieldOperand("quantity"),
                new SpecificationUtils.ValueOperand(new BigDecimal(1))
            )
        ).match(order);
        assert(result13 == true);

        boolean result14 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.ANY,
            new SpecificationUtils.FieldOperand("lineItems"),
            new SpecificationUtils.CriteriaOperand(
                OrderLineItem.class,
                SpecificationUtils.Operator.GTE,
                new SpecificationUtils.FieldOperand("quantity"),
                new SpecificationUtils.ValueOperand(new BigDecimal(3))
            )
        ).match(order);
        assert(result14 == false);

        boolean result15 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.ANY,
            new SpecificationUtils.FieldOperand("lineItems"),
            new SpecificationUtils.CriteriaOperand(
                OrderLineItem.class,
                SpecificationUtils.Operator.GTE,
                new SpecificationUtils.FieldOperand("variantId"),
                new SpecificationUtils.ValueOperand("1")
            )
        ).match(order);
        assert(result15 == true);

        boolean result16 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.ALL,
            new SpecificationUtils.FieldOperand("lineItems"),
            new SpecificationUtils.CriteriaOperand(
                OrderLineItem.class,
                SpecificationUtils.Operator.GTE,
                new SpecificationUtils.FieldOperand("variantId"),
                new SpecificationUtils.ValueOperand("1")
            )
        ).match(order);
        assert(result16 == true);

        boolean result17 = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.ALL,
            new SpecificationUtils.FieldOperand("lineItems"),
            new SpecificationUtils.CriteriaOperand(
                OrderLineItem.class,
                SpecificationUtils.Operator.GTE,
                new SpecificationUtils.FieldOperand("variantId"),
                new SpecificationUtils.ValueOperand("2")
            )
        ).match(order);
        assert(result17 == false);
    }

    @Test
    public void testBuildingJson() throws Exception {
        String json = getJson();
        System.out.println(json);
        assert (json.length() > 0);
    }

    @Test
    public void parseJson() throws Exception {
        String json = getJson();
        System.out.println(json);

        JsonNode rootNode = jsonConverter.readTree(json);
        SpecificationUtils.Criteria criteria = SpecificationUtils.parseCriteriaNode(rootNode);

        String reJson = jsonConverter.writeValueAsString(criteria);
        System.out.println(reJson);

        assert (json.equals(reJson));
    }



    private String getJson() throws Exception {
        SpecificationUtils.Criteria criteria = new SpecificationUtils.Criteria(
            Order.class,
            SpecificationUtils.Operator.AND,
            new SpecificationUtils.CriteriaOperand(
                Order.class,
                SpecificationUtils.Operator.EQ,
                new SpecificationUtils.FieldOperand("name"),
                new SpecificationUtils.ValueOperand("hoinx")
            ),
            new SpecificationUtils.CriteriaOperand(
                Order.class,
                SpecificationUtils.Operator.ALL,
                new SpecificationUtils.FieldOperand("lineItems"),
                new SpecificationUtils.CriteriaOperand(
                    OrderLineItem.class,
                    SpecificationUtils.Operator.GTE,
                    new SpecificationUtils.FieldOperand("variantId"),
                    new SpecificationUtils.ValueOperand("2")
                )
            )
        );

        return jsonConverter.writeValueAsString(criteria);
    }

    public static class Order{
        private String name;
        private Integer age;
        private String administrativeUnit;
        private List<String> tags;
        private List<OrderLineItem> lineItems;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAdministrativeUnit() {
            return administrativeUnit;
        }

        public void setAdministrativeUnit(String administrativeUnit) {
            this.administrativeUnit = administrativeUnit;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<OrderLineItem> getLineItems() {
            return lineItems;
        }

        public void setLineItems(List<OrderLineItem> lineItems) {
            this.lineItems = lineItems;
        }
    }

    public static class OrderLineItem{
        private Long variantId;
        private BigDecimal quantity;
        private Integer categoryId;

        public OrderLineItem(Long variantId, BigDecimal quantity, Integer categoryId) {
            this.variantId = variantId;
            this.quantity = quantity;
            this.categoryId = categoryId;
        }

        public Long getVariantId() {
            return variantId;
        }

        public void setVariantId(Long variantId) {
            this.variantId = variantId;
        }

        public BigDecimal getQuantity() {
            return quantity;
        }

        public void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }
    }
}
