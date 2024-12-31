package year2024.day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.ImportFile.fileToString;
import static common.PathConstructor.getInputPath;

public class Part_2 {
    public static void main(String[] args) {
        String input = fileToString(getInputPath(Part_2.class));

//        Patterns
        Pattern mulPattern = Pattern.compile("mul\\(([0-9]+),([0-9]+)\\)|don't\\(\\)|do\\(\\)");
        Matcher mulMatcher = mulPattern.matcher(input);

//        Calculating result, add or don't add based on previous do() or don't()
        int result = 0;
        boolean include = true;
        while (mulMatcher.find()) {
            String foundCommand = mulMatcher.group(0);

            if (foundCommand.equals("do()")) {
                include = true;
            } else if (foundCommand.equals("don't()")) {
                include = false;
            } else if (include) {
                result += Integer.parseInt(mulMatcher.group(1)) * Integer.parseInt(mulMatcher.group(2));
            }
        }

        System.out.println("Day 3, Part 2, Result: " + result);
    }
}
