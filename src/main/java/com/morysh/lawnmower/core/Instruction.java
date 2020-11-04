package com.morysh.lawnmower.core;

import org.jetbrains.annotations.NotNull;

public interface Instruction {
    /**
     * Executes this instruction on the given mower.
     * @param mower to execute the instruction on
     * @throws NullPointerException if mower is null
     */
    void execute(@NotNull Mower mower) throws NullPointerException;
}
