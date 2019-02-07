package com.martinez.lisandro;

import com.martinez.lisandro.solar.Coordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SunTest {

    @Test
    void CreateSun_Location_ShouldCreateInstanceWithLocation() {
        Coordinate center = new Coordinate(10, 1);
        Coordinate sun = new Coordinate(10, 1);

        assertThat(sun.equals(center)).isTrue();
        assertThat(center.equals(sun)).isTrue();

    }
}
