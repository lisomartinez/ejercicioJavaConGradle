package com.martinez.lisandro.solar;


public class Planet {
    private final int speed;
    private final int distanceFromSun;
    private final RotationStrategy rotationStrategy;
    private int degrees;

    public Planet(int speed, int degrees, int distanceFromSun, RotationStrategy rotationStrategy) {
        if (speed < 1) {
            throw new IllegalArgumentException("Speed should be positive");
        }
        if (degrees < 0 || degrees > 360) {
            throw new IllegalArgumentException("Degree should be greater than 0 and less than or equal to 360");
        }
        if (distanceFromSun < 0) {
            throw new IllegalArgumentException("Distance should be positive.");
        }
        if (rotationStrategy == null) {
            throw new IllegalArgumentException("RotationStrategy cannot be null");
        }

        this.speed = speed;
        this.degrees = degrees;
        this.distanceFromSun = distanceFromSun;
        this.rotationStrategy = rotationStrategy;
    }

    public void rotate() {
        degrees = rotationStrategy.rotate(degrees, speed);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planet planet = (Planet) o;

        if (speed != planet.speed) return false;
        if (getDistanceFromSun() != planet.getDistanceFromSun()) return false;
        return getDegrees() == planet.getDegrees();
    }

    @Override
    public int hashCode() {
        int result = speed;
        result = 31 * result + getDistanceFromSun();
        result = 31 * result + getDegrees();
        return result;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "speed=" + speed +
                ", distanceFromSun=" + distanceFromSun +
                ", rotationStrategy=" + rotationStrategy +
                ", degrees=" + degrees +
                '}';
    }

    public int getDegrees() {
        return degrees;
    }

    public int getDistanceFromSun() {
        return distanceFromSun;
    }
}
