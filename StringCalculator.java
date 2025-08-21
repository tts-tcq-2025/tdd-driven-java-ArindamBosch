package com.example.calculator;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] tokens = tokenize(input);
        return sum(tokens);
    }

    private String[] tokenize(String input) {
        String delimiter = ",|\n";

        if (input.startsWith("//")) {
            int delimiterEnd = input.indexOf("\n");
            String delimiterSpec = input.substring(2, delimiterEnd);
            if (delimiterSpec.startsWith("[") && delimiterSpec.endsWith("]")) {
                delimiterSpec = delimiterSpec.substring(1, delimiterSpec.length() - 1);
            }
            delimiter = Pattern.quote(delimiterSpec);
            input = input.substring(delimiterEnd + 1);
        }

        return input.split(delimiter);
    }

    private int sum(String[] tokens) {
        List<Integer> negatives = new ArrayList<>();
        int total = 0;

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            int value = Integer.parseInt(token);

            if (value < 0) {
                negatives.add(value);
            } else if (value <= 1000) {
                total += value;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " +
                    negatives.stream()
                             .map(String::valueOf)
                             .collect(Collectors.joining(",")));
        }

        return total;
    }
}
