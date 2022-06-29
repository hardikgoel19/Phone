package xyz.phone.commons.utils;

import java.util.function.Function;

import xyz.phone.commons.model.Message;

public class Comparator {

    public static java.util.Comparator<Message> byNumber(Function<Message, String> function) {
        return (obj1, obj2) -> {
            String value1 = function.apply(obj1);
            String value2 = function.apply(obj2);
            return Long.compare(
                    Converter.toLong(value1),
                    Converter.toLong(value2)
            );
        };
    }

    public static java.util.Comparator<Message> byNumberDesc(Function<Message, String> function) {
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
