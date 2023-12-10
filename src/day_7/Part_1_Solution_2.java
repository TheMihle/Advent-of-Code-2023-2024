// Second solution/attempt, no RegEx, using loops and hashmap instead.

package day_7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static common.ImportFile.fileToArray;

public class Part_1_Solution_2 {
    public static void main(String[] arg){

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray("src\\day_7\\input.txt");

//        Convert array to an 2D array
        ArrayList<ArrayList<String>> cardArray = new ArrayList<>();
        for (int i = 0; i < inputLines.size(); i++) {
            String[] tempArray = inputLines.get(i).split(" ");
            cardArray.add(new ArrayList<>());
            for (String string : tempArray) {
                cardArray.get(i).add(string);
            }
        }

//        Sorted map for hand value and bids
        TreeMap<Long, Integer> handMap = new TreeMap<>();

        for (ArrayList<String> stringArray : cardArray) {

//            Hashmap for possible characters
            HashMap<Character, Integer> cardMap = new HashMap<>();
            cardMap.put('2', 0);
            cardMap.put('3', 0);
            cardMap.put('4', 0);
            cardMap.put('5', 0);
            cardMap.put('6', 0);
            cardMap.put('7', 0);
            cardMap.put('8', 0);
            cardMap.put('9', 0);
            cardMap.put('T', 0);
            cardMap.put('J', 0);
            cardMap.put('Q', 0);
            cardMap.put('K', 0);
            cardMap.put('A', 0);

//            Counts characeters and put it in to hashmap
            for (char c : stringArray.get(0).toCharArray()){
                cardMap.put(c, cardMap.get(c)+1);
            }

//            Finds hand value with combination bonus
            long key = 0;
            if (cardMap.containsValue(5)){
                key = 60000000000L + handValue(stringArray.get(0));
            } else if (cardMap.containsValue(4)) {
                key = 50000000000L + handValue(stringArray.get(0));
            } else if (cardMap.containsValue(3) && cardMap.containsValue(2)) {
                key = 40000000000L + handValue(stringArray.get(0));
            } else if (cardMap.containsValue(3)) {
                key = 30000000000L + handValue(stringArray.get(0));
            } else if (cardMap.containsValue(2)) {
                key = handValue(stringArray.get(0));
                for (Map.Entry<Character, Integer> entry : cardMap.entrySet()){
                    if (entry.getValue() == 2){
                        key += 10000000000L;
                    }
                }
            } else {
                key = handValue(stringArray.get(0));
            }

//            Add bid amount to sorted map with a hands value as key
            handMap.put(key, Integer.parseInt(stringArray.get(1)));
        }

//        Calculates sum based on bid amount and rank
        int rank = 1;
        long sum = 0L;
        for (Map.Entry<Long, Integer> entry : handMap.entrySet()){
            sum += (long) entry.getValue() * rank;
            rank++;
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }

//    Calculates a hand value based on the individual cards only
    static long handValue(String hand){
        long handValue = 0;
        int multiplier = 100000000;
        for (char character : hand.toCharArray()){
            handValue += (long )characterValue(character) * multiplier;
            multiplier /= 100;
        }
        return handValue;
    }

//    Switch for the int value of cards
    static int characterValue(char character){
        int value;
        switch (character){
            case 'A' -> value = 14;
            case 'K' -> value = 13;
            case 'Q' -> value = 12;
            case 'J' -> value = 11;
            case 'T' -> value = 10;
            default -> value = Character.getNumericValue(character);
        }
        return value;
    }
}
