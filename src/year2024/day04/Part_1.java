package year2024.day04;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    public static void main(String[] args) {
        String[][] inputArray = fileTo2DArray(getInputPath(Part_1.class));

        int number = 0;

//        Loops through 2d array and finds X to check for XMAS and counts them
        for (int row = 0; row < inputArray.length; row++) {
            for (int col = 0; col < inputArray[row].length; col++) {
                if (inputArray[row][col].equals("X")) {

//                    Checks for letters in the different directions around the X and returns number
                   number += checkForXMAS(inputArray, row, col);
                }
            }
        }

        System.out.println("Day 4, Part 1, Number of XMAS: " + number);
    }

//    Checks for letters in the different directions around the input and returns number of matches
    public static int checkForXMAS(String[][] array, int row, int col) {
        int number = 0;

        for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
            for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                if (deltaRow == 0 && deltaCol == 0) continue;

//                Skip if coordinate border prevent word.
                if (!rangeCheck(array, row + 3 * deltaRow, col + 3 * deltaCol)) continue;

                if (array[row + 1 * deltaRow][col + 1 * deltaCol].equals("M") &&
                    array[row + 2 * deltaRow][col + 2 * deltaCol].equals("A") &&
                    array[row + 3 * deltaRow][col + 3 * deltaCol].equals("S")) {
                        number++;
                }
            }
        }
        return number;
    }
}
