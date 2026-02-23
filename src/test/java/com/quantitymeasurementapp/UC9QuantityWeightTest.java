package com.quantitymeasurementapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UC9QuantityWeightTest {

    private static final double EPSILON = 0.000001;


    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        assertTrue(new QuantityWeight(1000.0, WeightUnit.GRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(2.20462, WeightUnit.POUND)));
    }

    @Test
    void testEquality_GramToPound_EquivalentValue() {
        assertTrue(new QuantityWeight(453.592, WeightUnit.GRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.POUND)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertTrue(w.equals(w));
    }

    @Test
    void testEquality_ZeroValue() {
        assertTrue(new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_NegativeWeight() {
        assertTrue(new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_LargeWeightValue() {
        assertTrue(new QuantityWeight(1000000.0, WeightUnit.GRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_SmallWeightValue() {
        assertTrue(new QuantityWeight(0.001, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1.0, WeightUnit.GRAM)));
    }

    @Test
    void testEquality_TransitiveProperty() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight c = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }


    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight result =
                new QuantityWeight(2.20462, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    void testConversion_KilogramToPound() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), 1e-3);
    }

    @Test
    void testConversion_SameUnit() {
        QuantityWeight result =
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        QuantityWeight result =
                new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        QuantityWeight result =
                new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        QuantityWeight original =
                new QuantityWeight(1.5, WeightUnit.KILOGRAM);

        QuantityWeight result =
                original.convertTo(WeightUnit.GRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), EPSILON);
    }


    @Test
    void testAddition_SameUnit() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM),
                                WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        QuantityWeight a =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    @Test
    void testAddition_WithZero() {
        QuantityWeight result =
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(0.0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityWeight result =
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_LargeValues() {
        QuantityWeight result =
                new QuantityWeight(1e6, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1e6, WeightUnit.KILOGRAM));

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void testHashCodeConsistency() {
        QuantityWeight a =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }
}