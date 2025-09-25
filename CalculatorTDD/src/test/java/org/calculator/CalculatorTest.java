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
        assertEquals(1,calculator.Add("1"));
    }

}
