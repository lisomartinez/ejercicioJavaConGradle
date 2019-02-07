package com.martinez.lisandro.solar;

public interface Aligner {
    void load(Planet planet);

    void clear();

    boolean arePlanetsAligned();
}
