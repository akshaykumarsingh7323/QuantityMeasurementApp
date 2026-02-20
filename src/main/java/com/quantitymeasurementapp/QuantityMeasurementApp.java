package com.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter first value in feet: ");
            double value1 = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter second value in feet: ");
            double value2 = Double.parseDouble(scanner.nextLine());

            Feet feet1 = new Feet(value1);
            Feet feet2 = new Feet(value2);

            boolean result = feet1.equals(feet2);

            System.out.println("Equal: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values.");
        }

        scanner.close();
    }
}
