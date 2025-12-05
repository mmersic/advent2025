package org.mersic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day01 {

    private static int getPartOneZeroCount(List<String> lines) {
        int position = 50;
        int zero_count = 0;
        for (String line: lines) {
            int move = Integer.parseInt(line.substring(1));
            if (line.charAt(0) == 'L') {
                position -= move;
            } else {
                position += move;
            }
            if (position % 100 == 0) {
                zero_count++;
            }
        }
        return zero_count;
    }

    private static int getPartTwoZeroCount(List<String> lines) {
        int position = 50;
        int zero_count = 0;
        for (String line: lines) {
            int move = Integer.parseInt(line.substring(1));
            int fullRotations = move / 100;
            int partialRotation = move % 100;
            zero_count += fullRotations;
            
            if (line.charAt(0) == 'L') {
                if (partialRotation >= position && position != 0) {
                    zero_count++;
                }
                position -= partialRotation;
            } else {
                if (partialRotation + position >= 100) {
                    zero_count++;
                }
                position += partialRotation;
            }
            if (position < 0) {
                position += 100;
            } else if (position >= 100) {
                position -= 100;
            }
        }
        return zero_count;
    }
    
    
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(Day01.class.getClassLoader().getResource("day.01.input").toURI()));

        int partOneZeroCount = getPartOneZeroCount(lines);
        int partTwoZeroCount = getPartTwoZeroCount(lines);
        
        System.out.println("day 01 part 1: " + partOneZeroCount);
        
        //6395 too high
        //6394 too high
        System.out.println("day 01 part 2: " + partTwoZeroCount);
    }


}
