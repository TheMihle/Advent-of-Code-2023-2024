package day_2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as array
        ArrayList<String> inputLines = fileToArray("src\\day_2\\input.txt");

//        Max number to look for
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

//        Pattern for what to find
        Pattern colorPattern = Pattern.compile("red|green|blue");
        Pattern numberPattern = Pattern.compile("\\d+");

//        Sum for Game ID:
        int sum = 0;

        for (String inputLine : inputLines) {
            String[] colorCubeArray = inputLine.split("[:;,]");

            for (String cube : colorCubeArray) {
                if (cube.equals(colorCubeArray[0])) continue;
                Matcher matcher = colorPattern.matcher(cube);
                matcher.find();

//                Red cubes
                if (matcher.group().equals("red")){
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > maxRed) {
                        matcher = numberPattern.matcher(colorCubeArray[0]);
                        matcher.find();
                        sum += Integer.parseInt(matcher.group());
                        break;
                    }

//                    Green Cubes
                } else if (matcher.group().equals("green")) {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > maxGreen) {
                        matcher = numberPattern.matcher(colorCubeArray[0]);
                        matcher.find();
                        sum += Integer.parseInt(matcher.group());
                        break;
                    }

//                    Blue Cubes
                } else {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                     if (Integer.parseInt(matcher.group()) > maxBlue) {
                         matcher = numberPattern.matcher(colorCubeArray[0]);
                         matcher.find();
                         sum += Integer.parseInt(matcher.group());
                         break;
                   }
                }
            }
        }

        int totalSum = 0;
        for (int i = 1; i <=100 ; i++) {
            totalSum += i;
        }
        System.out.println("Max sum: " + totalSum);
        System.out.println("Calculated sum: " + sum);
        System.out.println("Actual Sum: " + (totalSum-sum));
    }
}
