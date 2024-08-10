package com.example.mysolutions;

public class Compound {
    private String name;
    private double molecularMass;
    private double equivalentMass;

    public Compound(String name, double molecularMass, double equivalentMass) {
        this.name = name;
        this.molecularMass = molecularMass;
        this.equivalentMass = equivalentMass;
    }

    public String getName() {
        return name;
    }

    public double getMolecularMass() {
        return molecularMass;
    }

    public double getEquivalentMass() {
        return equivalentMass;
    }

    @Override
    public String toString() {
        return name;
    }
}
