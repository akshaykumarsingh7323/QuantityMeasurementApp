package com.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== UC5 Length Conversion =====\n");

        try {

            System.out.print("Enter value: ");
            double value = scanner.nextDouble();

            System.out.println("\nAvailable Units:");
            for (LengthUnit unit : LengthUnit.values()) {
                System.out.println("- " + unit);
            }

            System.out.print("\nEnter source unit (FEET): ");
            LengthUnit source =
                    LengthUnit.valueOf(scanner.next().toUpperCase());

            System.out.print("Enter target unit (INCHES): ");
            LengthUnit target =
                    LengthUnit.valueOf(scanner.next().toUpperCase());

            double result =
                    QuantityLength.convert(value, source, target);

            System.out.println("\nResult:");
            System.out.println(value + " " + source +
                    " = " + result + " " + target);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid input or unit.");
        } catch (Exception e) {
            System.err.println("\nUnexpected error occurred.");
        } finally {
            scanner.close();
        }
    }
}