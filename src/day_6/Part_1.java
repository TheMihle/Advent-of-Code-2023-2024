package day_6;

import java.util.ArrayList;

import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray("src\\day_6\\input.txt");

//        Removes everything except the numbers and one space in between and then  splits and converts to int arrays
        for (int i = 0; i < inputLines.size(); i++) {
            inputLines.set(i, inputLines.get(i).replaceFirst(".+:", ""));
            inputLines.set(i, inputLines.get(i).trim().replaceAll(" +", " "));
        }

        String[] sTimeArray = inputLines.get(0).split(" ");
        String[] sDistanceArray = inputLines.get(1).split(" ");

        int[] timeArray = new int[sTimeArray.length];
        int[] distanceArray = new int[sDistanceArray.length];
        for (int i = 0; i < sTimeArray.length; i++) {
            timeArray[i] = Integer.parseInt(sTimeArray[i]);
            distanceArray[i] = Integer.parseInt(sDistanceArray[i]);
        }

        int sum = 1;

        // TODO: 06.12.2023 Maybe use math instead of loop?
//        Calculate how many possible ways to win
        for (int i = 0; i < timeArray.length; i++) {
            int maxTime = 0; int minTime = 0;
            for (int j = timeArray[i]; j > 0; j--) {
                if ((timeArray[i]-j) * j > distanceArray[i]){
                    maxTime = j;
                    break;
                }
            }

            for (int j = 0; j < timeArray[i]; j++) {
                if ((timeArray[i]-j) * j > distanceArray[i]){
                    minTime = j;
                    break;
                }
            }

//            Calculates sum
            sum *= (maxTime-minTime)+1;
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }
}
