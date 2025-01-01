package year2023.day09;

import java.util.Arrays;
import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Part_2 {
    public static void main(String[] args) {
        List<String> inputLines = fileToArray(getInputPath(Part_2.class));

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
            int previousValue = sequence[0] - rowCalc(sequence);
            System.out.println(previousValue);

//            Calculates sum
            extrapolatedValuesSum += previousValue;
        }

        System.out.println("Day 9, Part 2, Sum of extrapolated values: " + extrapolatedValuesSum);
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
            int previousValue = calcArray[0] - rowCalc(calcArray);
            System.out.println(previousValue);
            return previousValue;
        }
    }
}
