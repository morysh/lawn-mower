package com.morysh.lawnmower.core;

public class SimpleMower implements Mower {
    private Lawn lawn;
    private Coordinate coordinate;
    private Orientation orientation;

    public SimpleMower(Lawn lawn, Coordinate coordinate, Orientation orientation) {
        this.lawn = lawn;
        this.coordinate = coordinate;
        this.orientation = orientation;
    }

    @Override
    public void run() {

    }
}
