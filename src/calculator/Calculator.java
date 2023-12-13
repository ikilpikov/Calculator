package calculator;

import java.util.Stack;
import java.util.function.BinaryOperator;

public class Calculator {
    BinaryOperator<Double> add = (a, b) -> a + b;
    BinaryOperator<Double> substract = (a, b) -> a - b;
    BinaryOperator<Double> multiply = (a, b) -> a * b;
    BinaryOperator<Double> divide = (a, b) -> a / b;

    public double calculatePostfixExpression(String expression) {
        String[] tokens = expression.split(" ");

        var stack = new Stack<Double>();

        for (String token : tokens) {
            if (token.matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")) {
                stack.push(Double.parseDouble(token));
            } else {
                var operand2 = stack.pop();
                var operand1 = stack.pop();
                var result = calculateOperands(token.charAt(0), operand1, operand2);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    public double calculateOperands(char operation, double a, double b) {
        switch (operation) {
            case '+':
                return add.apply(a, b);
            case '-':
                return substract.apply(a, b);
            case '*':
                return multiply.apply(a, b);
            case '/':
                if (b == 0) {
                    throw new IllegalArgumentException("Divide by zero.");
                }
                return divide.apply(a, b);

            default:
                throw new UnsupportedOperationException();
        }
    }
}