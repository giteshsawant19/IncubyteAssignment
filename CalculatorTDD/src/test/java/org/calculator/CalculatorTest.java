package org.calculator;

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
}
