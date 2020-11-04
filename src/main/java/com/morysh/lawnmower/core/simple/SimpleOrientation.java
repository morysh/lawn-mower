package com.morysh.lawnmower.core.simple;

public enum SimpleOrientation {
    N("North"),
    E("East"),
    S("South"),
    W("West");

    private String value;

    SimpleOrientation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public SimpleOrientation getNextLeft() {
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

    public SimpleOrientation getNextRight() {
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
