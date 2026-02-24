package com.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Select Category:");
        System.out.println("1. Length");
        System.out.println("2. Weight");

        int category = sc.nextInt();

        switch (category) {

            case 1:
                handleLength(sc);
                break;

            case 2:
                handleWeight(sc);
                break;

            default:
                System.out.println("Invalid Category");
        }

        sc.close();
    }

    private static void handleLength(Scanner sc) {

        System.out.println("Enter value:");
        double value = sc.nextDouble();

        System.out.println("Select Unit:");
        System.out.println("1.FEET 2.INCHES 3.YARDS 4.CENTIMETERS");
        int unitChoice = sc.nextInt();

        LengthUnit unit;

        switch (unitChoice) {
            case 1: unit = LengthUnit.FEET; break;
            case 2: unit = LengthUnit.INCHES; break;
            case 3: unit = LengthUnit.YARDS; break;
            case 4: unit = LengthUnit.CENTIMETERS; break;
            default: throw new IllegalArgumentException("Invalid unit");
        }

        Quantity<LengthUnit> q1 = new Quantity<>(value, unit);

        System.out.println("Select Operation:");
        System.out.println("1.Equality 2.Conversion 3.Addition");

        int op = sc.nextInt();

        switch (op) {

            case 1:
                System.out.println("Enter second value:");
                double v2 = sc.nextDouble();

                System.out.println("Select unit:");
                int u2Choice = sc.nextInt();

                LengthUnit u2 = switch (u2Choice) {
                    case 1 -> LengthUnit.FEET;
                    case 2 -> LengthUnit.INCHES;
                    case 3 -> LengthUnit.YARDS;
                    case 4 -> LengthUnit.CENTIMETERS;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                Quantity<LengthUnit> q2 = new Quantity<>(v2, u2);
                System.out.println("Result: " + q1.equals(q2));
                break;

            case 2:
                System.out.println("Convert to unit:");
                int targetChoice = sc.nextInt();

                LengthUnit target = switch (targetChoice) {
                    case 1 -> LengthUnit.FEET;
                    case 2 -> LengthUnit.INCHES;
                    case 3 -> LengthUnit.YARDS;
                    case 4 -> LengthUnit.CENTIMETERS;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                System.out.println("Result: " + q1.convertTo(target));
                break;

            case 3:
                System.out.println("Enter second value:");
                double addVal = sc.nextDouble();

                System.out.println("Select unit:");
                int addUnitChoice = sc.nextInt();

                LengthUnit addUnit = switch (addUnitChoice) {
                    case 1 -> LengthUnit.FEET;
                    case 2 -> LengthUnit.INCHES;
                    case 3 -> LengthUnit.YARDS;
                    case 4 -> LengthUnit.CENTIMETERS;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                Quantity<LengthUnit> qAdd = new Quantity<>(addVal, addUnit);

                System.out.println("Result unit:");
                int resultUnitChoice = sc.nextInt();

                LengthUnit resultUnit = switch (resultUnitChoice) {
                    case 1 -> LengthUnit.FEET;
                    case 2 -> LengthUnit.INCHES;
                    case 3 -> LengthUnit.YARDS;
                    case 4 -> LengthUnit.CENTIMETERS;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                System.out.println("Result: " + q1.add(qAdd, resultUnit));
                break;

            default:
                System.out.println("Invalid operation");
        }
    }

    private static void handleWeight(Scanner sc) {

        System.out.println("Enter value:");
        double value = sc.nextDouble();

        System.out.println("Select Unit:");
        System.out.println("1.KG 2.GRAM 3.POUND");

        int unitChoice = sc.nextInt();

        WeightUnit unit;

        switch (unitChoice) {
            case 1: unit = WeightUnit.KILOGRAM; break;
            case 2: unit = WeightUnit.GRAM; break;
            case 3: unit = WeightUnit.POUND; break;
            default: throw new IllegalArgumentException("Invalid unit");
        }

        Quantity<WeightUnit> q1 = new Quantity<>(value, unit);

        System.out.println("Select Operation:");
        System.out.println("1.Equality 2.Conversion 3.Addition");

        int op = sc.nextInt();

        switch (op) {

            case 1:
                System.out.println("Enter second value:");
                double v2 = sc.nextDouble();

                System.out.println("Select unit:");
                int u2Choice = sc.nextInt();

                WeightUnit u2 = switch (u2Choice) {
                    case 1 -> WeightUnit.KILOGRAM;
                    case 2 -> WeightUnit.GRAM;
                    case 3 -> WeightUnit.POUND;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                Quantity<WeightUnit> q2 = new Quantity<>(v2, u2);
                System.out.println("Result: " + q1.equals(q2));
                break;

            case 2:
                System.out.println("Convert to unit:");
                int targetChoice = sc.nextInt();

                WeightUnit target = switch (targetChoice) {
                    case 1 -> WeightUnit.KILOGRAM;
                    case 2 -> WeightUnit.GRAM;
                    case 3 -> WeightUnit.POUND;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                System.out.println("Result: " + q1.convertTo(target));
                break;

            case 3:
                System.out.println("Enter second value:");
                double addVal = sc.nextDouble();

                System.out.println("Select unit:");
                int addUnitChoice = sc.nextInt();

                WeightUnit addUnit = switch (addUnitChoice) {
                    case 1 -> WeightUnit.KILOGRAM;
                    case 2 -> WeightUnit.GRAM;
                    case 3 -> WeightUnit.POUND;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                Quantity<WeightUnit> qAdd = new Quantity<>(addVal, addUnit);

                System.out.println("Result unit:");
                int resultUnitChoice = sc.nextInt();

                WeightUnit resultUnit = switch (resultUnitChoice) {
                    case 1 -> WeightUnit.KILOGRAM;
                    case 2 -> WeightUnit.GRAM;
                    case 3 -> WeightUnit.POUND;
                    default -> throw new IllegalArgumentException("Invalid");
                };

                System.out.println("Result: " + q1.add(qAdd, resultUnit));
                break;

            default:
                System.out.println("Invalid operation");
        }
    }
}