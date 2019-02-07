package com.martinez.lisandro.solar.rotators;

import com.martinez.lisandro.solar.RotationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
                Arguments.of(360, 1, 1),
                Arguments.of(360, 3, 3),
                Arguments.of(355, 5, 360)
        );
    }

    @BeforeEach
    void setUp() {
        rotationStrategy = new CounterClockWiseRotationStrategy();
    }

    @ParameterizedTest
    @DisplayName("Rotate planet X degrees from Y position")
    @MethodSource("positionsAndDegrees")
    void rotate(int position, int degrees, int expected) {
        int newPosition = rotationStrategy.rotate(position, degrees);
        assertThat(newPosition).isEqualTo(expected);
    }

    @Test
    @DisplayName("Rotate constructor with negative position throws IllegalArgumentException")
    void rotate_WithNegativePosition_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> rotationStrategy.rotate(-9, 1));
    }

    @Test
    @DisplayName("Rotate constructor with position greater than 360 throws IllegalArgumentException")
    void rotate_WithPositionGreaterThan360_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> rotationStrategy.rotate(370, 1));
    }

    @Test
    @DisplayName("Rotate constructor with negative degrees throws IllegalArgumentException")
    void rotate_WithNegativeDegrees_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> rotationStrategy.rotate(350, -1));
    }
}