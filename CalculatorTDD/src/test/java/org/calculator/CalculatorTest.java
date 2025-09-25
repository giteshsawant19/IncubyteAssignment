package org.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, calculator.Add(""));
    }

    @Test
    public void shouldAddSingleNumber() {
        assertEquals(1, calculator.Add("1"));
    }

    @Test
    public void shouldAddTwoNumbers() {
        assertEquals(4, calculator.Add("1,3"));
    }

    @Test
    public void shouldAddMultipleNumbers(){
        assertEquals(6, calculator.Add("1,2,3"));
        assertEquals(100, calculator.Add("25,25,25,25"));
        assertEquals(25, calculator.Add("24,1"));
        assertEquals(0, calculator.Add("0,0,0"));
    }

    @Test
    public void shouldAddNumberWithNewLines() {
        assertEquals(5, calculator.Add("2\n3"));
        assertEquals(25, calculator.Add("5,15\n5"));
    }

    @Test
    public void shouldAddWithDelimiters() {
        assertEquals(10, calculator.Add("//;\n1;9"));
        assertEquals(6, calculator.Add("//@\n1@2@3"));
        assertEquals(10, calculator.Add("//$\n1$2$3$4"));
    }

    @Test
    public void shouldNotAllowAddWithSingleNegativeNumber() {
        try {
            calculator.Add("1,-2,3");
            Assertions.fail("Expected RuntimeException for negative numbers");
        } catch (RuntimeException e) {
            assertEquals("negatives not allowed: -2",e.getMessage());
        }
    }

    @Test
    public void shouldNotAllowAddWithMultipleNegativeNumbers() {
        try {
            calculator.Add("1\n-2,-3");
            Assertions.fail("Expected RuntimeException for negative numbers");
        } catch (RuntimeException e) {
            assertEquals("negatives not allowed: -2, -3",e.getMessage());
        }
    }

    @Test
    public void shouldNotAllowAddWithMultipleNegativeNumbersAndCustomDelimiter() {
        try {
            calculator.Add("//;\n1;-2;3");
            org.junit.jupiter.api.Assertions.fail("Expected RuntimeException for negative numbers");
        } catch (RuntimeException e) {
            assertEquals("negatives not allowed: -2", e.getMessage());
        }
    }

    @Test
    public void shouldNotAddNumbersBiggerThan1000() {
        assertEquals(2, calculator.Add("2,1001"));
        assertEquals(3, calculator.Add("1\n2,1001"));
        assertEquals(0, calculator.Add("1001,1002,1003"));
        assertEquals(6, calculator.Add("//|\n1|2|3|1001"));
    }
}
