package com.martinez.lisandro.solar;

import com.martinez.lisandro.solar.aligners.CollinearAligner;
import com.martinez.lisandro.solar.aligners.Coordinate;
import com.martinez.lisandro.solar.rotators.ClockWiseRotationStrategy;
import com.martinez.lisandro.solar.rotators.CounterClockWiseRotationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolarSystemTest {

    private static final int START_POSITION = 0;
    private static final int ALPHA_DISTANCE = 500;
    private static final int BETA_DISTANCE = 2000;
    private static final int GAMMA_DISTANCE = 1000;
    private static final int ALPHA_SPEED = 1;
    private static final int BETA_SPEED = 3;
    private static final int GAMMA_SPEED = 5;

    private SolarSystem solarSystem;

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

    @BeforeEach
    void setUp() {
        solarSystem = makeDefaultSolarSystem();
    }

    @Test
    @DisplayName("Calculate alignments in 1 day should return 0")
    void calculateAlignments_OneDay_ShouldReturnZero() {
        int result = solarSystem.calculateAlignments(1);
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Calculate alignments in 90 day should return 1")
    void calculateAlignments_NinetyDay_ShouldReturnOne() {
        int result = solarSystem.calculateAlignments(90);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Calculate alignments in 180 day should return 2")
    void calculateAlignments_180Days_ShouldReturnThree() {
        int result = solarSystem.calculateAlignments(180);
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Calculate alignments in 270 day should return 3")
    void calculateAlignments_270Days_ShouldReturnFour() {
        int result = solarSystem.calculateAlignments(270);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Calculate alignments in 720 day should return 8")
    void calculateAlignments_720Days_ShouldReturnEight() {
        int result = solarSystem.calculateAlignments(720);
        assertThat(result).isEqualTo(8);
    }
}