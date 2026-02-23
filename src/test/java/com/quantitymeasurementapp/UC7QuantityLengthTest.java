package com.quantitymeasurementapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC7QuantityLengthTest {

    private static final double EPSILON = 0.001;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCHES);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

   @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        QuantityLength a = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(new QuantityLength(3.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        QuantityLength a = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(new QuantityLength(9.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result1 =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        QuantityLength result2 =
                QuantityLength.add(b, a, LengthUnit.YARDS);

        assertEquals(result1, result2);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.add(a, b, null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        QuantityLength a = new QuantityLength(1000.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(500.0, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        QuantityLength a = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

        QuantityLength a = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.002, LengthUnit.FEET);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(0.003, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength yards = new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength r1 = QuantityLength.add(feet, inches, LengthUnit.FEET);
        QuantityLength r2 = QuantityLength.add(yards, feet, LengthUnit.YARDS);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), r1);
        assertEquals(new QuantityLength(1.3333, LengthUnit.YARDS), r2);
    }
}