package springbook.learningtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @Before
    public void setUp() throws IOException {
        this.calculator = new Calculator();
        this.numFilepath = java.net.URLDecoder.decode(getClass().getResource("/numbers.txt").getPath(), "UTF-8");
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(this.numFilepath), is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(this.numFilepath), is(24));
    }

    @Test
    public void concatenate() throws IOException {
        assertThat(calculator.concatenate(this.numFilepath), is("1234"));
    }


}
