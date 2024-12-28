package year_2024.day_10;

import java.util.HashSet;
import java.util.Set;

import static common.ImportFile.fileToInt2DArray;

public class Part_1 {
    public static void main(String[] args) {
        int[][] topoMap = fileToInt2DArray("src\\year_2024\\day_10\\input.txt");

//        Find the starting points, 0, count number of unique 9s reached for every 0
        int trailHeadScore = 0;
        for (int row = 0; row < topoMap.length; row++) {
            for (int col = 0; col < topoMap[row].length; col++) {
                if(topoMap[row][col] == 0) {
                    trailHeadScore += findTrailHeadScore(topoMap, row, col, 1);
                }
            }
        }
        System.out.println("Day 11, Part 1, Sum of trailhead scores: " + trailHeadScore);
    }

//    Recursive method that finds the trailhead scores by traversing and checking if end is unique
    public static int findTrailHeadScore(Set<String> coordMemory, int[][] topoMap, int row, int col, int nextValue) {
        if (topoMap[row][col] == 9) {
            if (coordMemory.contains("[" +row + ", " + col + "]")) return 0;
            coordMemory.add("[" +row + ", " + col + "]");
            return 1;
        }

        int trailHeadScore = 0;
        int[][] directions = {{-1, 0},{0, -1}, {0, 1}, {1, 0}};
        for (int[] direction : directions) {
            int checkRow = row + direction[0];
            int checkCol = col + direction[1];

            if (!indexCheck(topoMap, checkRow, checkCol)) continue;
            if (topoMap[checkRow][checkCol] == nextValue) {
                trailHeadScore += findTrailHeadScore(coordMemory, topoMap, checkRow, checkCol, nextValue + 1);
            }
        }
        return trailHeadScore;
    }

//    Recursive method, but unique cordMemory
    private static int findTrailHeadScore(int[][] topoMap, int row, int col, int nextValue) {
        Set<String> coordMemory = new HashSet<>();
        return findTrailHeadScore(coordMemory, topoMap,row, col, nextValue);
    }

//    Checks if it's a valid index, not out of bounds
    public static boolean indexCheck(int[][] array, int row, int col) {
        if (row < 0 || col < 0) return false;
        return row < array.length && col < array[row].length;
    }
}