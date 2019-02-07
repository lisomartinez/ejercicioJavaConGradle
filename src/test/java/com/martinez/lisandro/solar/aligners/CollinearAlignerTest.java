package com.martinez.lisandro.solar.aligners;

import com.martinez.lisandro.solar.Planet;
import com.martinez.lisandro.solar.rotators.ClockWiseRotationStrategy;
import com.martinez.lisandro.solar.rotators.CounterClockWiseRotationStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CollinearAlignerTest {

    private CollinearAligner al;

    static Stream<Arguments> colinearPlanetsTrue() {
        return Stream.of(
                Arguments.of(new int[]{0, 0, 0}, true),
                Arguments.of(new int[]{0, 0, 360}, true),
                Arguments.of(new int[]{0, 360, 360}, true),
                Arguments.of(new int[]{360, 0, 360}, true),
                Arguments.of(new int[]{360, 360, 0}, true),
                Arguments.of(new int[]{360, 360, 360}, true),

                Arguments.of(new int[]{45, 45, 45}, true),
                Arguments.of(new int[]{45, 45, 225}, true),
                Arguments.of(new int[]{45, 225, 225}, true),
                Arguments.of(new int[]{225, 45, 225}, true),
                Arguments.of(new int[]{225, 225, 45}, true),
                Arguments.of(new int[]{225, 225, 225}, true),

                Arguments.of(new int[]{90, 90, 90}, true),
                Arguments.of(new int[]{90, 90, 270}, true),
                Arguments.of(new int[]{90, 270, 270}, true),
                Arguments.of(new int[]{270, 90, 270}, true),
                Arguments.of(new int[]{270, 270, 90}, true),
                Arguments.of(new int[]{270, 270, 270}, true),

                Arguments.of(new int[]{135, 135, 135}, true),
                Arguments.of(new int[]{135, 135, 315}, true),
                Arguments.of(new int[]{135, 315, 315}, true),

                Arguments.of(new int[]{315, 135, 315}, true),
                Arguments.of(new int[]{315, 315, 135}, true),
                Arguments.of(new int[]{315, 315, 315}, true),

                Arguments.of(new int[]{180, 180, 180}, true),
                Arguments.of(new int[]{180, 180, 360}, true),
                Arguments.of(new int[]{180, 360, 360}, true),
                Arguments.of(new int[]{360, 180, 180}, true),
                Arguments.of(new int[]{360, 360, 180}, true),
                Arguments.of(new int[]{360, 360, 360}, true)
        );
    }


    static Stream<Arguments> colinearPlanetsFalse() {
        return Stream.of(
                Arguments.of(new int[]{2, 6, 10}, false),
                Arguments.of(new int[]{4, 12, 20}, false),
                Arguments.of(new int[]{8, 24, 40}, false),
                Arguments.of(new int[]{12, 48, 80}, false),
                Arguments.of(new int[]{1, 1, 1}, false),
                Arguments.of(new int[]{2, 2, 2}, false),
                Arguments.of(new int[]{10, 10, 10}, false),
                Arguments.of(new int[]{315, 225, 225}, false),
                Arguments.of(new int[]{315, 45, 45}, false)
        );
    }

    @BeforeEach
    void setUp() {
        Coordinate sun = new Coordinate(0, 0);
        al = new CollinearAligner(sun);
    }


    @ParameterizedTest
    @MethodSource("colinearPlanetsTrue")
    void collinearTrue(int[] positions, boolean expected) {
        Planet alpha = new Planet(1, positions[0], 500, new ClockWiseRotationStrategy());
        Planet beta = new Planet(3, positions[1], 2000, new ClockWiseRotationStrategy());
        Planet gamma = new Planet(5, positions[2], 1000, new CounterClockWiseRotationStrategy());

        al.addToLine(alpha);
        al.addToLine(beta);
        al.addToLine(gamma);

        boolean result = al.arePlanetsAligned();

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("colinearPlanetsFalse")
    void collinearFalse(int[] positions, boolean expected) {
        Planet alpha = new Planet(1, positions[0], 500, new ClockWiseRotationStrategy());
        Planet beta = new Planet(3, positions[1], 2000, new ClockWiseRotationStrategy());
        Planet gamma = new Planet(5, positions[2], 1000, new CounterClockWiseRotationStrategy());

        al.addToLine(alpha);
        al.addToLine(beta);
        al.addToLine(gamma);

        boolean result = al.arePlanetsAligned();

        assertThat(result).isEqualTo(expected);
    }


    @AfterEach
    void tearDown() {
        al.clearLine();
    }
}