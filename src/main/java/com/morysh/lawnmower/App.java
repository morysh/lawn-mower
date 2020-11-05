package com.morysh.lawnmower;

import com.morysh.lawnmower.core.simulation.Simulation;
import com.morysh.lawnmower.services.FileProcessingException;
import com.morysh.lawnmower.services.SimpleSimulationService;
import com.morysh.lawnmower.services.SimulationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            LOG.error("No argument provided. A file path is needed to proceed");
            System.exit(1);
        }
        if (args[0] == "-h" || args[0] == "--help") {
            System.out.println("This program expects as a parameter the path to file containing the data to initialize the simulation");
            System.exit(0);
        }

        SimulationService simulationService = new SimpleSimulationService();
        try {
            Simulation simulation = simulationService.createFromFile(args[0]);
            simulationService.run(simulation);
            System.out.println(simulation.getResult().toString());
        } catch (FileProcessingException e) {
            LOG.error("Error while processing the file", e);
        }
    }
}
