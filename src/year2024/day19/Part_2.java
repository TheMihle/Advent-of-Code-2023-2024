package year2024.day19;

import java.util.*;

import static common.ImportFile.fileToArray;

public class Part_2 {
    public static void main(String[] args) {
        List<String> designs = fileToArray("src\\year2024\\day19\\input.txt");

//        Extract patterns, add to HashSet
        String[] patterns = designs.getFirst().split(", " );
        designs.removeFirst();
        designs.removeFirst();
        Set<String> hashPatterns = new HashSet<>(Arrays.asList(patterns));

        int longestPattern = 0;
        for (String pattern : patterns) {
            if (pattern.length() > longestPattern) longestPattern = pattern.length();
        }

//        Counts possible combinations with provided patterns with memory
        long possibleCombinations = 0;
        Map<String, Long> memory = new HashMap<>();

        for (String design : designs) {
            possibleCombinations += findMatch(design, hashPatterns, memory, longestPattern);
        }

        System.out.println("Day 19, Part 2, Number of possible design combinations: " + possibleCombinations);
    }

//    Recursive method that finds matches at the start of the design, calls itself recursively on the next part of the design.
//    Returns number of possible combinations.
    private static long findMatch(String subDesign, Set<String> patterns, Map<String, Long> memory, int maxLength) {
        if (subDesign.isEmpty()) return 1;
//        Skips if substring have already been calculated before, happens A LOT of times
        if (memory.containsKey(subDesign)) return memory.get(subDesign);

        long combinations = 0;
        for (int i = 1; i <= maxLength; i++) {
            if (i > subDesign.length()) continue;
            if (patterns.contains(subDesign.substring(0, i))) {
                combinations += findMatch(subDesign.substring(i), patterns, memory, maxLength);
            }
        }

//        Save new calculations of number combinations
        memory.put(subDesign, combinations);
        return combinations;
    }
}
