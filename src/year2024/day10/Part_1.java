package year2024.day10;

import common.Direction;

import java.util.HashSet;
import java.util.Set;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileToInt2DArray;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    private static final int END_VALUE = 9;

    public static void main(String[] args) {
        int[][] topoMap = fileToInt2DArray(getInputPath(Part_1.class));

//        Find the starting points, 0, count number of unique 9s reached for every 0
        int trailHeadScore = 0;
        for (int row = 0; row < topoMap.length; row++) {
            for (int col = 0; col < topoMap[row].length; col++) {
                if(topoMap[row][col] == 0) {
                    trailHeadScore += findTrailHeadScore(topoMap, row, col, 1);
                }
            }
        }
        System.out.println("Day 10, Part 1, Sum of trailhead scores: " + trailHeadScore);
    }

//    Recursive method that finds the trailhead scores by traversing and checking if end is unique
    public static int findTrailHeadScore(Set<String> endPointMemory, int[][] topoMap, int row, int col, int nextValue) {
        if (topoMap[row][col] == END_VALUE) {
            if (endPointMemory.contains("[" +row + ", " + col + "]")) return 0;
            endPointMemory.add("[" +row + ", " + col + "]");
            return 1;
        }

        int trailHeadScore = 0;
        for (Direction direction : Direction.values()) {
            if (!direction.isCardinal()) continue;
            int checkRow = row + direction.getDeltaX();
            int checkCol = col + direction.getDeltaY();

            if (!rangeCheck(topoMap, checkCol, checkRow)) continue;
            if (topoMap[checkRow][checkCol] == nextValue) {
                trailHeadScore += findTrailHeadScore(endPointMemory, topoMap, checkRow, checkCol, nextValue + 1);
            }
        }
        return trailHeadScore;
    }

//    Recursive method, but unique cordMemory
    private static int findTrailHeadScore(int[][] topoMap, int row, int col, int nextValue) {
        Set<String> endPointMemory = new HashSet<>();
        return findTrailHeadScore(endPointMemory, topoMap,row, col, nextValue);
    }
}
