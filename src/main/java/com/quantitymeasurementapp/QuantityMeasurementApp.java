package com.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Enter first value:");
            double value1 = scanner.nextDouble();

            System.out.println("Enter first unit (FEET, INCHES, YARDS, CENTIMETERS):");
            LengthUnit unit1 = LengthUnit.valueOf(scanner.next().toUpperCase());

            System.out.println("Enter second value:");
            double value2 = scanner.nextDouble();

            System.out.println("Enter second unit (FEET, INCHES, YARDS, CENTIMETERS):");
            LengthUnit unit2 = LengthUnit.valueOf(scanner.next().toUpperCase());

            System.out.println("Enter target unit (FEET, INCHES, YARDS, CENTIMETERS):");
            LengthUnit targetUnit = LengthUnit.valueOf(scanner.next().toUpperCase());

            QuantityLength q1 = new QuantityLength(value1, unit1);
            QuantityLength q2 = new QuantityLength(value2, unit2);

            QuantityLength result =
                    QuantityLength.add(q1, q2, targetUnit);

            System.out.println("Result: " + result);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}