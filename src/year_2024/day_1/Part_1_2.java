package year_2024.day_1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static common.ImportFile.*;

public class Part_1_2 {
    public static void main(String[] args) {
        List<String> inputArray = fileToArray("src\\year_2024\\day_1\\input.txt");

//        Creates two arrays for the two lists
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for (String line : inputArray) {
            String[] tempArray = line.split(" {3}");
            leftList.add(Integer.parseInt(tempArray[0]));
            rightList.add(Integer.parseInt(tempArray[1]));
        }

//        Sort the lists
        leftList.sort(Comparator.naturalOrder());
        rightList.sort(Comparator.naturalOrder());

//        Calculates total distance for Part 1
        int totalDistance = 0;
        for (int i = 0; i < leftList.size(); i++) {
            totalDistance += Math.abs(leftList.get(i)-rightList.get(i));
        }

        System.out.println("Day 1, Part 1, Total distance: " + totalDistance);

//        Calculates similarity core for Part 2
        int similarityScore = 0;
        for (Integer integerList1 : leftList) {
            int i = 0;
            for (Integer integerList2 : rightList) {
                if (integerList1.equals(integerList2)) i++;
            }
            similarityScore += integerList1 * i;
        }

        System.out.println("Day 1, Part 2, Similarity core: " + similarityScore);

    }
}
