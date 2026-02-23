package com.quantitymeasurementapp;

import java.util.Objects;

public final class QuantityLength {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public QuantityLength add(QuantityLength other) {
        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        double thisInFeet = unit.toFeet(this.value);
        double otherInFeet = other.unit.toFeet(other.value);

        double sumFeet = thisInFeet + otherInFeet;

        double result = unit.fromFeet(sumFeet);

        return new QuantityLength(result, this.unit);
    }

    public static QuantityLength add(QuantityLength a,
                                     QuantityLength b,
                                     LengthUnit targetUnit) {

        if (a == null || b == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid arguments");

        double aFeet = a.unit.toFeet(a.value);
        double bFeet = b.unit.toFeet(b.value);

        double sumFeet = aFeet + bFeet;

        double result = targetUnit.fromFeet(sumFeet);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisFeet = unit.toFeet(this.value);
        double otherFeet = other.unit.toFeet(other.value);

        return Math.abs(thisFeet - otherFeet) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.toFeet(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}