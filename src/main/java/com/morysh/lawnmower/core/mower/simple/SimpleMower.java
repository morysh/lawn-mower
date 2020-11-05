package com.morysh.lawnmower.core.mower.simple;

import com.morysh.lawnmower.core.mower.Instruction;
import com.morysh.lawnmower.core.mower.InvalidCoordinateException;
import com.morysh.lawnmower.core.mower.Mower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;

public class SimpleMower implements Mower {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMower.class);
    private Deque<? extends Instruction> instructions;
    private RectangularLawn lawn;
    private SimpleCardinalOrientation orientation;
    private SimpleCartesianCoordinatesPosition position;

    // Constructor uses type SimpleInstruction and not interface Instruction because it's only know to be compatible with this implementation as of now.
    public SimpleMower(RectangularLawn lawn, int x, int y, SimpleCardinalOrientation orientation, Deque<SimpleInstruction> instructions)
            throws InvalidCoordinateException {
        this.lawn = lawn;
        position = lawn.getPosition(x, y);
        if (position == null) {
            throw new InvalidCoordinateException("Given mower coordinate is outside the lawn");
        }
        if (!position.tryAcquire()) {
            throw new InvalidCoordinateException("Given mower position is already occupied");
        }
        this.orientation = orientation;
        this.instructions = instructions;
    }

    /**
     * Move the mower forward 1 coordinate in the direction it's facing.
     * If this action cannot be performed (eg. obstacle, out of bound), it is ignored.
     */
    @Override
    public void forward() {
        int nextX = position.getX();
        int nextY = position.getY();

        switch (orientation) {
            case N:
                nextY++;
                break;
            case E:
                nextX++;
                break;
            case S:
                nextY--;
                break;
            case W:
                nextX--;
                break;
            default:
                // Should never happen
                LOG.error("Invalid orientation while moving forward");
                return;
        }
        SimpleCartesianCoordinatesPosition nextCoordinate = lawn.getPosition(nextX, nextY);
        if (nextCoordinate == null) {
            LOG.debug("Trying to go outside of bounds : going {} from [{}, {}]. Ignoring instruction", orientation, position.getX(), position.getY());
            return;
        }
        if (lawn.getPosition(nextX, nextY).tryAcquire()) {
            // Position clear, release previous one
            position.release();
            position = nextCoordinate;
        } else {
            LOG.debug("Position [{}, {}] is already occupied. Ignoring instruction", nextX, nextY);
        }
    }

    /**
     * Rotates mower 90° to the left
     */
    @Override
    public void rotateLeft() {
        orientation = orientation.getNextLeft();
    }

    /**
     * Rotates mower 90° to the right
     */
    @Override
    public void rotateRight() {
        orientation = orientation.getNextRight();
    }

    public SimpleCartesianCoordinatesPosition getPosition() {
        return position;
    }

    public SimpleCardinalOrientation getOrientation() {
        return orientation;
    }

    @Override
    public void run() {
        while (!instructions.isEmpty()) {
            Instruction instruction = instructions.pop();
            instruction.execute(this);
        }
    }

    public Deque<? extends Instruction> getInstructions() {
        return instructions;
    }
}
