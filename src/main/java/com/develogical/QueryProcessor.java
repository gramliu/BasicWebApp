package com.develogical;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    private static Pattern numberPattern = Pattern.compile("\\d+");

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
            Matcher matcher = numberPattern.matcher(query);
            List<Long> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Long.parseLong(matcher.group()));
            }
            System.out.println(numbers);
            long sum = numbers.stream().reduce(0L, Long::sum);
            return String.valueOf(sum);
        }
        return "";
    }
}
