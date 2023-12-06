package day_4;

import java.util.ArrayList;

import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] arg) {

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray("src\\day_4\\input.txt");

        int sum = 0;

        for (int i = 0; i < inputLines.size(); i++) {

//            Removes card number from strings
            String[] gameData = inputLines.get(i).split("\\|");
            gameData[0] = gameData[0].replaceFirst(".+:", "");

//            Removes extra spaces and splits strings on space in to new arrays
            for (int j = 0; j < gameData.length; j++) {
                gameData[j] = gameData[j].replace("  ", " ")
                                         .replaceFirst(" ", "");
            }
            String[] winningNumbersString = gameData[0].split(" ");
            String[] yourNumbersString = gameData[1].split(" ");

//            Converts string arrays to int
            int[] winningNumbers = new int[winningNumbersString.length];
            for (int j = 0; j < winningNumbersString.length; j++) {
                winningNumbers[j] = Integer.parseInt(winningNumbersString[j]);
            }

            int[] yourNumbers = new int[yourNumbersString.length];
            for (int j = 0; j < yourNumbersString.length; j++) {
                yourNumbers[j] = Integer.parseInt(yourNumbersString[j]);
            }

//            Finds number of matches
            int matches = 0;
            for (int winningNumber : winningNumbers) {
                for (int yourNumber : yourNumbers) {
                    if (winningNumber == yourNumber) matches++;
                }
            }

//            Calucaltes the sum
            sum += (int) Math.pow(2, matches - 1);
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }
}
