package year_2024.day_11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import static common.ImportFile.fileToSplittedString;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as array
        String[] inputArray = fileToSplittedString("src\\year_2024\\day_11\\input.txt", " ");

//        Convert to LinkedList and create a listIterator
        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(inputArray));
        ListIterator<String> listIterator;

//        Calculates number of stones based on rules provided
        for (int i = 0; i < 25; i++) {
            listIterator = linkedList.listIterator();

            while (listIterator.hasNext()) {
                String stringValue = listIterator.next();

                if (stringValue.equals("0")) listIterator.set("1");  // Replace 0 with 1
                else if (stringValue.length() % 2 == 0) {            // If even number, divide into two halves, etc 2000 -> 20, 00 -> 20, 0
                    listIterator.remove();
                    listIterator.add(Long.toString(Long.parseLong(stringValue.substring(0, stringValue.length()/2))));
                    listIterator.add(Long.toString(Long.parseLong(stringValue.substring(stringValue.length()/2))));
                } else {                                             // Multiply by 2024
                    listIterator.set(Long.toString(Long.parseLong(stringValue) * 2024));
                }
            }
        }

//        Prints number of stones in the end result
        System.out.println("Day 11, Part 1, Total number of stones: " + linkedList.size());
    }
}
