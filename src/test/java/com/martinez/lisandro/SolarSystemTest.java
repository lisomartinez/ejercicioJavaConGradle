package com.martinez.lisandro;

import com.martinez.lisandro.solar.Coordinate;
import com.martinez.lisandro.solar.Planet;
import com.martinez.lisandro.solar.RotationStrategy;
import com.martinez.lisandro.solar.SolarSystem;
import com.martinez.lisandro.solar.aligners.CollinearAligner;
import com.martinez.lisandro.solar.rotations.ClockWiseRotationStrategy;
import com.martinez.lisandro.solar.rotations.CounterClockWiseRotationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolarSystemTest {

    static final int FIRST_DAY = 0;
    static final int START_POSITION = 0;
    static final int ALPHA_DISTANCE = 500;
    static final int BETA_DISTANCE = 2000;
    static final int GAMMA_DISTANCE = 1000;
    static final int ALPHA_SPEED = 1;
    static final int BETA_SPEED = 3;
    static final int GAMMA_SPEED = 5;
    private SolarSystem solarSystem;

    @BeforeEach
    void setUp() {
        solarSystem = new SolarSystem(getPLanets(), new CollinearAligner(new Coordinate(0, 0)));
    }

    @Test
    void simulateDay() {
        solarSystem.simulateDay();
        assertThat(solarSystem.arePlanetsAligned()).isFalse();
    }

    @Test
    void arePlanetsAligned() {
    }


    private List<Planet> getPLanets() {
        RotationStrategy clockwiseRotationStrategy = new ClockWiseRotationStrategy();
        RotationStrategy counterClockwiseRotationStrategy = new CounterClockWiseRotationStrategy();

        Planet alpha = new Planet(ALPHA_SPEED, START_POSITION, ALPHA_DISTANCE, clockwiseRotationStrategy);
        Planet beta = new Planet(BETA_SPEED, START_POSITION, BETA_DISTANCE, clockwiseRotationStrategy);
        Planet gamma = new Planet(GAMMA_SPEED, START_POSITION, GAMMA_DISTANCE, counterClockwiseRotationStrategy);
        return Arrays.asList(alpha, beta, gamma);
    }

    private Coordinate getSun() {
        return new Coordinate(0, 0);
    }
}