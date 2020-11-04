package com.morysh.lawnmower.core;

import java.util.concurrent.locks.ReentrantLock;

public class Coordinate extends ReentrantLock {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
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
