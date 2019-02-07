package com.martinez.lisandro;

import com.martinez.lisandro.solar.RotationStrategy;
import com.martinez.lisandro.solar.rotations.ClockWiseRotationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClockWiseRotationStrategyTest {

    private RotationStrategy rotationStrategy;

    static Stream<Arguments> positionsAndDegrees() {
        return Stream.of(
                Arguments.of(0, 1, 359),
                Arguments.of(1, 1, 0),
                Arguments.of(360, 1, 359),
                Arguments.of(315, 1, 314),
                Arguments.of(10, 5, 5)
        );
    }


    @BeforeEach
    void setUp() {
        rotationStrategy = new ClockWiseRotationStrategy();
    }

    @ParameterizedTest
    @MethodSource("positionsAndDegrees")
    void rotate(int position, int degrees, int expected) {
        int newPosition = rotationStrategy.rotate(position, degrees);
        assertThat(newPosition).isEqualTo(expected);
    }

    @Test
    void rotate_WithNegativePosition_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> rotationStrategy.rotate(-9, 1));
    }

    @Test
    void rotate_WithPositionGreaterThan360_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> rotationStrategy.rotate(370, 1));
    }


    @Test
    void rotate_WithNegativeDegrees_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> rotationStrategy.rotate(360, -1));
    }
}