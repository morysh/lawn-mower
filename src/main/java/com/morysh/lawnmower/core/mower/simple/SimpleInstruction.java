package com.morysh.lawnmower.core.mower.simple;

import com.morysh.lawnmower.core.mower.Instruction;
import com.morysh.lawnmower.core.mower.Mower;

import java.util.function.Consumer;

public enum SimpleInstruction implements Instruction {
    F(Mower::forward), L(Mower::rotateLeft), R(Mower::rotateRight);

    private Consumer<Mower> action;

    SimpleInstruction(Consumer<Mower> action) {
        this.action = action;
    }

    public void execute(Mower mower) throws NullPointerException {
        action.accept(mower);
    }
}
