package common;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportFile {

    //    Converts a file in to an ArrayList, one line per element
    public static ArrayList<String> fileToArray(String path) {

//        Creates scanner and array
        Scanner scanner = null;
        ArrayList<String> inputLines = new ArrayList<>();

//        Loads file with text to scanner
        File inputFile = new File(path);
        try {
            scanner = new Scanner(inputFile);
        } catch (Exception e) {
            System.out.println("File not found: " + e);
        }

//        Puts lines in file in to array and returns it
        while (scanner.hasNextLine()) {
            inputLines.add(scanner.nextLine());
        }
        return inputLines;
    }
    public static String[][] fileTo2DArray(String path){
        return fileTo2DArray(path, "");
    }

    public static String[][] fileTo2DArray(String path, String regex) {

//        Import file as array per line
        ArrayList<String> inputLines = fileToArray(path);

//        Create an 2D array of inputlines
        String[][] coordinate2DArray = new String[inputLines.size()][];

        for (int i = 0; i < inputLines.size(); i++) {
            coordinate2DArray[i] = (inputLines.get(i).split(regex));
        }

        return coordinate2DArray;
    }

//    Converts a file to a String, all lines added together
    public static String fileToString(String path) {

//        Creates scanner and string
        Scanner scanner = null;
        String inputString = "";

//        Loads file with text to scanner
        File inputFile = new File(path);
        try {
            scanner = new Scanner(inputFile);
        } catch (Exception e) {
            System.out.println("File not found: " + e);
        }

//        Puts lines in file in to array and returns it
        while (scanner.hasNextLine()) {
            inputString += scanner.nextLine();
        }

        return inputString;
    }

//    Converts file to a string, then splits to array
    public static String[] fileToSplittedString(String path, String regex) {
        String inputString = fileToString(path);
        return inputString.split(regex);
    }

    public static String[] fileToSplittedString(String path) {
        return fileToSplittedString(path, "");
    }
}
