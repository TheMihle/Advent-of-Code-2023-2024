package year2023.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

// First solution/attempt, using RegEx, fixed bug after completing attempt/solution 2. Solution 1 is probably better
// Should have sorted before doing RegEx
public class Part_1_Solution_1 {
    public static void main(String[] arg){
        List<String> inputLines = fileToArray(getInputPath(Part_1_Solution_1.class));

//        Convert array to an 2D array
        List<ArrayList<String>> cardArray = new ArrayList<>();
        for (int i = 0; i < inputLines.size(); i++) {
            String[] tempArray = inputLines.get(i).split(" ");
            cardArray.add(new ArrayList<>());
            for (String string : tempArray) {
                cardArray.get(i).add(string);
            }
        }

//        Patterns for recognizing number of letters.
        Pattern fivePattern = Pattern.compile("([A-Z1-9]).{0,3}\\1.{0,3}\\1.{0,3}\\1.{0,3}\\1");
        Pattern fourPattern = Pattern.compile("([A-Z1-9]).{0,3}\\1.{0,3}\\1.{0,3}\\1");
        Pattern threePattern = Pattern.compile("([A-Z1-9]).{0,3}\\1.{0,3}\\1");
        Pattern twoPattern = Pattern.compile("([A-Z1-9]).{0,3}\\1");

//        Sorted map for hand value and bids
        Map<Long, Integer> handValueBidMap = new TreeMap<>();

        for (List<String> stringArray : cardArray) {
            Matcher fiveMatcher = fivePattern.matcher(stringArray.getFirst());
            Matcher fourMatcher = fourPattern.matcher(stringArray.getFirst());
            Matcher threeMatcher = threePattern.matcher(stringArray.getFirst());
            Matcher twoMatcher = twoPattern.matcher(stringArray.getFirst());

//            Finds hand value with combination bonus
            long key = handValue(stringArray.get(0));

//            Five of a kind
            if (fiveMatcher.find()){
                key += 60000000000L;

//                Four of a kind
            } else if (fourMatcher.find()){
                key += 50000000000L;

//                Tree of a kind
            } else if (threeMatcher.find()){
                key += 30000000000L;

//                Four of a kind/full house
                twoMatcher.find();
                if (!twoMatcher.group(1).equals(threeMatcher.group(1))){
                        key += 10000000000L;
                    }
                while (twoMatcher.find(twoMatcher.end(1))){
                    if (!twoMatcher.group(1).equals(threeMatcher.group(1))){
                        key += 10000000000L;
                    }
                }

//                One/Two pairs
            } else if (twoMatcher.find(0)) {
                key += 10000000000L;
                if (twoMatcher.find(twoMatcher.end(1))) {
                    key += 10000000000L;
                }
            }

//            Add bid amount to sorted map with a hands value as key
            handValueBidMap.put(key, Integer.parseInt(stringArray.get(1)));
        }

//        Calculates sum based on bid amount and rank
        int rank = 1;
        long totalWinnings = 0L;
        for (Map.Entry<Long, Integer> entry : handValueBidMap.entrySet()){
            totalWinnings += (long) entry.getValue() * rank;
            rank++;
        }

        System.out.println("Day 7, Part 1, Total winnings: " + totalWinnings);
    }

//    Calculates a hand value based on the individual cards only
    private static long handValue(String hand){
        long handValue = 0;
        int multiplier = 100000000;
        for (char character : hand.toCharArray()){
            handValue += (long )characterValue(character) * multiplier;
            multiplier /= 100;
        }
        return handValue;
    }

//    Switch for the int value of cards
    private static int characterValue(char character){
        return switch (character){
            case 'A' -> 14;
            case 'K' -> 13;
            case 'Q' -> 12;
            case 'J' -> 11;
            case 'T' -> 10;
            default -> Character.getNumericValue(character);
        };
    }
}
