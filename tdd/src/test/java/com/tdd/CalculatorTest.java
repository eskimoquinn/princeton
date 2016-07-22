package com.tdd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by tgreen on 7/12/16.
 */
public class CalculatorTest {

    private Calculator calculator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void add_emptyString_returnsZero(){
        int actual = calculator.add("");

        assertThat(actual, is(0));
    }

    @Test
    public void add_nullString_returnsZero(){
        int actual = calculator.add(null);

        assertThat(actual, is(0));
    }

    @Test
    public void add_oneNumber_returnsNumber(){
        int actual = calculator.add("1");

        assertThat(actual, is(1));
    }

    @Test
    public void add_twoNumbers_returnsSum(){
        int actual = calculator.add("1,2");

        assertThat(actual, is(3));
    }

    @Test
    public void add_fourNumbers_returnsSum(){
        int actual = calculator.add("1,2,3,4");

        assertThat(actual, is(10));
    }

    @Test
    public void add_lineBreakDelimiter_returnsSum(){
        int actual = calculator.add("1\n2,3");

        assertThat(actual, is(6));
    }

    @Test
    public void add_defineDelimiter_returnsSum(){
        int actual = calculator.add("//;\n1;2");

        assertThat(actual, is(3));
    }

    @Test
    public void add_negativeNumber_NumberFormatExceptionWithMessage(){
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("negatives not allowed");

        calculator.add("1,-2");
    }

}
