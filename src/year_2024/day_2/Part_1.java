package year_2024.day_2;

import static common.ImportFile.fileTo2DArray;

public class Part_1 {
    public static void main(String[] args) {

//        Input file as 2D array
        String[][] inputArray = fileTo2DArray("src\\year_2024\\day_2\\input.txt", " ");

//        Convert to int array
        int[][] intArray = convert2DStringArrToInt(inputArray);

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

//    Converts 2D String array to int
    private static int[][] convert2DStringArrToInt(String[][] stringArray) {
        int[][] intArray = new int[stringArray.length][];
        for ( int i = 0; i < stringArray.length; i++) {
            intArray[i] = new int[stringArray[i].length];
            for (int j = 0; j < stringArray[i].length; j++) {
                intArray[i][j] = Integer.parseInt(stringArray[i][j]);
            }
        }
        return intArray;
    }
}
