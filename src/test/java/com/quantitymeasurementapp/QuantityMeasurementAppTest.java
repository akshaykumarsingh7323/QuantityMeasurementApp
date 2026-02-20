package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
    }

    @Test
    void testEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    void testEquality_NullComparison() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null), "Value should not be equal to null");
    }

    @Test
    void testEquality_SameReference() {
        Feet f1 = new Feet(1.0);

        assertTrue(f1.equals(f1), "Object should be equal to itself");
    }

    @Test
    void testEquality_NonNumericInput() {
        Feet f1 = new Feet(1.0);
        String nonNumeric = "abc";

        assertFalse(f1.equals(nonNumeric), "Feet should not equal non-numeric object");
    }
}