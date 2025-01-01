package year2023.day04;

import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    public static void main(String[] arg) {
        List<String> inputLines = fileToArray(getInputPath(Part_1.class));

        int totalPoints = 0;

        for (int i = 0; i < inputLines.size(); i++) {

//            Removes card number from strings
            String[] gameData = inputLines.get(i).split("\\|");
            gameData[0] = gameData[0].replaceFirst(".+:", "");

//            Removes extra spaces and splits strings on space in to new arrays
            for (int j = 0; j < gameData.length; j++) {
                gameData[j] = gameData[j].replace("  ", " ")
                                         .replaceFirst(" ", "");
            }

            String[] sWinningNumbers = gameData[0].split(" ");
            String[] sYourNumbers = gameData[1].split(" ");

//            Converts string arrays to int
            int[] winningNumbers = new int[sWinningNumbers.length];
            for (int j = 0; j < sWinningNumbers.length; j++) {
                winningNumbers[j] = Integer.parseInt(sWinningNumbers[j]);
            }

            int[] yourNumbers = new int[sYourNumbers.length];
            for (int j = 0; j < sYourNumbers.length; j++) {
                yourNumbers[j] = Integer.parseInt(sYourNumbers[j]);
            }

//            Finds number of matches
            int matches = 0;
            for (int winningNumber : winningNumbers) {
                for (int yourNumber : yourNumbers) {
                    if (winningNumber == yourNumber) matches++;
                }
            }

//            Calculates sum
            totalPoints += (int) Math.pow(2, matches - 1);
        }

        System.out.println("Day 4, Part 1, Total value of points: " + totalPoints);
    }
}
