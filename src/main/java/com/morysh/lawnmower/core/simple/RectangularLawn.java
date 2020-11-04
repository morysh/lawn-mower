package com.morysh.lawnmower.core.simple;

import com.morysh.lawnmower.core.InvalidCoordinateException;

public class RectangularLawn {
    CartesianCoordinatesPosition[][] grid;

    public RectangularLawn(int width, int height) throws InvalidCoordinateException {
        if (width <= 0 || height <= 0) {
            throw new InvalidCoordinateException("Lawn must have a positive width and length");
        }
        grid = new CartesianCoordinatesPosition[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new CartesianCoordinatesPosition(x, y);
            }
        }
    }

    public CartesianCoordinatesPosition getPosition(int x, int y) {
        try {
            return grid[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return grid[0].length;
    }
}
