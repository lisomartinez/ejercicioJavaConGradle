package com.martinez.lisandro;

import com.martinez.lisandro.solar.Aligner;
import com.martinez.lisandro.solar.Planet;
import com.martinez.lisandro.solar.RotationStrategy;
import com.martinez.lisandro.solar.SolarSystem;
import com.martinez.lisandro.solar.aligners.CollinearAligner;
import com.martinez.lisandro.solar.aligners.Coordinate;
import com.martinez.lisandro.solar.rotators.ClockWiseRotationStrategy;
import com.martinez.lisandro.solar.rotators.CounterClockWiseRotationStrategy;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static final int START_POSITION = 0;

    public static final int ALPHA_DISTANCE = 500;
    public static final int BETA_DISTANCE = 2000;
    public static final int GAMMA_DISTANCE = 1000;

    public static final int ALPHA_SPEED = 1;
    public static final int BETA_SPEED = 3;
    public static final int GAMMA_SPEED = 5;


    public static void main(String[] args) {
        SolarSystem solarSystem = makeDefaultSolarSystem();

        int days = 0;
        if (args.length > 0) {
            try {
                days = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Number");
                System.exit(1);
            }
        } else {
            System.out.println("The number of days was not entered");
            System.exit(0);
        }
        int total = solarSystem.calculateAlignments(days);

        String message = makeMessage(days, total);

        System.out.println(message);

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

    private static String makeMessage(int days, int total) {
        StringBuilder sb = new StringBuilder();
        sb.append("The total number of times planets and Sun are aligned in ");
        sb.append(days);
        sb.append(" day(s) is = ");
        sb.append(total);

        return sb.toString();
    }

}
