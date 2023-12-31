package calculator;

import calculator.Calculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calculator;
    @Before
    public void setUp(){
        calculator = new Calculator();
    }


    @Test
    public void calculatePostfixExpression() {
        var expression = "17.2 5 22 - 4 * +";
        var actualResult = calculator.calculatePostfixExpression(expression);

        var expectedResult = -50.8;

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    public void calculateOperands() {
        assertEquals(5, calculator.calculateOperands('+', 2, 3), 0.01);
        assertEquals(-1, calculator.calculateOperands('-', 2, 3), 0.01);
        assertEquals(6, calculator.calculateOperands('*', 2, 3), 0.01);
        assertEquals(5, calculator.calculateOperands('/', 10, 2), 0.01);
    }
}