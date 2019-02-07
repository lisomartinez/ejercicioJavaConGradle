package com.martinez.lisandro.solar;

import java.util.List;

public class SolarSystem {

    private static final int FIRST_DAY = 0;

    private List<Planet> planets;
    private Aligner aligner;

    public SolarSystem(List<Planet> planets, Aligner aligner) {
        this.planets = planets;
        this.aligner = aligner;
    }


    public int calculateAlignments(int days) {
        int total = 0;
        for (int i = FIRST_DAY; i < days; i++) {
            if (arePlanetsAligned()) {
                total++;
            }
        }
        return total;
    }

    private boolean arePlanetsAligned() {
        for (Planet planet : planets) {
            planet.rotate();
            aligner.addToLine(planet);
        }
        boolean result = aligner.arePlanetsAligned();
        aligner.clearLine();
        return result;
    }

}
