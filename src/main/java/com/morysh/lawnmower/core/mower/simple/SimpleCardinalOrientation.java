package com.morysh.lawnmower.core.mower.simple;

import com.morysh.lawnmower.core.mower.Orientation;

public enum SimpleCardinalOrientation implements Orientation {
    N, E, S, W;

    public SimpleCardinalOrientation getNextLeft() {
        switch (this) {
            case N:
                return W;
            case E:
                return N;
            case S:
                return E;
            case W:
                return S;
            default:
                // Should never happen, here to avoid compile errors
                return null;
        }
    }

    public SimpleCardinalOrientation getNextRight() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            case W:
                return N;
            default:
                // Should never happen, here to avoid compile errors
                return null;
        }
    }
}
