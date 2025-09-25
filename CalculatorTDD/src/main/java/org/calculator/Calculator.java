package org.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public int Add(String numbers) {
        if(numbers.isEmpty()){
            return 0;
        }

        String delimiter = "[,|\\n]";
        String numbersToProcess = numbers;

        if(numbers.startsWith("//")) {
            int newLineIndex = numbers.indexOf('\n');
            if(newLineIndex != -1) {
                String delimiterPart = numbers.substring(2,newLineIndex);

                if(delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
                    delimiter = delimiterPart.substring(1, delimiterPart.length() - 1);
                } else {
                    delimiter = delimiterPart;
                }
                numbersToProcess = numbers.substring(newLineIndex+1);
            }
        }

        String[] numberStrings = numbersToProcess.split(escapeRegexSpecialChars(delimiter));

        List<String> negativeNumbers = new ArrayList<>();
        for(String number : numberStrings) {
            if(!number.isEmpty()) {
                if(Integer.parseInt(number.trim()) < 0){
                    negativeNumbers.add(number);
                }
            }
        }

        // Throw exception if negative numbers found
        if (!negativeNumbers.isEmpty()) {
            throw new RuntimeException("negatives not allowed: " + String.join(", ", negativeNumbers));
        }

        int sum = 0;
        for(String number : numberStrings) {
                int numberValue = Integer.parseInt(number);
                if (numberValue <= 1000) {
                    sum += numberValue;
                }
            }
        return sum;
    }

    private String escapeRegexSpecialChars(String delimiter) {
        if(delimiter.equals("[,|\\n]")) {
            return delimiter; //default
        }

        return delimiter.replaceAll("([\\\\\\[\\]\\{\\}\\(\\)\\*\\+\\?\\^\\$\\|\\.])", "\\\\$1");
    }
}
