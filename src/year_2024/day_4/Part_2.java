package year_2024.day_4;

import static common.ImportFile.fileTo2DArray;

public class Part_2 {
    public static void main(String[] args) {

//        Import file as 2D array
        String[][] array = fileTo2DArray("src\\year_2024\\day_4\\input.txt");

        int number = 0;

//        Loops through 2D Array and finds A to check for X-mas
//        Can Ignore spots at the border
        for (int row = 1; row < array.length-1; row++) {
            for (int col = 1; col < array[row].length-1; col++) {
                if (array[row][col].equals("A")) {

//                    Checks for the X-MAS pattern and counts if true
                    if (checkForXMAS(array, row, col)) number++;
                }
            }
        }

//        Print result:
        System.out.println("Day 4, Part 2, Number of X-MAS " + number);
    }

//    Checks if there is X-MAS pattern
    public static boolean checkForXMAS(String[][] array, int row, int col) {

        if (array[row - 1][col - 1].equals("M") && array[row + 1][col + 1].equals("S") ||
            array[row - 1][col - 1].equals("S") && array[row + 1][col + 1].equals("M")) {
            if (array[row - 1][col + 1].equals("M") && array[row + 1][col - 1].equals("S") ||
                array[row - 1][col + 1].equals("S") && array[row + 1][col - 1].equals("M")) {
                return true;
            }
        }
        return false;
    }
}
