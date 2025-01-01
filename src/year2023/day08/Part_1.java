package year2023.day08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    public static void main(String[] args){
        List<String> inputLines = fileToArray(getInputPath(Part_1.class));

//        Patterns for recognizing 3 letters
        Pattern pattern = Pattern.compile("[A-Z]{3}");
        Matcher matcher;

//        Converts directions to an array with ints for indexes 0 = left, 1 = right.
        int[] directions = new int[inputLines.getFirst().length()];
        int iteration = 0;
        for (Character c : inputLines.getFirst().toCharArray()){
            if (c.equals('L')){
                directions[iteration] = 0;
            } else if (c.equals('R')){
                directions[iteration] = 1;
            }
            iteration++;
        }

//        Converts input array to hashmap, first set of letters as keys and the two others in array as value.
        Map<String, String[]> map = new HashMap<>();
        for (int i = 2; i < inputLines.size(); i++) {
            matcher = pattern.matcher(inputLines.get(i));
            matcher.find();
            String key = matcher.group();
            matcher.find();
            String left = matcher.group();
            matcher.find();
            String right = matcher.group();

            map.put(key, new String[]{left, right});
        }

//        Finds the number of steps between AAA and ZZZ via two loops
        String nextKey = "AAA";
        boolean end = false;
        int stepsCount = 0;
        while (!end){

//            Direction/step loop
            for (int direction : directions) {
                String[] destinations = map.get(nextKey);
                nextKey = destinations[direction];
            stepsCount++;
            }
            if (nextKey.equals("ZZZ")) end = true;
        }

        System.out.println("Day 8, Part 1, Number of steps required to reach ZZZ: " + stepsCount);
    }
}
