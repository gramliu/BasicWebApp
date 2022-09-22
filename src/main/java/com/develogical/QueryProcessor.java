package com.develogical;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryProcessor {

    private static Pattern numberPattern = Pattern.compile("\\d+");

    private static List<Long> getNumbers(String query) {
        List<Long> numbers = new ArrayList<>();
        Matcher matcher = numberPattern.matcher(query);
        while (matcher.find()) {
            numbers.add(Long.parseLong(matcher.group()));
        }
        return numbers;
    }

    private static boolean isSquare(long l) {
        for (long i = 0; i < Math.sqrt(l) + 1; i++) {
            if (i * i == l) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCube(long l) {
        for (long i = 0; i < Math.sqrt(l) + 1; i++) {
            if (i * i * i == l) {
                return true;
            }
        }
        return false;
    }

    public String process(String query) {
        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }
        if (query.toLowerCase().contains("name")) {
            return "dyno-saur";
        }
        if (query.contains("plus") || query.contains("+")) {
            List<Long> numbers = getNumbers(query);
            long sum = numbers.stream().reduce(0L, Long::sum);
            return String.valueOf(sum);
        }
        if (query.contains("minus") || query.contains("-")) {
            List<Long> numbers = getNumbers(query);
            long sum = numbers.stream().reduce(0L, (a, b) -> a - b);
            return String.valueOf(sum);
        }
        if (query.contains("multiplied") || query.contains("times") || query.contains("*")) {
            List<Long> numbers = getNumbers(query);
            long sum = numbers.stream().reduce(1L, (a, b) -> a * b);
            return String.valueOf(sum);
        }
        if (query.contains("divided")) {
            List<Long> numbers = getNumbers(query);
            long sum = numbers.stream().reduce(1L, (a, b) -> a / b);
            return String.valueOf(sum);
        }
        if (query.contains("largest")) {
            List<Long> numbers = getNumbers(query);
            long largest = numbers.stream().reduce(Long.MIN_VALUE, Long::max);
            return String.valueOf(largest);
        }
        if (query.contains("smallest")) {
            List<Long> numbers = getNumbers(query);
            long smallest = numbers.stream().reduce(Long.MAX_VALUE, Long::min);
            return String.valueOf(smallest);
        }
        if (query.contains("square and a cube")) {
            List<Long> numbers = getNumbers(query);
            List<Long> matching = numbers.stream().filter((a) -> isSquare(a) && isCube(a)).collect(Collectors.toList());
            return matching.toString();
        }
        return "";
    }
}
