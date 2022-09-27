package vn.sapo.utils;

import org.thymeleaf.util.Validate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionUtils {
    private CollectionUtils() {
    }

    public static int size(Collection<?> target) {
        Validate.notNull(target, "Cannot get list size of null");
        return target.size();
    }

    public static boolean isEmpty(Collection<?> target) {
        return target == null || target.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> target) {
        return !isEmpty(target);
    }

    public static boolean contains(Collection<?> target, Object element) {
        Validate.notNull(target, "Cannot execute list contains: target is null");
        return target.contains(element);
    }

    public static boolean containsAll(Collection<?> target, Object[] elements) {
        Validate.notNull(target, "Cannot execute list containsAll: target is null");
        Validate.notNull(elements, "Cannot execute list containsAll: elements is null");
        return containsAll(target, Arrays.asList(elements));
    }

    public static boolean containsAll(Collection<?> target, Collection<?> elements) {
        Validate.notNull(target, "Cannot execute list contains: target is null");
        Validate.notNull(elements, "Cannot execute list containsAll: elements is null");
        return target.containsAll(elements);
    }

    public static <T> String join(Collection<T> list, String delimiter) {
        if (isEmpty(list)) {
            return "";
        } else {
            if (delimiter == null) {
                delimiter = ",";
            }

            return list.stream().map(Object::toString).collect(Collectors.joining(delimiter));
        }
    }

    public static <T> String join(Collection<T> list) {
        return join(list, ",");
    }

    public static Collection<String> normalize(Collection<?> collection) {
        if (collection == null) {
            return Collections.emptyList();
        }
        return collection.stream()
            .filter(Objects::nonNull)
            .map(String::valueOf)
            .collect(Collectors.toList());
    }
}
