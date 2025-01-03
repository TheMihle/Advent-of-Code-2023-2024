package year2024.day19;

import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    public static void main(String[] args) {
        List<String> designs = fileToArray(getInputPath(Part_1.class));

//        Extract patterns
        String[] patterns = designs.getFirst().split(", " );
        designs.removeFirst();
        designs.removeFirst();

//        Creates regex pattern for the patterns
        String pattern = "(" + String.join("|", patterns) + ")*";

//        Counts possible designs with provided patterns
        int possibleDesings = 0;
        for (String design : designs) {
            if (design.matches(pattern)) possibleDesings++;
        }

        System.out.println("Day 19, Part 1, Number of possible designs: " + possibleDesings);
    }
}
