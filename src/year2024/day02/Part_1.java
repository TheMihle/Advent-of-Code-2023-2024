package year2024.day02;

import static common.ImportFile.fileToInt2DArray;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    public static void main(String[] args) {
        int[][] intArray = fileToInt2DArray(getInputPath(Part_1.class), " ");

//        Calculates number of safe reports
        int numberSafe = 0;

        for (int[] ints : intArray) {
            int steps = 0;
            for (int i = 0; i < ints.length-1; i++) {
                int difference = ints[i+1] - ints[i];
                if (difference > 0 && difference < 4) {
                    steps++;
                } else if (difference < 0 && difference > -4) {
                    steps--;
                }
            }

            int diffFromLength = Math.abs(steps) - (ints.length-1);
            if (diffFromLength == 0) numberSafe++;
        }

        System.out.println("Day 2, Part 1, Number of safe reports: " + numberSafe );
    }
}
