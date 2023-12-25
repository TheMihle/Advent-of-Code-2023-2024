package day_11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray("src\\day_11\\input.txt");

//        Create an 2D array
        ArrayList<ArrayList<String>> spaceCoordinates= new ArrayList<>();

        for (String inputLine : inputLines) {
            String[] horizontal = inputLine.split("");
            ArrayList<String> tempList = new ArrayList<>(Arrays.asList(horizontal));
            spaceCoordinates.add(tempList);
        }

        long sum = 0;

//        Arrays for columns and rows where space has expanded
        ArrayList<Integer> expSpaceX = new ArrayList<>();
        ArrayList<Integer> expSpaceY = new ArrayList<>();

//        Finds where space is expanded horizontally
        for (int x = 0; x < spaceCoordinates.get(0).size(); x++) {
            boolean galaxies = false;
            for (ArrayList<String> rowArray : spaceCoordinates) {
                if (rowArray.get(x).equals("#")) {
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
        for (int y = 0; y < spaceCoordinates.size(); y++) {
            boolean galaxies = false;
            for (int x = 0; x < spaceCoordinates.get(y).size(); x++) {
                if (spaceCoordinates.get(y).get(x).equals("#")) {
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

        for (int y = 0; y < spaceCoordinates.size(); y++) {
            for (int x = 0; x < spaceCoordinates.get(y).size(); x++) {
                if (spaceCoordinates.get(y).get(x).equals("#")){
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
                        extraSpace += 1;
                    }
                }
                for (Integer xExpSpace : expSpaceX) {
                    if ((xExpSpace > xSecondGalaxy && xExpSpace < xFirstGalaxy) ||
                            (xExpSpace < xSecondGalaxy && xExpSpace > xFirstGalaxy)) {
                        extraSpace+= 1;
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
