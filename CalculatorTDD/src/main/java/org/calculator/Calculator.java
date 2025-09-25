package org.calculator;

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
                delimiter = numbers.substring(2,newLineIndex);
                numbersToProcess = numbers.substring(newLineIndex+1);
            }
        }

        String[] numberStrings = numbersToProcess.split(escapeRegexSpecialChars(delimiter));
        int sum = 0;
        for(String number : numberStrings) {
            sum += Integer.parseInt(number.trim());
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
