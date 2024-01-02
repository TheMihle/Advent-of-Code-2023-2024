package day_16;

import static common.ImportFile.fileTo2DArray;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as 2DArray
        String[][] spaceCoordinates= fileTo2DArray("src\\day_16\\input.txt");

        int[][][] energizedCoordinates = new int[spaceCoordinates.length][spaceCoordinates[0].length][4];

//        Starts recursive methods to find what coordinates are energized
        startRight(0, 0, spaceCoordinates, energizedCoordinates);

//        Prints energizedCoordinateArray
        for (int[][] energizedCoordinate : energizedCoordinates) {
            for (int[] ints : energizedCoordinate) {
                int coordinateSum = 0;
                for (int anInt : ints) {
                    if (anInt > 0) coordinateSum++;
                }
                System.out.print(coordinateSum + " ");
            }
            System.out.println();
        }

//        Calculate sum
        int sum = numberOfEnergized(energizedCoordinates);

//        Prints sum
        System.out.println("Sum: " + sum);
    }

//    Moves downwards in coordinateSystem and energizes until symbol is hit, or loop is found
    static void downwards(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates){

        do {
            energizedCoordinates[y][x][3] += 1;
            y++;
            if (y > spaceCoordinates.length - 1) return;
            if (energizedCoordinates[y][x][3] > 1 && energizedCoordinates[y-1][x][3] > 1) return;
        } while (spaceCoordinates[y][x].equals(".") ||
                 spaceCoordinates[y][x].equals("|"));

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "\\" -> right(x, y, spaceCoordinates, energizedCoordinates);
            case "/" -> left(x, y, spaceCoordinates, energizedCoordinates);
            case "-" -> {
                left(x,y, spaceCoordinates, energizedCoordinates);
                right(x, y, spaceCoordinates, energizedCoordinates);
            }
        }
    }

//    Moves upwards in coordinateSystem and energizes until symbol is hit, or loop is found
    static void upwards(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates){

        do {
            energizedCoordinates[y][x][1] += 1;
            y--;
            if (y == -1) return;
            if (energizedCoordinates[y][x][1] > 1 && energizedCoordinates[y+1][x][1] > 1) return;
        } while (spaceCoordinates[y][x].equals(".") ||
                 spaceCoordinates[y][x].equals("|"));

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "\\" -> left(x, y, spaceCoordinates, energizedCoordinates);
            case "/" -> right(x, y, spaceCoordinates, energizedCoordinates);
            case "-" -> {
                left(x,y, spaceCoordinates, energizedCoordinates);
                right(x, y, spaceCoordinates, energizedCoordinates);
            }
        }
    }

//    Moves left in coordinateSystem and energizes until symbol is hit, or loop is found
    static void left(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates){

        do {
            energizedCoordinates[y][x][2] += 1;
            x--;
            if (x < 0) return;
            if (energizedCoordinates[y][x][2] > 1 && energizedCoordinates[y][x+1][2] > 1) return;
        } while (spaceCoordinates[y][x].equals(".") ||
                 spaceCoordinates[y][x].equals("-"));

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "|" -> {
                upwards(x, y, spaceCoordinates, energizedCoordinates);
                downwards(x, y, spaceCoordinates, energizedCoordinates);
            }
            case "\\" -> upwards(x, y, spaceCoordinates, energizedCoordinates);
            case "/" -> downwards(x, y, spaceCoordinates, energizedCoordinates);
        }
    }

//    Moves right in coordinateSystem and energizes until symbol is hit, or loop is found
    static void right(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates){

        do {
            energizedCoordinates[y][x][0] += 1;
            x++;
            if (x > spaceCoordinates[y].length - 1) return;
            if (energizedCoordinates[y][x][0] > 1 && energizedCoordinates[y][x-1][0] > 1) return;
        } while (spaceCoordinates[y][x].equals(".") ||
                 spaceCoordinates[y][x].equals("-"));

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "|" -> {
                upwards(x, y, spaceCoordinates, energizedCoordinates);
                downwards(x, y, spaceCoordinates, energizedCoordinates);
            }
            case "\\" -> downwards(x, y, spaceCoordinates, energizedCoordinates);
            case "/" -> upwards(x, y, spaceCoordinates, energizedCoordinates);
        }
    }

//    Moves right in coordinateSystem and energizes until symbol is hit, starts recursion
    static void startRight(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates) {

        while (spaceCoordinates[y][x].equals(".") ||
               spaceCoordinates[y][x].equals("-")) {
            energizedCoordinates[y][x][0] += 1;
            x++;
            if (x > spaceCoordinates[y].length - 1) {
                return;
            }
        }

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "|" -> {
                upwards(x, y, spaceCoordinates, energizedCoordinates);
                downwards(x, y, spaceCoordinates, energizedCoordinates);
            }
            case "\\" -> downwards(x, y, spaceCoordinates, energizedCoordinates);
            case "/" -> upwards(x, y, spaceCoordinates, energizedCoordinates);
        }
    }

//    Calculates number of energized cells in a 3D coordinate system where the 3D is same location
    static int numberOfEnergized(int[][][] energizedCoordinates){
        int number = 0;
         for (int[][] energizedCoordinate : energizedCoordinates) {
            for (int[] ints : energizedCoordinate) {
                boolean coordinateEnergized = false;
                for (int anInt : ints) {
                    if (anInt > 0) {
                        coordinateEnergized = true;
                        break;
                    }
                }
                if (coordinateEnergized) number++;
            }
        }
        return number;
    }
}
