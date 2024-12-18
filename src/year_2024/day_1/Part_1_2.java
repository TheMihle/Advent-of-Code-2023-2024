package year_2024.day_1;


import java.util.ArrayList;
import java.util.Comparator;

import static common.ImportFile.*;

public class Part_1_2 {
    public static void main(String[] args) {
        ArrayList<String> input = fileToArray("src\\year_2024\\day_1\\input.txt");

//        Creates two arrays for the two lists
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (String line : input) {
            String[] tempArray = line.split(" {3}");
            list1.add(Integer.parseInt(tempArray[0]));
            list2.add(Integer.parseInt(tempArray[1]));
        }

//        Sort the lists
        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());

//        Calculates total distance for Part 1
        int totalDistance = 0;
        for (int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i)-list2.get(i));
        }

        System.out.println("Day 1, Part 1, Total distance: " + totalDistance);

//        Calculates similarity core for Part 2
        int similarityScore = 0;
        for (Integer integerList1 : list1) {
            int i = 0;
            for (Integer integerList2 : list2) {
                if (integerList1.equals(integerList2)) i++;
            }
            similarityScore += integerList1 * i;
        }

        System.out.println("Day 1, Part 2, Similarity core: " + similarityScore);

    }
}
