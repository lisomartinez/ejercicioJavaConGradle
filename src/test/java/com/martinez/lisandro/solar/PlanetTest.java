package com.martinez.lisandro.solar;

import com.martinez.lisandro.solar.rotators.ClockWiseRotationStrategy;
import com.martinez.lisandro.solar.rotators.CounterClockWiseRotationStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlanetTest {

    static Stream<Arguments> positionsAndDegreesClockWise() {
        return Stream.of(
                Arguments.of(0, 359),
                Arguments.of(1, 0),
                Arguments.of(1, 0),
                Arguments.of(0, 359),
                Arguments.of(0, 359),
                Arguments.of(0, 359)
        );
    }

    static Stream<Arguments> positionsAndDegreesCounterClockWise() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 2),
                Arguments.of(359, 360),
                Arguments.of(360, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("positionsAndDegreesClockWise")
    @DisplayName("Planet Clockwise rotation should modify its degrees")
    void planet_rotateClockWise_ShouldModifyDegree(int position, int expected) {
        Planet planet = new Planet(1, position, 500, new ClockWiseRotationStrategy());
        planet.rotate();
        assertThat(planet.getDegrees()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("positionsAndDegreesCounterClockWise")
    @DisplayName("Planet CounterClockwise rotation should modify its degrees")
    void planet_rotateCounterClockWise_ShouldModifyDegree(int position, int expected) {
        Planet planet = new Planet(1, position, 500, new CounterClockWiseRotationStrategy());
        planet.rotate();
        assertThat(planet.getDegrees()).isEqualTo(expected);
    }


    @Test
    @DisplayName("Planet with Speed less than one should throw IllegalArgumentException")
    void planet_InvalidSpeed_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Planet(0, 0, 500, new ClockWiseRotationStrategy()));
    }

    @Test
    @DisplayName("Planet with negative degrees should throw IllegalArgumentException")
    void planet_NegativeDegrees_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Planet(0, -1, 500, new ClockWiseRotationStrategy()));
    }

    @Test
    @DisplayName("Planet with Degrees greater than 360 one should throw IllegalArgumentException")
    void planet_DegreesGreaterThan360_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Planet(0, 370, 500, new ClockWiseRotationStrategy()));
    }

    @Test
    @DisplayName("Planet with RotationStrategy NULL should throw IllegalArgumentException")
    void planet_RotationStrategyNull_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Planet(0, 0, 500, null));
    }
}