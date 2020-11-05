package com.morysh.lawnmower.services;

import com.morysh.lawnmower.core.simulation.Simulation;

public interface SimulationService {
    /**
     * Creates a {@link Simulation} from the given file
     *
     * @param filePath path to the file to read
     * @return an initialized {@link Simulation}
     * @throws FileProcessingException if there is an problem with the file
     */
    Simulation createFromFile(String filePath) throws FileProcessingException;

    /**
     * Runs the given {@link Simulation} and returns once it is finished. The result can be read from {@link Simulation#getResult()}
     *
     * @param simulation the simulation to run
     */
    void run(Simulation simulation);
}
