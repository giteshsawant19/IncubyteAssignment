package org.calculator;

public class Calculator {

    public int Add(String numbers) {
        if(numbers.isEmpty()){
            return 0;
        }

        String[] numberStrings = numbers.split("[,|\\n]");
        int sum = 0;
        for(String number : numberStrings) {
            sum += Integer.parseInt(number.trim());
        }
        return sum;
    }
}
