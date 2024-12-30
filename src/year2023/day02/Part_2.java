package year2023.day02;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.ImportFile.fileToArray;

public class Part_2 {
    public static void main(String[] args) {
        List<String> inputLines = fileToArray("src\\year2023\\day02\\input.txt");

//        Pattern for what to find
        Pattern colorPattern = Pattern.compile("red|green|blue");
        Pattern numberPattern = Pattern.compile("\\d+");

        int sum = 0;

        for (String inputLine : inputLines) {
            String[] colorCubeArray = inputLine.split(": ")[1]
                                               .split("[;,]");

//                Highest number of each colour found
                int redHigh = 0;
                int greenHigh = 0;
                int blueHigh = 0;

//                Finds highest number of each colour found
            for (String cube : colorCubeArray) {
                Matcher matcher = colorPattern.matcher(cube);
                matcher.find();

//                Red cubes
                if (matcher.group().equals("red")) {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > redHigh) {
                        redHigh = Integer.parseInt(matcher.group());
                    }

//                    Green Cubes
                } else if (matcher.group().equals("green")) {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > greenHigh) {
                        greenHigh = Integer.parseInt(matcher.group());
                    }

//                    Blue Cubes
                } else {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > blueHigh) {
                        blueHigh = Integer.parseInt(matcher.group());
                    }
                }
            }

//            Calculating sum
            sum += redHigh * greenHigh * blueHigh;
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }
}
