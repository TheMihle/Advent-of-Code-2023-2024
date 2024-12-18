package year_2024.day_1;


import java.util.ArrayList;
import java.util.Comparator;

import static common.ImportFile.*;

public class Part_1 {
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

//        Calculates total Part 1
        int sumPart1 = 0;
        for (int i = 0; i < list1.size(); i++) {
            sumPart1 += Math.abs(list1.get(i)-list2.get(i));
        }

//        Print output Part 1
        System.out.println("Day 1, Part 1, Total distance: " + sumPart1);

//        Calculates total Part 2
        int sumPart2 = 0;
        for (Integer integerList1 : list1) {
            int i = 0;
            for (Integer integerList2 : list2) {
                if (integerList1.equals(integerList2)) i++;
            }
            sumPart2 += integerList1 * i;
        }

//        Print output Part 2
        System.out.println("Day 1, Part 2, Similarity core: " + sumPart2);

    }
}
