package calculator;

import java.util.Stack;

public class Converter {
    public String makePostfixExpression(String infixExpression) {
        var postfix = new StringBuilder();
        var stack = new Stack<Character>();

        for (char ch : infixExpression.toCharArray()) {
            if (Character.isDigit(ch)) {
                postfix.append(ch).append(" ");
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(ch)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    private static boolean isOperator(char ch) {
        return ch == '+'
                || ch == '-'
                || ch == '*'
                || ch == '/';
    }

    private static int getPriority(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
}
