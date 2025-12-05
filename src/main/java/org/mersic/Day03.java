package org.mersic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day03 {

    public static long getPartTwo(List<String> lines) {
        long partTwo = 0;

        for (String line : lines) {
            char[] chars = line.toCharArray();
            char[] batt = new char[12];
            System.arraycopy(chars, chars.length - 12, batt, 0, 12);
            for (int i = chars.length-13; i >= 0; i--) {
                char temp = chars[i];
                for (int j = 0; j < 12; j++) {
                    if (temp >= batt[j]) {
                        char t2 = batt[j];
                        batt[j] = temp;
                        temp = t2;
                    } else {
                        break;
                    }
                }
            }
            partTwo += Long.parseLong(new String(batt));
        }

        return partTwo;
    }
    
    public static int getPartOne(List<String> lines) {
        int partOne = 0;
        
        for (String line : lines) {
            char[] chars = line.toCharArray();
            char first = chars[chars.length-2];
            char second = chars[chars.length-1];
            for (int i = chars.length-3; i >= 0; i--) {
                if (chars[i] >= first) {
                    if (first > second) {
                        second = first;
                    }
                    first = chars[i];
                }
            }
            partOne += Integer.parseInt(first + "" + second);
        }
        
        return partOne;
    }
    
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(Day01.class.getClassLoader().getResource("day.03.input").toURI()));

        long partOne = getPartOne(lines);
        long partTwo = getPartTwo(lines);


//        day 03 part 1: 16812
//        day 03 part 2: 166345822896410
        System.out.println("day 03 part 1: " + partOne);
        System.out.println("day 03 part 2: " + partTwo);
    }
}
