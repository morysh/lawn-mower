package com.morysh.lawnmower;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @Test
    public void mainTest() {
        // GIVEN
        String[] args = new String[]{Objects.requireNonNull(getClass().getClassLoader().getResource("simple-test.txt")).getPath()};

        // WHEN
        App.main(args);

        // THEN
        assertEquals("1 3 N\n5 1 E\n", out.toString());
    }

    @BeforeEach
    public void replaceSystemOutput() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    public void restoreSystemOutput() {
        System.setOut(originalOut);
    }
}
