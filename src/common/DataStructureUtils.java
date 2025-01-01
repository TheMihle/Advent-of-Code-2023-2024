package common;

public class DataStructureUtils {

    public static <T> boolean rangeCheck(T[][] array, int col, int row) {
        return row >= 0 && col >= 0 && row < array.length && col < array[row].length;
    }

    public static boolean rangeCheck(int[][] array, int col, int row) {
        return row >= 0 && col >= 0 && row < array.length && col < array[row].length;
    }

//    I know print for catch is not ideal, but it works in this case, its just AOC
    public static int[][] string2DToInt(String[][] stringArray) {
        int[][] intArray = new int[stringArray.length][];
        try {
            for (int row = 0; row < stringArray.length; row++) {
                intArray[row] = new int[stringArray[row].length];
                for (int col = 0; col < stringArray[row].length; col++) {
                    intArray[row][col] = Integer.parseInt(stringArray[row][col]);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Could not parse to to int, check if input is parsable: " + e);
        }
        return intArray;
    }
}
