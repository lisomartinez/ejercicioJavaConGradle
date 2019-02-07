package com.martinez.lisandro.solar;

import java.util.List;

public class SolarSystem {

    private static final int FIRST_DAY = 0;

    private List<Planet> planets;
    private boolean planetsAligned;
    private Aligner aligner;

    public SolarSystem(List<Planet> planets, Aligner aligner) {
        this.planets = planets;
        this.aligner = aligner;
    }


    public int calculateAlignments(int days) {
        int total = 0;
        for (int i = FIRST_DAY; i < days; i++) {
            simulateDay();
            if (arePlanetsAligned()) {
                total++;
            }
        }
        return total;
    }

    public void simulateDay() {
        for (Planet planet : planets) {
            planet.rotate();
            aligner.load(planet);
        }
        planetsAligned = aligner.arePlanetsAligned();
        aligner.clear();
    }


    public boolean arePlanetsAligned() {
        return planetsAligned;
    }
}
