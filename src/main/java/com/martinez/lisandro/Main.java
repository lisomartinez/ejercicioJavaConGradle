package com.martinez.lisandro;

import com.martinez.lisandro.solar.*;
import com.martinez.lisandro.solar.aligners.CollinearAligner;
import com.martinez.lisandro.solar.rotations.ClockWiseRotationStrategy;
import com.martinez.lisandro.solar.rotations.CounterClockWiseRotationStrategy;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static final int DEFAULT_DAYS = 365;

    public static final int START_POSITION = 0;

    public static final int ALPHA_DISTANCE = 500;
    public static final int BETA_DISTANCE = 2000;
    public static final int GAMMA_DISTANCE = 1000;

    public static final int ALPHA_SPEED = 1;
    public static final int BETA_SPEED = 3;
    public static final int GAMMA_SPEED = 5;


    public static void main(String[] args) {
        SolarSystem solarSystem = makeDefaultSolarSystem();

        int days = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_DAYS;

        int total = solarSystem.calculateAlignments(days);

        StringBuilder sb = new StringBuilder();
        sb.append("The total number of times all planets and Sun are aligned in ");
        sb.append(days);
        sb.append(" day(s) is = ");
        sb.append(total);

        System.out.println(sb.toString());

    }

    public static SolarSystem makeDefaultSolarSystem() {
        List<Planet> planets = createPlanets();
        Aligner aligner = createAligner();
        return new SolarSystem(planets, aligner);
    }

    private static List<Planet> createPlanets() {
        RotationStrategy clockwiseRotationStrategy = new ClockWiseRotationStrategy();
        RotationStrategy counterClockwiseRotationStrategy = new CounterClockWiseRotationStrategy();

        Planet alpha = new Planet(ALPHA_SPEED, START_POSITION, ALPHA_DISTANCE, clockwiseRotationStrategy);
        Planet beta = new Planet(BETA_SPEED, START_POSITION, BETA_DISTANCE, clockwiseRotationStrategy);
        Planet gamma = new Planet(GAMMA_SPEED, START_POSITION, GAMMA_DISTANCE, counterClockwiseRotationStrategy);
        return Arrays.asList(alpha, beta, gamma);
    }

    private static Aligner createAligner() {
        Coordinate sun = new Coordinate(0, 0);
        return new CollinearAligner(sun);
    }

}
