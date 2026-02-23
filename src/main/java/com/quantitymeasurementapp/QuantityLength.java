package com.quantitymeasurementapp;

public final class QuantityLength {

    private static final double EPSILON = 0.001;

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

    // UC6: implicit target unit (first operand unit)
    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    // UC7: explicit target unit
    public static QuantityLength add(QuantityLength a,
                                     QuantityLength b,
                                     LengthUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double aFeet = a.unit.toFeet(a.value);
        double bFeet = b.unit.toFeet(b.value);

        double sumFeet = aFeet + bFeet;

        double result = targetUnit.fromFeet(sumFeet);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisFeet = this.unit.toFeet(this.value);
        double otherFeet = other.unit.toFeet(other.value);

        return Math.abs(thisFeet - otherFeet) < EPSILON;
    }

    @Override
    public int hashCode() {
        return 1;   // acceptable since equals uses epsilon
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}