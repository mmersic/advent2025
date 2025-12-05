package org.mersic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day02 {
    
    public static long getPartOne(String[] ranges) {
        long partOne = 0;
        
        for (String range : ranges) {
            String[] rp = range.split("-");
            long lower =  Long.parseLong(rp[0]);
            long upper = Long.parseLong(rp[1]);
            long frontHalf = 1;
            long current = Long.parseLong(frontHalf + "" + frontHalf);
            for (; current < upper; frontHalf++) {
                current = Long.parseLong(frontHalf + "" + frontHalf);
                if (current >= lower && current <= upper) {
                    partOne += current;
                }
            }
        }
        
        return partOne;
    }

    public static long getPartTwo(String[] ranges) {
        Set<Long> invalidIds = new HashSet<>();

        for (String range : ranges) {
            String[] rp = range.split("-");
            long lower =  Long.parseLong(rp[0]);
            long upper = Long.parseLong(rp[1]);
            long current = 11;
            for (long pattern = 1; current <= upper;) {
                while (current <= upper) {
                    if (current >= lower) {
                        invalidIds.add(current);
                    }
                    current = Long.parseLong(current + "" + pattern);
                }
                pattern++;
                current = Long.parseLong(pattern + "" + pattern);
            }
        }
        return invalidIds.stream().reduce(0L, Long::sum);
    }    
    
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(Day01.class.getClassLoader().getResource("day.02.input").toURI()));
        String[] ranges = lines.get(0).split(",");

        long partOne = getPartOne(ranges);
        long partTwo = getPartTwo(ranges);

        System.out.println("day 02 part 1: " + partOne);
        System.out.println("day 02 part 2: " + partTwo);
    }
}
