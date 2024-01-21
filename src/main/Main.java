package main;

import calculator.Calculator;
import calculator.Converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input an expression: ");
        var infixExpression = scanner.nextLine();

        var calculator = new Calculator(new Converter());
        var result = calculator
                .calculateExpression(infixExpression);

        System.out.println("Result is: " + result);
    }
}