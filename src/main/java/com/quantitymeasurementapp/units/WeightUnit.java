package com.quantitymeasurementapp.units;

public enum WeightUnit implements IMeasurable {
	
	KILOGRAM(1000.0),
    GRAM(1.0),          
    POUND(453.59237);
	
    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
    
    @Override
    public String getUnitName() {
        return name();
    }
}