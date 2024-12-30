package year2023.day04;

import java.util.Arrays;
import java.util.List;

import static common.ImportFile.fileToArray;

public class Part_2 {
    public static void main(String[] arg) {
        List<String> inputLines = fileToArray("src\\year2023\\day04\\input.txt");

//        Array for number of copies of each cards
        int[] cardCounts = new int[inputLines.size()];
        Arrays.fill(cardCounts, 1);

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

//            Ads copies of following cards
            if (i + matches > inputLines.size()) matches = (inputLines.size() - 1) - i;
            for (int j = 0; j < matches; j++) {
                cardCounts[i + j + 1] += cardCounts[i];
            }
        }

//        Calculates sum
        int totalCardCount = 0;
        for (int cardCount : cardCounts) {
            totalCardCount += cardCount;
        }

        System.out.println("Day 4, Part 2, Total number of Scratchcards: " + totalCardCount);
    }
}
