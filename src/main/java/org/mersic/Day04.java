package org.mersic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day04 {

    private static long getPartOne(char[][] input, boolean modify) {
        long partOne = 0;
        long lastPartOne = 0;
        
        do {
            lastPartOne = partOne;
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length; j++) {
                    if (input[i][j] == '.') {
                        continue;
                    }
                    int neighborCount = getNeighborCount(input, i, j);
                    if (neighborCount < 4) {
                        if (modify) {
                            input[i][j] = '.';
                        }
                        partOne++;
                    }
                }
            }
        } while (modify && lastPartOne != partOne);
        return partOne;
    }

    private static int getNeighborCount(char[][] input, int i, int j) {
        int neighborCount = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (k == 0 && l == 0) {
                    continue;
                }
                try {
                    if (input[i + k][j + l] == '@') {
                        neighborCount++;
                    }
                } catch (Exception e) {
                    //Ignore array index out of bounds...
                }
            }
        }
        return neighborCount;
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(Day01.class.getClassLoader().getResource("day.04.input").toURI()));

        char[][] input = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            input[i] = lines.get(i).toCharArray();
        }
        
        long partOne = getPartOne(input, false);
        long partTwo = getPartOne(input, true);

        System.out.println("day 04 part 1: " + partOne);
        System.out.println("day 04 part 2: " + partTwo);
    }
}
