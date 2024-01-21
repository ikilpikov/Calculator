package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterTest {
    Converter converter;

    @BeforeEach
    void setUp() {
        converter = new Converter();
    }

    @Test
    void makePostfixExpressionWithNoDoubles() {
        var expectedPostfixExpression = "17 5 22 - 4 * +";
        var actualPostfixExpression = converter
                .makePostfixExpression("17 + (5 - 22) * 4");

        assertEquals(expectedPostfixExpression, actualPostfixExpression);
    }

    @Test
    void makePostfixExpressionWithDoublesOnly() {
        var expectedPostfixExpression = "3.1 6.3 - 2.1 1.3 + *";
        var actualPostfixExpression = converter
                .makePostfixExpression("(3.1 - 6.3) * (2.1 + 1.3)");

        assertEquals(expectedPostfixExpression, actualPostfixExpression);
    }

    @Test
    void makePostfixExpressionWithMixedNumberType() {
        var expectedPostfixExpression = "3 0.5 - 2.1 1 + /";
        var actualPostfixExpression = converter
                .makePostfixExpression("(3 - 0.5) / (2.1 + 1)");

        assertEquals(expectedPostfixExpression, actualPostfixExpression);
    }
}