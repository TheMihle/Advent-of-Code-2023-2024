package year2023.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args){

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray("src\\year2023\\day08\\input.txt");

//        Patterns for recognizing 3 letters
        Pattern pattern = Pattern.compile("[A-Z]{3}");
        Matcher matcher;

//        Converts directions to an array with ints for indexes 0 = left, 1 = right.
        int[] directions = new int[inputLines.get(0).length()];
        int iteraton = 0;
        for (Character c : inputLines.get(0).toCharArray()){
            if (c.equals('L')){
                directions[iteraton] = 0;
            } else if (c.equals('R')){
                directions[iteraton] = 1;
            }
            iteraton++;
        }

//        Converts input array to hashmap, first set of letters as keys and the two others in array as value.
        HashMap<String, String[]> map = new HashMap<>();
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
        int numberOfSteps = 0;
        while (!end){

//            Direction/step loop
            for (int direction : directions) {
                String[] destionations = map.get(nextKey);
                nextKey = destionations[direction];
            numberOfSteps++;
            }
            if (nextKey.equals("ZZZ")) end = true;
        }

//        Prints put number of steps
        System.out.println(numberOfSteps);
    }
}
