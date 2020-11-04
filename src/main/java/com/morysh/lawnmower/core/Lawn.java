package com.morysh.lawnmower.core;

public class Lawn {
    Coordinate[][] grid;

    public Lawn(int width, int height) {
        grid = new Coordinate[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Coordinate(x, y);
            }
        }
    }

    public
}
