package year2023.day11;

import java.awt.Point;
import java.util.ArrayList;

import static common.ImportFile.fileTo2DArray;

public class Part_2 {
    public static void main(String[] args) {

//        Import file as 2D array
        String[][] spaceCoordinates= fileTo2DArray("src\\year2023\\day11\\input.txt");

        long sum = 0;

//        Arrays for columns and rows where space has expanded
        ArrayList<Integer> expSpaceX = new ArrayList<>();
        ArrayList<Integer> expSpaceY = new ArrayList<>();

//        Finds where space is expanded horizontally
        for (int x = 0; x < spaceCoordinates[0].length; x++) {
            boolean galaxies = false;
            for (String[] rowArray : spaceCoordinates) {
                if (rowArray[x].equals("#")) {
                    galaxies = true;
                    break;
                }
            }
//            If empty column, saves it to array
            if (!galaxies) {
                expSpaceX.add(x);
            }
        }

//        Finds where space is expanded vertically
        for (int y = 0; y < spaceCoordinates.length; y++) {
            boolean galaxies = false;
            for (int x = 0; x < spaceCoordinates[y].length; x++) {
                if (spaceCoordinates[y][x].equals("#")) {
                    galaxies = true;
                    break;
                }
            }
//            If empty row, saves it to array
            if (!galaxies) {
                expSpaceY.add(y);
            }
        }

//        Prints expanded space rows columns and rows
//        System.out.println("Columns with expanded space: " + expSpaceX);
//        System.out.println("Rows with expanded space: " + expSpaceY);

//        Creates array with coordinates of the galaxies
        ArrayList<Point> galaxyCoordinates = new ArrayList<>();

        for (int y = 0; y < spaceCoordinates.length; y++) {
            for (int x = 0; x < spaceCoordinates[y].length; x++) {
                if (spaceCoordinates[y][x].equals("#")){
                    galaxyCoordinates.add(new Point(x,y));
                }
            }
        }

//        Prints GalaxyCoordinates
//        System.out.println(galaxyCoordinates);

//        Calculates distance between all galaxies in array
        for (int i = 0; i < galaxyCoordinates.size(); i++) {
            for (int j = i+1; j < galaxyCoordinates.size(); j++) {

//                Adds extra length for every expanded space line it crosses.
                int extraSpace = 0;
//                Optimization
                int yFirstGalaxy = (int) galaxyCoordinates.get(i).getY();
                int ySecondGalaxy = (int) galaxyCoordinates.get(j).getY();
                int xFirstGalaxy = (int) galaxyCoordinates.get(i).getX();
                int xSecondGalaxy = (int) galaxyCoordinates.get(j).getX();

                for (Integer yExpSpace : expSpaceY) {
                    if ((yExpSpace < ySecondGalaxy && yExpSpace > yFirstGalaxy)) {
                        extraSpace += 999999;
                    }
                }
                for (Integer xExpSpace : expSpaceX) {
                    if ((xExpSpace > xSecondGalaxy && xExpSpace < xFirstGalaxy) ||
                            (xExpSpace < xSecondGalaxy && xExpSpace > xFirstGalaxy)) {
                        extraSpace+= 999999;
                    }
                }

//                Calculates sum
                sum += Math.abs(yFirstGalaxy-ySecondGalaxy) + Math.abs(xFirstGalaxy-xSecondGalaxy) + extraSpace;
            }
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }
}
