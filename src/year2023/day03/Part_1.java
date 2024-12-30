package year2023.day03;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray("src\\year2023\\day03\\input.txt");

//        Pattern for what to find
        Pattern numberPattern = Pattern.compile("\\d+");
        Pattern symbolPattern = Pattern.compile("[^\\d.]");

        int sum = 0;

//        Loop through array and find numbers
        for (int i = 0; i < inputLines.size(); i++) {
            Matcher matcherNumber = numberPattern.matcher(inputLines.get(i));
            while (true) {
                if (matcherNumber.find()){

//                    Area that should be selected for symbols
                    int startIndex = matcherNumber.start()-1;
                    int endIndex = matcherNumber.end()+1;
                    int startLine = -1;
                    int endLine = 1;

//                    Edge cases
                    if (matcherNumber.start() == 0) startIndex = 0;
                    if (matcherNumber.end() == inputLines.get(i).length()) endIndex = inputLines.get(i).length();
                    if (i == 0) startLine = 0;
                    if (i == inputLines.size()-1) endLine = 0;

//                    Creates substring of possible places for a symbol
                    String subString = "";
                    for (int j = i+startLine; j <= i+endLine; j++){
                        subString += inputLines.get(j).substring(startIndex, endIndex);
                    }

//                    Adds number to sum if there is a match of a symbol in substring
                    Matcher matcherSymbol = symbolPattern.matcher(subString);
                    if (matcherSymbol.find()) sum += Integer.parseInt(matcherNumber.group());

                } else {
                    break;
                }
            }
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }
}
