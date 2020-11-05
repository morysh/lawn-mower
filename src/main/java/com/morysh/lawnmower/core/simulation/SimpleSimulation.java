package com.morysh.lawnmower.core.simulation;

import com.morysh.lawnmower.core.mower.Mower;
import com.morysh.lawnmower.core.mower.simple.RectangularLawn;

import java.util.List;

public class SimpleSimulation implements Simulation {
    private RectangularLawn lawn;
    private List<Mower> mowers;
    private StringBuilder result;

    public SimpleSimulation(RectangularLawn lawn, List<Mower> mowers) {
        this.lawn = lawn;
        this.mowers = mowers;
        result = new StringBuilder();
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    public StringBuilder getResult() {
        return result;
    }

    public RectangularLawn getLawn() {
        return lawn;
    }
}
