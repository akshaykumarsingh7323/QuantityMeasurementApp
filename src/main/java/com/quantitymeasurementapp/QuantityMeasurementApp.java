package com.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first value:");
        double value1 = sc.nextDouble();

        System.out.println("Enter first unit (KILOGRAM / GRAM / POUND):");
        WeightUnit unit1 = WeightUnit.valueOf(sc.next().toUpperCase());

        System.out.println("Enter second value:");
        double value2 = sc.nextDouble();

        System.out.println("Enter second unit (KILOGRAM / GRAM / POUND):");
        WeightUnit unit2 = WeightUnit.valueOf(sc.next().toUpperCase());

        QuantityWeight w1 = new QuantityWeight(value1, unit1);
        QuantityWeight w2 = new QuantityWeight(value2, unit2);

        System.out.println("Are Equal? -> " + w1.equals(w2));

        QuantityWeight sum = w1.add(w2);
        System.out.println("Sum (in first unit) -> " + sum);

        System.out.println("Convert first weight to GRAM:");
        System.out.println(w1.convertTo(WeightUnit.GRAM));

        sc.close();
    }
}