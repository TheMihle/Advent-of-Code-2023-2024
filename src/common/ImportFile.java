package common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportFile {

//    Converts a file in to an ArrayList, one line per element
    public static ArrayList<String> fileToArray(String path) {
        ArrayList<String> inputLines = new ArrayList<>();

//        Loads file with text to scanner to load it in to the array
        File inputFile = new File(path);
        try (Scanner scanner = new Scanner(inputFile)){
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File not found or failed to load: " + e);
        }
        return inputLines;
    }

//    Converts file to 2D array, with a specified split regex
    public static String[][] fileTo2DArray(String path, String regex) {
        List<String> inputLines = fileToArray(path);

//        Create an 2D array of inputlines
        String[][] coordinate2DArray = new String[inputLines.size()][];
        for (int i = 0; i < inputLines.size(); i++) {
            coordinate2DArray[i] = (inputLines.get(i).split(regex));
        }
        return coordinate2DArray;
    }

//    Converts file to 2D array, one symbol per element
    public static String[][] fileTo2DArray(String path){
        return fileTo2DArray(path, "");
    }

//    Create an integer 2D array of inputlines
    public static int[][] fileToInt2DArray(String path, String regex) {
        String[][] stringArray = fileTo2DArray(path, regex);
        int[][] intArray = new int[stringArray.length][];
        try {
            for ( int i = 0; i < stringArray.length; i++) {
                intArray[i] = new int[stringArray[i].length];
                for (int j = 0; j < stringArray[i].length; j++) {
                    intArray[i][j] = Integer.parseInt(stringArray[i][j]);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not parse to to int, check file or regex :" + e);
        }
        return intArray;
    }

//    Converts file to integer 2D array, one symbol per element
    public static int[][] fileToInt2DArray(String path) {
        return fileToInt2DArray(path, "");
    }

//    Converts a file to a String, all lines added together
    public static String fileToString(String path) {
        StringBuilder inputString = new StringBuilder();

//        Loads file with text to scanner and convert to one string
        File inputFile = new File(path);
        try (Scanner scanner = new Scanner(inputFile)){
            while (scanner.hasNextLine()) {
                inputString.append(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File not found or failed to load: " + e);
        }
        return inputString.toString();
    }

//    Converts file to a string, then splits to array based in input regex
    public static String[] fileToSplittedString(String path, String regex) {
        String inputString = fileToString(path);
        return inputString.split(regex);
    }

//    Converts file to string, then splits to array with one symbol per element
    public static String[] fileToSplittedString(String path) {
        return fileToSplittedString(path, "");
    }
}
