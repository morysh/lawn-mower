package com.morysh.lawnmower.core.simulation;

import com.morysh.lawnmower.core.mower.Mower;

import java.util.List;

public interface Simulation {
    List<Mower> getMowers();

    StringBuilder getResult();
}
