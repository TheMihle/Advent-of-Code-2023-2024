package day_6;

import java.util.ArrayList;

import static common.ImportFile.fileToArray;

public class Part_2 {
    public static void main(String[] args) {

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray("src\\day_6\\input.txt");

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

//        Calculates and prints sum
        System.out.println("Sum: " + (maxTime - minTime + 1));
    }
}
