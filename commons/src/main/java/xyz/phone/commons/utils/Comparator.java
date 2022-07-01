package xyz.phone.commons.utils;

import java.util.function.Function;

public class Comparator<T> {

    public java.util.Comparator<T> byNumber(Function<T, String> function) {
        return (obj1, obj2) -> {
            String value1 = function.apply(obj1);
            String value2 = function.apply(obj2);
            return Long.compare(
                    Converter.toLong(value1),
                    Converter.toLong(value2)
            );
        };
    }

    public java.util.Comparator<T> byNumberDesc(Function<T, String> function) {
        return (obj1, obj2) -> {
            String value1 = function.apply(obj1);
            String value2 = function.apply(obj2);
            return Long.compare(
                    Converter.toLong(value1) * -1L,
                    Converter.toLong(value2) * -1L
            );
        };
    }
}
