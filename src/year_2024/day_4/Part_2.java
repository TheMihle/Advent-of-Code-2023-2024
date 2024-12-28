package year_2024.day_4;

import static common.ImportFile.fileTo2DArray;

public class Part_2 {
    public static void main(String[] args) {
        String[][] inputArray = fileTo2DArray("src\\year_2024\\day_4\\input.txt");

        int number = 0;

//        Loops through 2D Array and finds A to check for X-MAS and counts them
//        Can ignore spots at the border
        for (int row = 1; row < inputArray.length-1; row++) {
            for (int col = 1; col < inputArray[row].length-1; col++) {
                if (inputArray[row][col].equals("A")) {

//                    Checks for the X-MAS pattern around A and counts number
                    if (checkForXMAS(inputArray, row, col)) number++;
                }
            }
        }

        System.out.println("Day 4, Part 2, Number of X-MAS " + number);
    }

//    Checks if there is X-MAS pattern
    public static boolean checkForXMAS(String[][] array, int row, int col) {
        return ((array[row - 1][col - 1].equals("M") && array[row + 1][col + 1].equals("S") ||
                 array[row - 1][col - 1].equals("S") && array[row + 1][col + 1].equals("M")) &&
                (array[row - 1][col + 1].equals("M") && array[row + 1][col - 1].equals("S") ||
                 array[row - 1][col + 1].equals("S") && array[row + 1][col - 1].equals("M")));
    }
}
