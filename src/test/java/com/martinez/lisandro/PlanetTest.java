package com.martinez.lisandro;

import com.martinez.lisandro.solar.Planet;
import com.martinez.lisandro.solar.rotations.ClockWiseRotationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlanetTest {

    private Planet planet;

    static Stream<Arguments> positionsAndDegrees() {
        return Stream.of(
                Arguments.of(0, 359),
                Arguments.of(1, 0),
                Arguments.of(1, 0),
                Arguments.of(0, 359),
                Arguments.of(0, 359),
                Arguments.of(0, 359)
        );
    }

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @MethodSource("positionsAndDegrees")
    void planet_rotateClockWise_ShouldModifyDegree(int position, int expected) {
        planet = new Planet(1, position, 500, new ClockWiseRotationStrategy());
        planet.rotate();
        assertThat(planet.getDegrees()).isEqualTo(expected);
    }


    @Test
    void planet_InvalidSpeed_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Planet(0, 0, 500, new ClockWiseRotationStrategy()));
    }


    @Test
    void planet_InvalidPosition_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Planet(0, -1, 500, new ClockWiseRotationStrategy()));
    }


}