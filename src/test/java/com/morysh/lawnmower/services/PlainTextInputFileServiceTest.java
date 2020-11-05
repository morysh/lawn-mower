package com.morysh.lawnmower.services;

import com.morysh.lawnmower.core.mower.simple.SimpleCardinalOrientation;
import com.morysh.lawnmower.core.mower.simple.SimpleInstruction;
import com.morysh.lawnmower.core.mower.simple.SimpleMower;
import com.morysh.lawnmower.core.simulation.SimpleSimulation;
import com.morysh.lawnmower.core.simulation.Simulation;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlainTextInputFileServiceTest {
    @Test
    public void createSimulationFromFileTest() throws FileProcessingException {
        // GIVEN
        InputFileService inputFileService = new PlainTextInputFileService();
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("simple-test.txt")).getPath();

        // WHEN
        Simulation simulation = inputFileService.createSimulationFromFile(filePath);

        // THEN
        assertTrue(simulation instanceof SimpleSimulation);
        assertEquals(6, ((SimpleSimulation) simulation).getLawn().getWidth());
        assertEquals(6, ((SimpleSimulation) simulation).getLawn().getHeight());
        assertEquals(2, ((SimpleSimulation) simulation).getMowers().size());

        assertTrue(((SimpleSimulation) simulation).getMowers().get(0) instanceof SimpleMower);
        assertTrue(((SimpleSimulation) simulation).getMowers().get(1) instanceof SimpleMower);

        assertEquals(1, ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(0)).getPosition().getX());
        assertEquals(2, ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(0)).getPosition().getY());
        assertEquals(SimpleCardinalOrientation.N, ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(0)).getOrientation());
        // LFLFLFLFF
        assertEquals(new ArrayDeque<>(
                        Arrays.asList(SimpleInstruction.L, SimpleInstruction.F, SimpleInstruction.L, SimpleInstruction.F, SimpleInstruction.L, SimpleInstruction.F,
                                SimpleInstruction.L, SimpleInstruction.F, SimpleInstruction.F)).toString(),
                ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(0)).getInstructions().toString());

        assertEquals(3, ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(1)).getPosition().getX());
        assertEquals(3, ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(1)).getPosition().getY());
        assertEquals(SimpleCardinalOrientation.E, ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(1)).getOrientation());
        // FFRFFRFRRF
        assertEquals(new ArrayDeque<>(
                        Arrays.asList(SimpleInstruction.F, SimpleInstruction.F, SimpleInstruction.R, SimpleInstruction.F, SimpleInstruction.F, SimpleInstruction.R,
                                SimpleInstruction.F, SimpleInstruction.R, SimpleInstruction.R, SimpleInstruction.F)).toString(),
                ((SimpleMower) ((SimpleSimulation) simulation).getMowers().get(1)).getInstructions().toString());
    }
}
