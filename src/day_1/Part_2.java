package day_1;

import java.util.ArrayList;
import java.util.regex.*;
import static common.ImportFile.fileToArray;

public class Part_2 {

//    Converts letter numbers to numbers
    static String numberSwitch(String string){
        switch (string){
            case "one" -> string = "1";
            case "two" -> string = "2";
            case "three" -> string = "3";
            case "four" -> string = "4";
            case "five" -> string = "5";
            case "six" -> string = "6";
            case "seven" -> string = "7";
            case "eight" -> string = "8";
            case "nine" -> string = "9";
        }
        return string;
    }

//    Main
    public static void main(String[] args) {

//        Import file as array
        ArrayList<String> inputLines = fileToArray("src\\day_1\\input.txt");

//        Pattern for what to find
        Pattern pattern = Pattern.compile("[0-9]|one|two|three|four|five|six|seven|eight|nine");

        int sum = 0;

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
            String secoundNuber = numberSwitch(out);

//            Puts them together and caluclates sum
            try{
                sum += Integer.parseInt(firstNumber + secoundNuber);
            }
            catch (Exception e){
                System.out.println("Error, character in array: " + e);
            }
        }

//        Print result
        System.out.println("Sum: " + sum);
    }
}
