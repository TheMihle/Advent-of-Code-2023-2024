package year_2024.day_6;

import static common.ImportFile.fileTo2DArray;

public class Part_1 {
    public static void main(String[] args) {

//        Import 2D array
        String[][] inputArray = fileTo2DArray("src\\year_2024\\day_6\\input.txt", "");

//        Array for visited coordinates
        int[][] visitedArray = new int[inputArray.length][inputArray[0].length];

//        Find start
        int[] coords = new int[]{0,0};
        findStart(inputArray, coords);

//        Direction to move
        int x = -1, y = 0;
        int direction = 0;

//        Move and mark positions loop
        while (coords[0] >= 0 && coords[0] < inputArray.length &&
                coords[1] >= 0 && coords[1] < inputArray[coords[0]].length) {
            visitedArray[coords[0]][coords[1]] = 1;

//            Checks next square and changes direction if needed
//            Try/catch to ignore index out of bounds
            try {
                if (inputArray[coords[0] + x][coords[1] + y].equals("#")) {
                    if (direction == 3) direction = 0;
                    else direction++;

                    if (direction == 0) {
                        x = -1;y = 0;
                    } else if (direction == 1) {
                        x = 0;y = 1;
                    } else if (direction == 2) {
                        x = 1;y = 0;
                    } else {
                        x = 0;y = -1;
                    }
                }
            } catch (Exception e) {
//                Ignore exception
            }

            coords[0] += x;
            coords[1] += y;
        }

//        Outputs number of visited positions
        int numbVisited = countVisited(visitedArray);
        System.out.println("Day 6, Part 1, Number of distinct positions: " + numbVisited);
    }


//    Finds the coordinates for starting
    public static void findStart(String[][] inputArray, int [] coords) {
        while(coords[0]++ < inputArray.length) {
            for (int i = 0; i < inputArray[coords[0]].length; i++) {
                if (inputArray[coords[0]][i].equals("^")) {
                    coords[1] = i;
                    return;
                }
            }
        }
    }

//    Calculates total number of positions visited based on array
    public static int countVisited(int[][] visitedArray) {
        int numVisited = 0;

        for (int[] ints : visitedArray) {
            for (int visited : ints) {
                if (visited == 1) numVisited++;
            }
        }
        return numVisited;
    }
}
