package com.martinez.lisandro;

import com.martinez.lisandro.solar.Coordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {
    @Test
    void moveTo() {
        Coordinate coordinate = new Coordinate(0, 0);
        coordinate.moveTo(10, 10);
        assertThat(coordinate.getX()).isEqualTo(coordinate.getX());
        assertThat(coordinate.getY()).isEqualTo(coordinate.getY());
    }
}