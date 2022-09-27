package vn.sapo.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectUtils {
    public static List<Field> getAllFields(List<Field> fields, Class<?> clazz) {
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fields = getAllFields(fields, clazz.getSuperclass());
        }
        return fields;
    }

    public static Field getField(String fieldName, Class<?> clazz){
        List<Field> fields = new ArrayList<>();
        fields = ObjectUtils.getAllFields(fields, clazz);

        Field field = fields.stream()
            .filter(f -> f.getName().equals(fieldName))
            .findFirst()
            .orElse(null);
        field.setAccessible(true);
        return field;
    }

    public static <T> boolean equals(T obj1, T obj2) {
        boolean result = true;
        if (obj1 == null && obj2 != null)
            result = false;
        if (obj1 != null && obj2 == null)
            result = false;
        if (obj1 == null && obj2 == null)
            result = true;
        if (obj1 != null && obj2 != null) {
            result = obj1.equals(obj2);
        }
        return result;
    }

    public static <T> boolean equalsInString(T obj1, T obj2) {
        boolean result = true;
        if (obj1 == null && obj2 != null)
            result = false;
        if (obj1 != null && obj2 == null)
            result = false;
        if (obj1 == null && obj2 == null)
            result = true;
        if (obj1 != null && obj2 != null) {
            result = obj1.toString().equals(obj2.toString());
        }
        return result;
    }

    public static <T> int compare(T obj1, Object obj2) throws Exception {
        if(equals(obj1, obj2))
            return 0;

        if(obj2 == null)
            throw new Exception("target value can not be null");

        if(obj1 == null)
            throw new Exception("target value can not be null");

        if(equals(obj1.toString(), obj2.toString()))
            return 0;

        if(obj1 instanceof Byte)
            return (Byte) obj1 > Byte.valueOf(obj2.toString()) ? 1 : -1;
        if(obj1 instanceof Integer)
            return (Integer) obj1 > Integer.valueOf(obj2.toString())  ? 1 : -1;
        else if(obj1 instanceof Long)
            return (Long) obj1 > Long.valueOf(obj2.toString()) ? 1 : -1;
        else if(obj1 instanceof Float)
            return (Float) obj1 > Float.valueOf(obj2.toString()) ? 1 : -1;
        else if(obj1 instanceof Double)
            return (Double) obj1 > Double.valueOf(obj2.toString()) ? 1 : -1;
        else if (obj1 instanceof BigDecimal)
            return ((BigDecimal) obj1).compareTo(new BigDecimal(obj2.toString())) > 0 ? 1 : -1;

        throw new Exception("Chưa định nghĩa hàm compare cho kiểu " + obj1.getClass().getName());
    }
    public static boolean in(Object fieldValue, Object container) throws Exception {
        if(container == null)
            throw new Exception("container must not be null");
        if(!(container instanceof List))
            throw new Exception("value must be array or list");
        if(fieldValue == null)
            return false;
        return ((List) container).stream()
            .map(v -> v.toString())
            .filter(v -> v.equals(fieldValue.toString()))
            .count() > 0;
    }
    public static boolean contains(Class<?> fieldType, Object fieldValue, Object value) throws Exception {
        if(!fieldType.getName().equals(List.class.getName()))
            throw new Exception("can not apply CONTAIN operator for non array property");
        return ((List) fieldValue).contains(value);
    }
}
