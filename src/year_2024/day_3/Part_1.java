package year_2024.day_3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.ImportFile.fileToString;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as String
        String input = fileToString("src\\year_2024\\day_3\\input.txt");

//        Patterns and matcher with group
        Pattern mulPattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)");
        Matcher mulMatcher = mulPattern.matcher(input);

//        Calculating result
        int result = 0;
        while (mulMatcher.find()) {
            result += Integer.parseInt(mulMatcher.group(1)) * Integer.parseInt(mulMatcher.group(2));
        }

        System.out.println("Day 3, Part 1, Result:" + result);
    }
}
