package year2024.day08;

import java.awt.*;
import java.util.*;
import java.util.List;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

 /*
     Storing antenna coords in map and then calculate antiondes with that, is extra code, than double looping,
     but is also faster and easier to split into methods
 */
public class Part_1 {
    public static void main(String[] args) {
        String[][] antennaMap = fileTo2DArray(getInputPath(Part_1.class));

        Map<String, List<Point>> antennaCoords = new HashMap<>();
        Set<Point> antinodes = new HashSet<>();

//        Add antenna coords to hashmap based on antenna frequency
        addAllAntennaCoords(antennaMap, antennaCoords);

//        Find all antinodes and add to Set
        findAntinodes(antennaMap, antennaCoords, antinodes);

        System.out.println("Day 8, Part 1, Number of unique antinodes: " + antinodes.size());
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
                    Point antinode = new Point(antenna1.x + (antenna1.x - antenna2.x), antenna1.y + (antenna1.y - antenna2.y));
                    if (rangeCheck(antennaMap, antinode.x, antinode.y)) antinodes.add(antinode);
                }
            }
        }
    }
}
