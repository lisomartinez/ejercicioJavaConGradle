package com.martinez.lisandro.solar.aligners;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {
    @Test
    @DisplayName("moveTo change the values of the X and Y axis")
    void moveTo_XandYvalues_changeInstanceVariables() {
        Coordinate coordinate = new Coordinate(0, 0);
        coordinate.moveTo(10, 10);
        assertThat(coordinate.getX()).isEqualTo(coordinate.getX());
        assertThat(coordinate.getY()).isEqualTo(coordinate.getY());
    }
}