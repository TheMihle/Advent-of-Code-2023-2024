package year2023.day07;

import java.util.*;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

// May be overcomplicated for what it needs to, but it works
public class Part_2 {
    public static void main(String[] arg){
        List<String> inputLines = fileToArray(getInputPath(Part_2.class));

//        Convert array to an 2D array
        List<ArrayList<String>> cardArray = new ArrayList<>();
        for (int i = 0; i < inputLines.size(); i++) {
            String[] tempArray = inputLines.get(i).split(" ");
            cardArray.add(new ArrayList<>());
            for (String string : tempArray) {
                cardArray.get(i).add(string);
            }
        }

//        Sorted map for hand value and bids
        Map<Long, Integer> handBidMap = new TreeMap<>();

        for (List<String> stringArray : cardArray) {

//            Hashmap for possible characters
            Map<Character, Integer> cardMap = generateCardMap(0);

//            Counts characters and put it in to hashmap
            for (char c : stringArray.getFirst().toCharArray()){
                cardMap.put(c, cardMap.get(c)+1);
            }

//            Number of Jokers
            int jokers = cardMap.get('J');
            cardMap.put('J', 0);

//            Finds the other, first character with the most characters, if none, A
            char mostCommonChar = 'A';
            Integer numberMostCommonChar = 0;
            for (Map.Entry<Character, Integer> entry : cardMap.entrySet()){
                if (entry.getValue() > numberMostCommonChar){
                    numberMostCommonChar = entry.getValue();
                    mostCommonChar = entry.getKey();
                }
            }

//            Adds number of Jokers to the other character its most of
            if (jokers > 0){
                cardMap.put(mostCommonChar, numberMostCommonChar + jokers);
            }

//            Finds hand value with combination bonus
            long key = handValue(stringArray.get(0));

//            Five of a kind
            if (cardMap.containsValue(5)){
                key += 60000000000L;

//                Four of a kind
            } else if (cardMap.containsValue(4)) {
                key += 50000000000L;

//                Full house
            } else if (cardMap.containsValue(3) && cardMap.containsValue(2)) {
                key += 40000000000L;

//                Tree of a kind
            } else if (cardMap.containsValue(3)) {
                key += 30000000000L;
//
//                One/two pairs
            } else if (cardMap.containsValue(2)) {
                for (Map.Entry<Character, Integer> entry : cardMap.entrySet()){
                    if (entry.getValue() == 2){
                        key += 10000000000L;
                    }
                }
            }

//            Add bid amount to sorted map with a hands value as key
            handBidMap.put(key, Integer.parseInt(stringArray.get(1)));
        }

//        Calculates sum based on bid amount and rank
        int rank = 1;
        long totalWinnings = 0L;
        for (Map.Entry<Long, Integer> entry : handBidMap.entrySet()){
            totalWinnings += (long) entry.getValue() * rank;
            rank++;
        }

        System.out.println( "Day 7, Part 1, Total winnings: " + totalWinnings);
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
            case 'J' -> 1;
            case 'T' -> 10;
            default -> Character.getNumericValue(character);
        };
    }

    private static Map<Character, Integer> generateCardMap(Integer value) {
        char[] cards = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

        Map<Character, Integer> cardMap = new HashMap<>();
        for (char card : cards) {
            cardMap.put(card, value);
        }
        return cardMap;
    }
}
