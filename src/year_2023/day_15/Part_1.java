package year_2023.day_15;

import static common.ImportFile.fileToString;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as string
        String inputString = fileToString("src\\year_2023\\day_15\\input.txt");

//        Convert string to array
        String[] sequence = inputString.split(",");

        int sum = 0;

        for (String step : sequence) {

//            Calculates hash value of a step
            int currentValue = 0;
            for (char character : step.toCharArray()) {
                currentValue += character;
                currentValue *= 17;
                currentValue %= 256;
            }

//            Calculates sum
            sum += currentValue;
        }

//        Prints sum
        System.out.println("Sum: " + sum);
    }
}
