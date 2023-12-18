package common;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportFile {

//    Converts a file in to an ArrayList, one line per element
    public static ArrayList<String> fileToArray(String path){

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
        while (scanner.hasNextLine()){
            inputLines.add(scanner.nextLine());
        }
        return inputLines;
    }

//    Converts a file to a String, all lines added together
    public static String fileToString(String path){

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
        while (scanner.hasNextLine()){
            inputString += scanner.nextLine();
        }

        return inputString;
    }
}
