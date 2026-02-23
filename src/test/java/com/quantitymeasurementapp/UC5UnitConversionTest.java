package com.quantitymeasurementapp;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityLength.convert(1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityLength.convert(24.0,
                LengthUnit.INCHES,
                LengthUnit.FEET);

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityLength.convert(1.0,
                LengthUnit.YARDS,
                LengthUnit.INCHES);

        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityLength.convert(2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES);

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityLength.convert(0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityLength.convert(-1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testEquality() {
        QuantityLength l1 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength l2 =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testInvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(1.0,
                        null,
                        LengthUnit.FEET));
    }

    @Test
    void testInvalidValue_NaN_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    @Test
    void testRoundTripConversion() {

        double original = 5.0;

        double converted =
                QuantityLength.convert(original,
                        LengthUnit.FEET,
                        LengthUnit.YARDS);

        double back =
                QuantityLength.convert(converted,
                        LengthUnit.YARDS,
                        LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }
}