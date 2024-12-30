package year2023.day15;

import java.util.LinkedHashMap;
import java.util.Map;

import static common.ImportFile.fileToString;

public class Part_2 {
    public static void main(String[] args) {

//        Import file as string
        String inputString = fileToString("src\\year2023\\day15\\input.txt");

//        Convert string to array
        String[] sequence = inputString.split(",");

        int sum = 0;

//        Array of linkedHashMap for boxes with sets of labels and lenses in them.
        LinkedHashMap<String, Integer>[] boxLensMapArray = new LinkedHashMap[256];

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

//        Prints out boxLensArray with content
//        for (LinkedHashMap<String, Integer> stringIntegerLinkedHashMap : hashMap) {
//            System.out.println(stringIntegerLinkedHashMap);
//        }

//            Calculates sum
        for (int i = 0; i < boxLensMapArray.length; i++) {
            int iteration = 1;
            for (Map.Entry<String, Integer> lens : boxLensMapArray[i].entrySet()) {
                sum += (i + 1) * iteration * lens.getValue();
                iteration++;
            }
        }

//        Prints sum
        System.out.println("Sum: " + sum);
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
