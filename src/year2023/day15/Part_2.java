package year2023.day15;

import java.util.LinkedHashMap;
import java.util.Map;

import static common.ImportFile.fileToString;

public class Part_2 {
    public static void main(String[] args) {
        String inputString = fileToString("src\\year2023\\day15\\input.txt");

//        Convert string to array
        String[] sequence = inputString.split(",");

        int focusingPower = 0;

//        Array of linkedHashMap for boxes with sets of labels and lenses in them.
        Map<String, Integer>[] boxLensMapArray = new LinkedHashMap[256];

        for (int i = 0; i < boxLensMapArray.length; i++) {
            boxLensMapArray[i] = new LinkedHashMap<>();
        }

        for (String step : sequence) {

//            Adds lens to box
            if (step.contains("=")) {
                String label = step.split("=")[0];
                Integer value = Integer.parseInt(step.split("=")[1]);
                boxLensMapArray[labelHash(label)].put(label, value);

//                Remove lens from box
            } else if (step.contains("-")) {
                String label = step.split("-")[0];
                boxLensMapArray[labelHash(label)].remove(label);
            }
        }

//            Calculates focusing power
        for (int i = 0; i < boxLensMapArray.length; i++) {
            int iteration = 1;
            for (Map.Entry<String, Integer> lens : boxLensMapArray[i].entrySet()) {
                focusingPower += (i + 1) * iteration * lens.getValue();
                iteration++;
            }
        }

        System.out.println("Day 15, Part 2, Focusing power of lens configuration : " + focusingPower);
    }

//    Calculates hash value of a label
    public static int labelHash(String label) {
        int value = 0;
        for (char character : label.toCharArray()) {
            value += character;
            value *= 17;
            value %= 256;
        }
        return value;
    }
}
