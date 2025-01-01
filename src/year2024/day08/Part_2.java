package year2024.day08;

import java.awt.*;
import java.util.List;
import java.util.*;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

 /*
     Storing antenna coords in map and then calculate antiondes with that, is extra code, than double looping,
     but is also faster and easier to split into methods
 */
public class Part_2 {
    public static void main(String[] args) {
        String[][] antennaMap = fileTo2DArray(getInputPath(Part_2.class ));

        Map<String, List<Point>> antennaCoords = new HashMap<>();
        Set<Point> antinodes = new HashSet<>();

//        Add antenna coords to hashmap based on antenna frequency
        addAllAntennaCoords(antennaMap, antennaCoords);

//        Find all antinodes and add to Set
        findAntinodes(antennaMap, antennaCoords, antinodes);

        System.out.println("Day 8, Part 2, Number of unique antinodes: " + antinodes.size());
    }

//    Add antenna coords to hashmap based on antenna frequency
    public static void addAllAntennaCoords(String[][] antennaMap, Map<String, List<Point>> antennaCoords) {
        for (int row = 0; row < antennaMap.length; row++) {
            for (int col = 0; col < antennaMap[row].length; col++) {
                if (!antennaMap[row][col].equals(".")) {
                    antennaCoords.computeIfAbsent(antennaMap[row][col], n -> new ArrayList<>()).add(new Point(col, row));
                }
            }
        }
    }

//    Find all antinodes and add to Set
    public static void findAntinodes(String[][] antennaMap, Map<String, List<Point>> antennaCoords, Set<Point> antinodes) {
        for (List<Point> list : antennaCoords.values()) {
            for (Point antenna1 : list) {
                for (Point antenna2 : list) {
                    if (antenna1.equals(antenna2)) continue;
                    calcAntinodes(antinodes, antennaMap, antenna1, antenna1.x - antenna2.x, antenna1.y - antenna2.y);
                }
            }
        }
    }

//    Calulates antiodes based on one pair of antennas
    private static void calcAntinodes(Set<Point> antinodes,String[][] antennaMap, Point point, int deltaX, int deltaY) {
        int x = point.x;
        int y = point.y;
        while (rangeCheck(antennaMap, x, y)) {
            antinodes.add(new Point(x, y));
            x += deltaX;
            y += deltaY;
        }
    }

}
