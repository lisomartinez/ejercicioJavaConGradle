package com.martinez.lisandro.solar.aligners;

import com.martinez.lisandro.solar.Aligner;
import com.martinez.lisandro.solar.Planet;

import java.util.HashSet;
import java.util.Set;

public class CollinearAligner implements Aligner {
    private final Coordinate sun;
    private final Set<Double> slopes;


    public CollinearAligner(Coordinate sun) {
        this.sun = sun;
        this.slopes = new HashSet<>();
    }

    @Override
    public void addToLine(Planet planet) {
        double slope = getSlopeOfLineFromPlanetToSun(planet);
        slopes.add(slope);
    }

    private double getSlopeOfLineFromPlanetToSun(Planet planet) {
        double radians = Math.toRadians(planet.getDegrees());
        int x = Math.round((float) (sun.getX() + Math.cos(radians) * planet.getDistanceFromSun()));
        int y = Math.round((float) (sun.getY() + Math.sin(radians) * planet.getDistanceFromSun()));

        if (x == 0) return Double.POSITIVE_INFINITY;

        double slope = (double) (y - sun.getY()) / (x - sun.getX());
        return Math.round(slope * 1000.0) / 1000.0;
    }

    @Override
    public void clearLine() {
        slopes.clear();
    }


    @Override
    public boolean arePlanetsAligned() {
        return slopes.size() <= 1;
    }
}
