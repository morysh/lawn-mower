package com.morysh.lawnmower.core.simple;

import java.util.concurrent.Semaphore;

/**
 * Represents a immutable position in the lawn, described with cartesian coordinates.
 * @see Semaphore
 */
public class CartesianCoordinatesPosition extends Semaphore {
    private final int x;
    private final int y;

    public CartesianCoordinatesPosition(int x, int y) {
        // Only one mower per position allowed
        super(1);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
