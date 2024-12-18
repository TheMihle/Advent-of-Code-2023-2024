package year_2023.day_2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as array
        ArrayList<String> inputLines = fileToArray("src\\year_2023\\day_2\\input.txt");

//        Max number to look for
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

//        Pattern for what to find
        Pattern colorPattern = Pattern.compile("red|green|blue");
        Pattern numberPattern = Pattern.compile("\\d+");

//        Sum for Game ID:
        int sum = 0;

        for (int i = 0; i < inputLines.size(); i++) {
            String[] colorCubeArray = inputLines.get(i).split(": ")[1]
                                                       .split("[;,]");
            boolean overMax = false;

//            Finds games with too many of a coloured cube
            for (String cube : colorCubeArray) {
                Matcher matcher = colorPattern.matcher(cube);
                matcher.find();

//                Red cubes
                if (matcher.group().equals("red")){
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > maxRed) {
                        overMax = true;
                    }

//                    Green Cubes
                } else if (matcher.group().equals("green")) {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > maxGreen) {
                        overMax = true;
                    }

//                    Blue Cubes
                } else {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                     if (Integer.parseInt(matcher.group()) > maxBlue) {
                         overMax = true;
                   }
                }
            }

//            Calculates sum
            if (!overMax){ sum += i+1; }
        }

//        Prints sum
        System.out.println("Calculated sum: " + sum);
    }
}
