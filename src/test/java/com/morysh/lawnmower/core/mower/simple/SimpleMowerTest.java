package com.morysh.lawnmower.core.mower.simple;

import com.morysh.lawnmower.core.mower.InvalidCoordinateException;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMowerTest {

    @Test
    public void CollisionTest() throws InvalidCoordinateException {
        // GIVEN
        RectangularLawn lawn = new RectangularLawn(6, 6);
        Deque<SimpleInstruction> instructions = new ArrayDeque<>();
        // LFLFLFLFF
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.F);
        SimpleMower mower = new SimpleMower(lawn, 1, 2, SimpleCardinalOrientation.N, instructions);
        // Putting another mower on the path with no instructions => not moving
        SimpleMower obstacle = new SimpleMower(lawn, 0, 2, SimpleCardinalOrientation.N, new ArrayDeque<>());

        // WHEN
        mower.run();

        // THEN
        assertEquals(2, mower.getPosition().getX());
        assertEquals(3, mower.getPosition().getY());
        assertEquals(SimpleCardinalOrientation.N, mower.getOrientation());
    }

    @Test
    public void outOfBoundsTest() throws InvalidCoordinateException {
        // GIVEN
        RectangularLawn lawn = new RectangularLawn(6, 6);
        Deque<SimpleInstruction> instructions = new ArrayDeque<>();
        // FFFRFFRFRRF
        // One more forward at the beginning brings it outside the bounds. Should not change final position
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.F);
        SimpleMower mower = new SimpleMower(lawn, 3, 3, SimpleCardinalOrientation.E, instructions);

        // WHEN
        mower.run();

        // THEN
        assertEquals(5, mower.getPosition().getX());
        assertEquals(1, mower.getPosition().getY());
        assertEquals(SimpleCardinalOrientation.E, mower.getOrientation());
    }

    @Test
    public void simpleMowerTest1() throws InvalidCoordinateException {
        // GIVEN
        RectangularLawn lawn = new RectangularLawn(6, 6);
        Deque<SimpleInstruction> instructions = new ArrayDeque<>();
        // LFLFLFLFF
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.L);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.F);
        SimpleMower mower = new SimpleMower(lawn, 1, 2, SimpleCardinalOrientation.N, instructions);

        // WHEN
        mower.run();

        // THEN
        assertEquals(1, mower.getPosition().getX());
        assertEquals(3, mower.getPosition().getY());
        assertEquals(SimpleCardinalOrientation.N, mower.getOrientation());
    }

    @Test
    public void simpleMowerTest2() throws InvalidCoordinateException {
        // GIVEN
        RectangularLawn lawn = new RectangularLawn(6, 6);
        Deque<SimpleInstruction> instructions = new ArrayDeque<>();
        // FFRFFRFRRF
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.F);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.R);
        instructions.add(SimpleInstruction.F);
        SimpleMower mower = new SimpleMower(lawn, 3, 3, SimpleCardinalOrientation.E, instructions);

        // WHEN
        mower.run();

        // THEN
        assertEquals(5, mower.getPosition().getX());
        assertEquals(1, mower.getPosition().getY());
        assertEquals(SimpleCardinalOrientation.E, mower.getOrientation());
    }
}
