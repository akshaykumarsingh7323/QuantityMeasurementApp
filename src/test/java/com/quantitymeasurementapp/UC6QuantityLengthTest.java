package com.quantitymeasurementapp;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC6QuantityLengthTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(2, LengthUnit.FEET);

        assertEquals(new QuantityLength(3, LengthUnit.FEET), a.add(b));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12, LengthUnit.INCHES);

        assertEquals(new QuantityLength(2, LengthUnit.FEET), a.add(b));
    }

    @Test
    void testAddition_WithZero() {
        QuantityLength a = new QuantityLength(5, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(5, LengthUnit.FEET), a.add(b));
    }

    @Test
    void testAddition_NullSecondOperand() {
        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> a.add(null));
    }
}