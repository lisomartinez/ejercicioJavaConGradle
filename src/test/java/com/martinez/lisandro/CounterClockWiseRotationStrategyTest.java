package com.martinez.lisandro;

import com.martinez.lisandro.solar.RotationStrategy;
import com.martinez.lisandro.solar.rotations.CounterClockWiseRotationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CounterClockWiseRotationStrategyTest {

    private RotationStrategy rotationStrategy;

    static Stream<Arguments> positionsAndDegrees() {
        return Stream.of(
                Arguments.of(0, 1, 1),
                Arguments.of(1, 1, 2),
                Arguments.of(359, 1, 360),
                Arguments.of(360, 1, 1)
        );
    }

    @BeforeEach
    void setUp() {
        rotationStrategy = new CounterClockWiseRotationStrategy();
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
        assertThrows(IllegalArgumentException.class, () -> rotationStrategy.rotate(350, -1));
    }

    @ParameterizedTest
    @MethodSource("positionsAndDegrees")
    void rotate(int position, int degrees, int expected) {
        int newPosition = rotationStrategy.rotate(position, degrees);
        assertThat(newPosition).isEqualTo(expected);
    }
}