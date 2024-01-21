package calculator;

import java.util.Stack;
import java.util.function.BinaryOperator;

public class Calculator {
    private Converter converter;
    BinaryOperator<Double> add = (a, b) -> a + b;
    BinaryOperator<Double> subtract = (a, b) -> a - b;
    BinaryOperator<Double> multiply = (a, b) -> a * b;
    BinaryOperator<Double> divide = (a, b) -> a / b;


    public Calculator(Converter converter) {
        this.converter = converter;
    }

    public double calculateExpression(String expression) {
        String postfixExpression = convertExpressionToPostfix(expression);
        String[] tokens = postfixExpression.split(" ");

        var stack = new Stack<Double>();

        for (var token : tokens) {
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

    public String convertExpressionToPostfix(String infixExpression) {
        return converter.makePostfixExpression(infixExpression);
    }

    public double calculateOperands(char operation, double a, double b) {
        switch (operation) {
            case '+':
                return add.apply(a, b);
            case '-':
                return subtract.apply(a, b);
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