package year2023.day06;

import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Part_2 {
    public static void main(String[] args) {
        List<String> inputLines = fileToArray(getInputPath(Part_2.class));

//        Removes everything except the numbers and converts to int/Long
        for (int i = 0; i < inputLines.size(); i++) {
            inputLines.set(i, inputLines.get(i).replaceFirst(".+:", ""));
            inputLines.set(i, inputLines.get(i).trim().replaceAll(" +", ""));
        }

        int time = Integer.parseInt(inputLines.get(0));
        long distance = Long.parseLong(inputLines.get(1));

        // TODO: 06.12.2023 Maybe use math instead of loops?
//       Calculates max and minimum time
        int maxTime = 0;
        int minTime = 0;
        for (int j = time; j > 0; j--) {
            if ((long) (time - j) * j > distance) {
                maxTime = j;
                break;
            }
        }

        for (int j = 0; j < time; j++) {
            if ((long) (time - j) * j > distance) {
                minTime = j;
                break;
            }
        }

        System.out.println("Day 6, Part 2, Number of ways to beat the record: " + (maxTime - minTime + 1));
    }
}
