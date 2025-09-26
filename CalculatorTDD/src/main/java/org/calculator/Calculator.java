package org.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public int Add(String numbers) {
        // Handle empty string case - return 0
        if(numbers.isEmpty()){
            return 0;
        }

        // Default delimiters: comma and newline
        String delimiter = "[,|\n]";
        String numbersToProcess = numbers;

        // Check for custom delimiter format: //[delimiter]\n[numbers]
        if(numbers.startsWith("//")) {
            int newLineIndex = numbers.indexOf('\n');
            if(newLineIndex != -1) {
                // Extract the delimiter part between // and \n
                String delimiterPart = numbers.substring(2,newLineIndex);

                // Handle multiple delimiters in brackets: [delim1][delim2]...
                if(delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
                    List<String> delimiters = new ArrayList<>();
                    int start = 0;
                    // Parse all delimiters within brackets
                    while(start < delimiterPart.length()) {
                        int openBracket = delimiterPart.indexOf('[',start);
                        int closeBracket = delimiterPart.indexOf(']', openBracket);
                        if(openBracket != -1 && closeBracket != -1) {
                            delimiters.add(delimiterPart.substring(openBracket+1,closeBracket));
                            start = closeBracket + 1;
                        } else {
                            break;
                        }
                    }
                    // Build regex pattern for multiple delimiters
                    if(delimiters.size() == 1) {
                        delimiter = escapeRegexSpecialChars(delimiters.get(0));
                    } else {
                        StringBuilder regex = new StringBuilder();
                        for (int i = 0; i < delimiters.size(); i++) {
                            if (i > 0) regex.append("|");
                            regex.append(escapeRegexSpecialChars(delimiters.get(i)));
                        }
                        delimiter = regex.toString();
                    }
                } else {
                    // Single character delimiter
                    delimiter = escapeRegexSpecialChars(delimiterPart);
                }
                // Extract the numbers part after the delimiter definition
                numbersToProcess = numbers.substring(newLineIndex+1);
            }
        }

        // Split the numbers string using the determined delimiter
        String[] numberStrings = numbersToProcess.split(delimiter);

        // Check for negative numbers and collect them for error reporting
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

        // Calculate sum, ignoring numbers greater than 1000
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
        // Return default delimiter as-is since it's already a valid regex pattern
        if(delimiter.equals("[,|\n]")) {
            return delimiter; //default
        }

        // Escape all regex special characters to treat them as literal characters
        return delimiter.replaceAll("([\\\\\\[\\]\\{\\}\\(\\)\\*\\+\\?\\^\\$\\|\\.])", "\\\\$1");
    }
}
