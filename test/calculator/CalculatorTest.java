package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    Calculator calculator;
    static final double DELTA = 0.01;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator(new Converter());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/calculator-test-cases.csv")
    void calculateExpressionTest(String expression, double expectedResult) {
        double actualResult = calculator.calculateExpression(expression);
        assertEquals(expectedResult, actualResult, DELTA);
    }

    @ParameterizedTest
    @MethodSource("getCalculationArguments")
    void calculateOperands(double expected, char operation, double a, double b) {
        double actual = calculator.calculateOperands(operation, a, b);
        assertEquals(expected, actual, DELTA);
    }

    @Test
    void divideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateExpression("1 / 0"));
    }

    @Test
    void checkUnsupportedOperationResult() {
        char unsupportedOperation = '&';
        assertThrows(UnsupportedOperationException.class,
                () -> calculator.calculateOperands(unsupportedOperation, 0, 0));
    }

    private static Stream<Arguments> getCalculationArguments() {
        return Stream.of(
                Arguments.of(5, '+', 2, 3),
                Arguments.of(-1, '-', 2, 3),
                Arguments.of(6, '*', 2, 3),
                Arguments.of(5, '/', 10, 2)
        );
    }

}