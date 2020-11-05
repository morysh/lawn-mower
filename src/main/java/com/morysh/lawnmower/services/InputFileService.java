package com.morysh.lawnmower.services;

import com.morysh.lawnmower.core.simulation.Simulation;

public interface InputFileService {
    public Simulation createSimulationFromFile(String filePath) throws FileProcessingException;
}
