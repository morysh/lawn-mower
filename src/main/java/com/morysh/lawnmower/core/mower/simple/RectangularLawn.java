package com.morysh.lawnmower.core.mower.simple;

import com.morysh.lawnmower.core.mower.InvalidCoordinateException;

public class RectangularLawn {
    SimpleCartesianCoordinatesPosition[][] grid;

    public RectangularLawn(int width, int height) throws InvalidCoordinateException {
        if (width <= 0 || height <= 0) {
            throw new InvalidCoordinateException("Lawn must have a positive width and length");
        }
        grid = new SimpleCartesianCoordinatesPosition[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new SimpleCartesianCoordinatesPosition(x, y);
            }
        }
    }

    public SimpleCartesianCoordinatesPosition getPosition(int x, int y) {
        try {
            return grid[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getHeight() {
        return grid[0].length;
    }

    public int getWidth() {
        return grid.length;
    }
}
