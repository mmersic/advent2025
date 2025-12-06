package org.mersic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {
    private static long getPartOne(String[][] problems) {
        long partOne = 0;

        for (int i = 0; i < problems[0].length; i++) {
            char op = problems[problems.length-1][i].charAt(0);
            long ans = op == '+' ? 0 : 1;
            for (int j = 0; j < problems.length-1; j++) {
                switch (op) {
                    case '+':
                        ans += Long.parseLong(problems[j][i]);
                        break;
                    case '*':
                        ans *= Long.parseLong(problems[j][i]);
                        break;
                    default:
                        System.out.println("unexpected op: " + op);
                        throw new RuntimeException("unexpected op: " + op);
                }
            }
            partOne += ans;
        }
        
        return partOne;
    }

    private static long getPartTwo(String input) {
        long partTwo = 0;
        String[] lines = input.split("\n");
        int len = Arrays.stream(lines).mapToInt(String::length).max().getAsInt();
        for (int i = 0; i < lines.length; i++) {
            while (lines[i].length() < len) {
                lines[i] += ' ';
            }
        }

        List<Long> nums = new ArrayList<>();
        for (int i = lines[0].length() - 1; i >= 0; i--) {
            char[] col = new char[lines.length - 1];
            for (int j = 0; j < lines.length - 1; j++) {
                col[j] = lines[j].charAt(i);
            }
            nums.add(Long.parseLong(new String(col).trim()));
            if (lines[lines.length-1].charAt(i) == '+') {
                partTwo += nums.stream().reduce(0L, Long::sum);
                i--;
                nums.clear();
            } else if (lines[lines.length-1].charAt(i) == '*') {
                partTwo += nums.stream().reduce(1L, (a ,b) -> a * b);
                i--;
                nums.clear();
            }
        }
        
        return partTwo;
    }
    
    public static void main(String[] args) throws Exception {
        String originalInput = Files.readString(Path.of(Day01.class.getClassLoader().getResource("day.06.input").toURI()));
        String input = originalInput.replaceAll("[ ]+", " ");
        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replaceAll("^ +", "");
        }
        String[][] problems = new String[lines.length][lines[0].split(" ").length];
        for (int i = 0; i < problems.length; i++) {
            problems[i] = lines[i].split(" ");
        }
        
        long partOne = getPartOne(problems);
        long partTwo = getPartTwo(originalInput);

        System.out.println("day 03 part 1: " + partOne);
        System.out.println("day 03 part 2: " + partTwo);
    }

}
