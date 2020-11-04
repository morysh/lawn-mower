package com.morysh.lawnmower.core;

public interface Mower extends Runnable {
    /**
     * Moves the mower forward in the direction it's facing
     */
    void forward();

    /**
     * Rotates the mower to the left
     */
    void rotateLeft();

    /**
     * Rotates the mower to the right
     */
    void rotateRight();
}
