package com.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Choose Operation:");
            System.out.println("1 - Convert");
            System.out.println("2 - Add (Implicit Target)");
            System.out.println("3 - Add (Explicit Target)");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            if (choice == 1) {

                System.out.print("Enter value: ");
                double value = scanner.nextDouble();

                System.out.print("Enter unit (FEET, INCHES, YARDS, CENTIMETERS): ");
                LengthUnit unit =
                        LengthUnit.valueOf(scanner.next().toUpperCase());

                System.out.print("Enter target unit: ");
                LengthUnit targetUnit =
                        LengthUnit.valueOf(scanner.next().toUpperCase());

                QuantityLength quantity =
                        new QuantityLength(value, unit);

                QuantityLength result =
                        quantity.convertTo(targetUnit);

                System.out.println("Result: " + result);
            }

            else if (choice == 2) {

                System.out.print("Enter first value: ");
                double v1 = scanner.nextDouble();

                System.out.print("Enter first unit: ");
                LengthUnit u1 =
                        LengthUnit.valueOf(scanner.next().toUpperCase());

                System.out.print("Enter second value: ");
                double v2 = scanner.nextDouble();

                System.out.print("Enter second unit: ");
                LengthUnit u2 =
                        LengthUnit.valueOf(scanner.next().toUpperCase());

                QuantityLength q1 = new QuantityLength(v1, u1);
                QuantityLength q2 = new QuantityLength(v2, u2);

                QuantityLength result = q1.add(q2);

                System.out.println("Result: " + result);
            }

            else if (choice == 3) {

                System.out.print("Enter first value: ");
                double v1 = scanner.nextDouble();

                System.out.print("Enter first unit: ");
                LengthUnit u1 =
                        LengthUnit.valueOf(scanner.next().toUpperCase());

                System.out.print("Enter second value: ");
                double v2 = scanner.nextDouble();

                System.out.print("Enter second unit: ");
                LengthUnit u2 =
                        LengthUnit.valueOf(scanner.next().toUpperCase());

                System.out.print("Enter target unit: ");
                LengthUnit targetUnit =
                        LengthUnit.valueOf(scanner.next().toUpperCase());

                QuantityLength q1 = new QuantityLength(v1, u1);
                QuantityLength q2 = new QuantityLength(v2, u2);

                QuantityLength result =
                        q1.add(q2, targetUnit);

                System.out.println("Result: " + result);
            }

            else {
                System.out.println("Invalid choice!");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input - " + e.getMessage());
        }

        scanner.close();
    }
}