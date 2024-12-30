package year2024.day11;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static common.ImportFile.fileToSplittedString;

public class Part_2 {
    public static void main(String[] args) {

//        Import file as an array
        String[] inputArray = fileToSplittedString("src\\year2024\\day11\\input.txt", " ");

//        Convert to HashMap
        Map<String, Long> map = new HashMap<>();
        for (String string : inputArray) map.put(string, 1L);

//        Calculates stones based on rules provided, first time using Stream
//        Considered extracting the if else block to a method but decided not to.
        for (int i = 0; i < 75; i++) {
            Map<String, Long> tempMap = map.entrySet().stream().flatMap(entry -> {
                if (entry.getKey().equals("0")) {                       //  Replace 0 with 1
                    return Stream.of(Map.entry("1", entry.getValue()));
                } else if (entry.getKey().length() % 2 == 0) {          // If even number, divide into two halves, etc 2000 -> 20, 00 -> 20, 0
                    return Stream.of(
                            Map.entry(Long.toString(Long.parseLong(entry.getKey().substring(0, entry.getKey().length()/2))), entry.getValue()),
                            Map.entry(Long.toString(Long.parseLong(entry.getKey().substring(entry.getKey().length()/2))), entry.getValue()));
                } else {                                                // Multiply by 2024
                    return Stream.of(Map.entry(Long.toString(Long.parseLong(entry.getKey()) * 2024), entry.getValue()));
                }
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
            map = new HashMap<>(tempMap);
        }

//        Printing key value pair in hashmap
//        map.forEach((key, value) -> {
//            System.out.println("Key: " + key + " Value: " + value);
//        });

//        Calculates the total number of stones
        long stoneCount = map.values().stream().mapToLong(l -> l).sum();

        System.out.println("Day 11, Part 2, Total number of stones: " + stoneCount);
    }
}
