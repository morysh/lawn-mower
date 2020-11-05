package com.morysh.lawnmower.services;

import com.morysh.lawnmower.core.mower.InvalidCoordinateException;
import com.morysh.lawnmower.core.mower.Mower;
import com.morysh.lawnmower.core.mower.simple.RectangularLawn;
import com.morysh.lawnmower.core.mower.simple.SimpleCardinalOrientation;
import com.morysh.lawnmower.core.mower.simple.SimpleInstruction;
import com.morysh.lawnmower.core.mower.simple.SimpleMower;
import com.morysh.lawnmower.core.simulation.SimpleSimulation;
import com.morysh.lawnmower.core.simulation.Simulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class PlainTextInputFileService implements InputFileService {
    private static final Pattern firstLinePattern = Pattern.compile("^(\\d+) (\\d+)$");
    private static final Pattern initialPositionPattern = Pattern.compile("^(\\d+) (\\d+) (" + getEnumPattern(SimpleCardinalOrientation.values()) + ")$");
    private static final Pattern instructionsPattern = Pattern.compile("^" + getEnumPattern(SimpleInstruction.values()) + "+$");

    @Override
    public Simulation createSimulationFromFile(String filePath) throws FileProcessingException {
        Path path = Paths.get(filePath);
        if (Files.notExists(path)) {
            throw new FileProcessingException("Cannot find given file");
        }

        List<String> lines;

        try {
            lines = Files.readAllLines(path);

            if (lines.size() < 3) {
                throw new FileProcessingException("Provided file does not have enough lines to create simulation. Minimum : 3; Actual : " + lines.size());
            }

            Matcher firstLineMatcher = firstLinePattern.matcher(lines.get(0));
            if (!firstLineMatcher.matches()) {
                throw new FileProcessingException("Invalid first line \"" + lines.get(0) + "\". Should be in the form \"x y\"");
            }

            RectangularLawn lawn = new RectangularLawn(Integer.parseInt(firstLineMatcher.group(1), 10) + 1, Integer.parseInt(firstLineMatcher.group(2), 10) + 1);
            lines.remove(0);

            List<Mower> mowers = new ArrayList<>();

            for (int lineIndex = 0; lineIndex < lines.size(); lineIndex += 2) {
                Matcher initialPositionMatcher = initialPositionPattern.matcher(lines.get(lineIndex));
                if (!initialPositionMatcher.matches()) {
                    throw new FileProcessingException(
                            "Invalid mower initial position \"" + lines.get(0) + "\". Should be in the form \"x y " + getEnumPattern(SimpleCardinalOrientation.values()) +
                                    "\"");
                }

                Matcher instructionsMatcher = instructionsPattern.matcher(lines.get(lineIndex + 1));
                if (!instructionsMatcher.matches()) {
                    throw new FileProcessingException(
                            "Invalid instructions \"" + lines.get(0) + "\". Instructions can only be one of " + getEnumPattern(SimpleInstruction.values()));
                }

                int x = Integer.parseInt(initialPositionMatcher.group(1), 10);
                int y = Integer.parseInt(initialPositionMatcher.group(2), 10);
                SimpleCardinalOrientation orientation = SimpleCardinalOrientation.valueOf(initialPositionMatcher.group(3));
                String instructionsString = instructionsMatcher.group();
                Deque<SimpleInstruction> instructions = new ArrayDeque<>(instructionsString.length());
                for (int instructionIndex = 0; instructionIndex < instructionsString.length(); instructionIndex++) {
                    instructions.add(SimpleInstruction.valueOf(instructionsString.substring(instructionIndex, instructionIndex + 1)));
                }

                mowers.add(new SimpleMower(lawn, x, y, orientation, instructions));
            }

            return new SimpleSimulation(lawn, mowers);
        } catch (InvalidCoordinateException | IOException e) {
            throw new FileProcessingException(e);
        }
    }

    private static String getEnumPattern(Enum[] values) {
        return Arrays.stream(values).map(Enum::name).collect(Collectors.joining("", "[", "]"));
    }
}
