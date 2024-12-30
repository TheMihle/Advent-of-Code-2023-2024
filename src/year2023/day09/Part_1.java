package year2023.day09;

import java.util.Arrays;
import java.util.List;

import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {
        List<String> inputLines = fileToArray("src\\year2023\\day09\\input.txt");

        long extrapolatedValuesSum = 0;

        for (String inputLine : inputLines) {

//            Splits string to array and converts to int array
            String[] sSequence = inputLine.split(" ");

            int[] sequence = new int[sSequence.length];
            for (int i = 0; i < sSequence.length; i++) {
                sequence[i] = Integer.parseInt(sSequence[i]);
            }

            System.out.println(Arrays.toString(sequence));

//            Calculates next step via recursive method
            int nextValue = sequence[sequence.length-1] + rowCalc(sequence);
            System.out.println(nextValue);

//            Calculates sum
            extrapolatedValuesSum += nextValue;
        }

        System.out.println("Day 9, Part 1, Sum of extrapolated values: " + extrapolatedValuesSum);
    }

//    Calculating next row, returns calculated next step
    public static int rowCalc(int[] inputArray){
        int[] calcArray = new int[inputArray.length-1];
        for (int i = 1; i < inputArray.length; i++) {
            calcArray[i-1] = inputArray[i] - inputArray[i-1];
        }
        System.out.println(Arrays.toString(calcArray));

        boolean allZero = Arrays.stream(calcArray).allMatch((i) -> i == 0);
        if (allZero){
            return 0;
        } else {
            int nextValue = calcArray[calcArray.length-1] + rowCalc(calcArray);
            System.out.println(nextValue);
            return nextValue;
        }
    }
}
