package com.martinez.lisandro.solar;

public interface Aligner {
    void addToLine(Planet planet);

    void clearLine();

    boolean arePlanetsAligned();
}
