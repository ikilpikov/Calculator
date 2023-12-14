package calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {
    Converter converter;

    @Before
    public void setUp() {
        converter = new Converter();
    }
    @Test
    public void makePostfixExpression() {
        var expectedPostfixExpression = "17.2 5 22 - 4 * +";
        var actualPostfixExpression = converter
                .makePostfixExpression("17.2 + (5 - 22) * 4");

        assertEquals(expectedPostfixExpression, actualPostfixExpression);
    }
}