package org.mersic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

record Range(long low, long high) {
    public boolean overlaps(Range r) {
        if (this.low < r.low && this.high < r.low) {
            return false;
        } else if (r.low < this.low && r.high < this.low) {
            return false;
        } else {
            return true;
        }
    }
    public Range combine(Range r) {
        long newLow = Math.min(this.low, r.low);
        long newHigh = Math.max(this.high, r.high);
        return new Range(newLow, newHigh);
    }
}

class RangeList extends ArrayList<Range> {
    @Override
    public boolean add(Range r) {
        Optional<Range> intersecting = Optional.empty();
        while ((intersecting = removeIntersecting(r)).isPresent()) {
            r = r.combine(intersecting.get());
        }
        
        return super.add(r);
    }
    
    public Optional<Range> removeIntersecting(Range r) {
        for (Range r2 : this) {
            if (r.overlaps(r2)) {
                this.remove(r2);
                return Optional.of(r2);
            }
        }
        return Optional.empty();
    }
}

public class Day05 {
    
    private static long getPartOne(String[] ranges, String[] ingredients) {
        long partOne = 0;

        for (String ingredient : ingredients) {
            long item = Long.parseLong(ingredient);
            for (String range : ranges) {
                String[] r = range.split("-");
                long lower = Long.parseLong(r[0]);
                long upper = Long.parseLong(r[1]);
                if (lower <= item && item <= upper) {
                    partOne++;
                    break;
                }
            }
        }

        return partOne;
    }

    
    private static long getPartTwo(String[] ranges) {
        long partTwo = 0;

        RangeList rangesList = new RangeList();
        for (String range : ranges) {
            String[] r = range.split("-");
            long lower = Long.parseLong(r[0]);
            long upper = Long.parseLong(r[1]);
            rangesList.add(new Range(lower, upper));
        }
        
        for (Range r : rangesList) {
            partTwo += (r.high() - r.low() + 1);
        }
        
        return partTwo;
    }

    public static void main(String[] args) throws Exception {
        
        String[] lines = Files.readString(Path.of(Day01.class.getClassLoader().getResource("day.05.input").toURI())).split("\n\n");
        String[] ranges = lines[0].split("\n");
        String[] ingredients = lines[1].split("\n");
        
        long partOne = getPartOne(ranges, ingredients);
        long partTwo = getPartTwo(ranges);

        System.out.println("day 05 part 1: " + partOne);
        System.out.println("day 05 part 2: " + partTwo);
    }

}
