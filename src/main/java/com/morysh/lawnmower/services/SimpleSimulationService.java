package com.morysh.lawnmower.services;

import com.morysh.lawnmower.core.mower.Mower;
import com.morysh.lawnmower.core.simulation.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SimpleSimulationService implements SimulationService {
    public static final Logger LOG = LoggerFactory.getLogger(SimpleSimulationService.class);

    private InputFileService inputFileService = new PlainTextInputFileService();

    @Override
    public Simulation createFromFile(String filePath) throws FileProcessingException {
        return inputFileService.createSimulationFromFile(filePath);
    }

    @Override
    public void run(Simulation simulation) {
        List<Thread> threads = new ArrayList<>(simulation.getMowers().size());
        for (Mower mower : simulation.getMowers()) {
            Thread thread = new Thread(mower);
            threads.add(thread);
            thread.start();
        }
        for (int i = 0; i < threads.size(); i++) {
            try {
                // Wait completion of all threads
                threads.get(i).join();
                simulation.getResult()
                        .append(simulation.getMowers().get(i).getPosition().getX())
                        .append(" ")
                        .append(simulation.getMowers().get(i).getPosition().getY())
                        .append(" ")
                        .append(simulation.getMowers().get(i).getOrientation().toString())
                        .append("\n");
            } catch (InterruptedException e) {
                simulation.getResult().append("Simulation interrupted\n");
                LOG.error("Simulation interrupted", e);
                return;
            }
        }
        // Remove last line break
        simulation.getResult().setLength(simulation.getResult().length() - 1);
    }

    void setInputFileService(InputFileService inputFileService) {
        this.inputFileService = inputFileService;
    }
}
