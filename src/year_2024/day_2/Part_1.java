package year_2024.day_2;

import static common.ImportFile.fileTo2DArray;

public class Part_1 {
    public static void main(String[] args) {

//        Input array
        String[][] array = fileTo2DArray("src\\year_2024\\day_2\\input.txt", " ");

//        Convert to int array
        int[][] intArray = new int[array.length][];
        for ( int i = 0; i < array.length; i++) {
            intArray[i] = new int[array[i].length];
            for (int j = 0; j < array[i].length; j++) {
                intArray[i][j] = Integer.parseInt(array[i][j]);
            }
        }

//        Calculates number of safes
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
