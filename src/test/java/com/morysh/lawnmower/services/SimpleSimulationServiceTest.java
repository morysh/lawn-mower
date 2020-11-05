package com.morysh.lawnmower.services;

import com.morysh.lawnmower.core.mower.Mower;
import com.morysh.lawnmower.core.mower.Orientation;
import com.morysh.lawnmower.core.mower.Position;
import com.morysh.lawnmower.core.simulation.Simulation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SimpleSimulationServiceTest {
    @Test
    public void createFromFileTest() throws FileProcessingException {
        // GIVEN
        final String TEST_STRING = "lorem/ipsum/dolor/sit/amet.txt";
        SimpleSimulationService simulationService = new SimpleSimulationService();
        InputFileService inputFileService = mock(InputFileService.class);
        simulationService.setInputFileService(inputFileService);

        // WHEN
        simulationService.createFromFile(TEST_STRING);

        // THEN
        verify(inputFileService).createSimulationFromFile(TEST_STRING);
    }

    @Test
    public void runTest() {
        // GIVEN
        final int x1 = (int) Math.floor(Math.random() * 100);
        final int y1 = (int) Math.floor(Math.random() * 100);
        final int x2 = (int) Math.floor(Math.random() * 100);
        final int y2 = (int) Math.floor(Math.random() * 100);
        final String o1 = "BlaBla";
        final String o2 = "Car";
        SimpleSimulationService simulationService = new SimpleSimulationService();

        Mower mower1 = mock(Mower.class);
        when(mower1.getPosition()).thenReturn(new Position() {
            @Override
            public int getX() {
                return x1;
            }

            @Override
            public int getY() {
                return y1;
            }
        });
        when(mower1.getOrientation()).thenReturn(new Orientation() {
            @Override
            public String toString() {
                return o1;
            }
        });

        Mower mower2 = mock(Mower.class);
        when(mower2.getPosition()).thenReturn(new Position() {
            @Override
            public int getX() {
                return x2;
            }

            @Override
            public int getY() {
                return y2;
            }
        });
        when(mower2.getOrientation()).thenReturn(new Orientation() {
            @Override
            public String toString() {
                return o2;
            }
        });

        Simulation simulation = mock(Simulation.class);
        when(simulation.getMowers()).thenReturn(Arrays.asList(mower1, mower2));
        StringBuilder result = new StringBuilder();
        when(simulation.getResult()).thenReturn(result);

        // WHEN
        simulationService.run(simulation);

        // THEN
        verify(mower1).run();
        verify(mower2).run();
        assertEquals(String.format("%d %d %s\n%d %d %s\n", x1, y1, o1, x2, y2, o2), simulation.getResult().toString());
    }
}
