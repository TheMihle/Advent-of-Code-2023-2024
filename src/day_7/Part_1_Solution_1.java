// First solution/attempt, using RegEx, fixed bug after completing attempt/solution 2

package day_7;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.ImportFile.fileToArray;

public class Part_1_Solution_1 {
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

//        Patterns for recognizing number of letters.
        Pattern fivePattern = Pattern.compile("([A-Z1-9]).{0,3}\\1.{0,3}\\1.{0,3}\\1.{0,3}\\1");
        Pattern fourPattern = Pattern.compile("([A-Z1-9]).{0,3}\\1.{0,3}\\1.{0,3}\\1");
        Pattern threePattern = Pattern.compile("([A-Z1-9]).{0,3}\\1.{0,3}\\1");
        Pattern twoPattern = Pattern.compile("([A-Z1-9]).{0,3}\\1");

//        Sorted map for hand value and bids
        TreeMap<Long, Integer> handMap = new TreeMap<>();

        for (ArrayList<String> stringArray : cardArray) {
            Matcher fiveMatcher = fivePattern.matcher(stringArray.get(0));
            Matcher fourMatcher = fourPattern.matcher(stringArray.get(0));
            Matcher threeMatcher = threePattern.matcher(stringArray.get(0));
            Matcher twoMatcher = twoPattern.matcher(stringArray.get(0));

//            Finds hand value with combination bonus
            long key = 0;
            if (fiveMatcher.find()){
                key = 60000000000L + handValue(stringArray.get(0));
            } else if (fourMatcher.find()){
                key = 50000000000L + handValue(stringArray.get(0));
            } else if (threeMatcher.find()){
                key = 30000000000L + handValue(stringArray.get(0));
                twoMatcher.find();
                if (!twoMatcher.group(1).equals(threeMatcher.group(1))){
                        key += 10000000000L;
                    }
                while (twoMatcher.find(twoMatcher.end(1))){
                    if (!twoMatcher.group(1).equals(threeMatcher.group(1))){
                        key += 10000000000L;
                    }
                }
            } else if (twoMatcher.find(0)) {
                key = 10000000000L + handValue(stringArray.get(0));
                if (twoMatcher.find(twoMatcher.end(1))) {
                    key += 10000000000L;
                }
            } else {
                key = handValue(stringArray.get(0));
            }

//            Add bid amount to sorted map with a hands value as key
            handMap.put(key, Integer.parseInt(stringArray.get(1)));
        }

//        Calculates sum based on bid amount and rank
        System.out.println(handMap);
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
