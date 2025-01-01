package year2023.day15;

import static common.ImportFile.fileToSplittedString;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    public static void main(String[] args) {
        String[] sequence = fileToSplittedString(getInputPath(Part_1.class), ",");

        int sum = 0;

        for (String step : sequence) {

//            Calculates hash value of a step
            int currentValue = hash(step);

//            Calculates sum
            sum += currentValue;
        }

        System.out.println("Day 15, Part 1, Sum of the results: " + sum);
    }

//    Calculates hash value of a label
    public static int hash(String label) {
        int value = 0;
        for (char character : label.toCharArray()) {
            value += character;
            value *= 17;
            value %= 256;
        }
        return value;
    }
}
