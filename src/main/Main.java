package main;

import calculator.Calculator;
import calculator.Converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input an expression: ");
        var infixExpression = scanner.nextLine();
        var postfixExpression = new Converter()
                .makePostfixExpression(infixExpression);

        var calculator = new Calculator();
        var result = calculator
                .calculatePostfixExpression(postfixExpression);

        System.out.println("Result is: " + result);
    }
}