package com.morysh.lawnmower.core.mower;

public interface Mower extends Runnable {
    /**
     * Moves the mower forward in the direction it's facing. how much it moves depends on the implementation
     */
    void forward();

    /**
     * Rotates the mower to the left. How much it rotates depends on the implementation
     */
    void rotateLeft();

    /**
     * Rotates the mower to the right. How much it rotates depends on the implementation
     */
    void rotateRight();

    Orientation getOrientation();

    Position getPosition();
}
