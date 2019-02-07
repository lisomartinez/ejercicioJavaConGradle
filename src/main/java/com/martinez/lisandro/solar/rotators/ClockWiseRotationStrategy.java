package com.martinez.lisandro.solar.rotators;

import com.martinez.lisandro.solar.RotationStrategy;

public class ClockWiseRotationStrategy implements RotationStrategy {
    @Override
    public int rotate(int position, int degrees) {

        if (position < 0 || position > 360) {
            throw new IllegalArgumentException("Position should be greater than 0 and less than or equal to 360.");
        }

        if (degrees < 1) {
            throw new IllegalArgumentException("Degrees should be positive.");
        }

        if (position == 0) {
            position = 360;
        }
        return position - degrees;
    }


}
