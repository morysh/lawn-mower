package com.morysh.lawnmower.core.simple;

import com.morysh.lawnmower.core.Instruction;
import com.morysh.lawnmower.core.Mower;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public enum SimpleInstruction implements Instruction {
    F(Mower::forward),
    L(Mower::rotateLeft),
    R(Mower::rotateRight);

    private Consumer<Mower> action;

    SimpleInstruction(Consumer<Mower> action) {
        this.action = action;
    }

    public void execute(@NotNull Mower mower) throws NullPointerException {
        action.accept(mower);
    }
}
