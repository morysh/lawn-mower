package com.morysh.lawnmower.core.mower;

public interface Instruction {
    /**
     * Executes this instruction on the given mower.
     *
     * @param mower to execute the instruction on
     * @throws NullPointerException if mower is null
     */
    void execute(Mower mower) throws NullPointerException;
}
