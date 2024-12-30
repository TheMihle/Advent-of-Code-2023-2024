package year2023.day01;

import java.util.List;
import java.util.regex.*;
import static common.ImportFile.fileToArray;

public class Part_2 {
    public static void main(String[] args) {
        List<String> inputLines = fileToArray("src\\year2023\\day01\\input.txt");

//        Pattern for what to find
        Pattern pattern = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");

        int calibrationValueSum = 0;

//        Loop through the different lines
        for (String inputLine : inputLines){
            Matcher matcher = pattern.matcher(inputLine);

//            Finds first number
            matcher.find();
            String out = matcher.group();
            String firstNumber = numberSwitch(out);

//            Finds second number
            while (matcher.find()){
                out = matcher.group();
            }
            String secondNumber = numberSwitch(out);

//            Puts them together and calculates sum
            try{
                calibrationValueSum += Integer.parseInt(firstNumber + secondNumber);
            }
            catch (Exception e){
                System.out.println("Error, character in array: " + e);
            }
        }

//        Print result
        System.out.println("Day 1, Part 2, Sum of calibration values: " + calibrationValueSum);
    }

//    Converts letter numbers to numbers
    static String numberSwitch(String string){
        return switch (string){
            case "one" -> "1";
            case "two" -> "2";
            case "three" -> "3";
            case "four" -> "4";
            case "five" -> "5";
            case "six" -> "6";
            case "seven" -> "7";
            case "eight" -> "8";
            case "nine" -> "9";
            default -> string;
        };
    }
}
