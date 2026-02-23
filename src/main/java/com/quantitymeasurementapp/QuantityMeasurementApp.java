package com.quantitymeasurementapp;

import java.util.Scanner;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first value:");
        double value1 = scanner.nextDouble();

        System.out.println("Enter first unit (FEET, INCHES, YARDS, CENTIMETERS):");
        LengthUnit unit1 = LengthUnit.valueOf(scanner.next().toUpperCase());

        System.out.println("Enter second value:");
        double value2 = scanner.nextDouble();

        System.out.println("Enter second unit (FEET, INCHES, YARDS, CENTIMETERS):");
        LengthUnit unit2 = LengthUnit.valueOf(scanner.next().toUpperCase());

        QuantityLength q1 = new QuantityLength(value1, unit1);
        QuantityLength q2 = new QuantityLength(value2, unit2);

        QuantityLength result = q1.add(q2);

        System.out.println("Result (in first operand unit): " + result);

        scanner.close();
    }
}