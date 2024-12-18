package year_2024.day_9;

import java.util.ArrayList;

import static common.ImportFile.fileToSplittedString;

public class Part_1 {
    public static void main(String[] args) {

//        Import file as array and convert to int array
        String[] inputArray = fileToSplittedString("src\\year_2024\\day_9\\input.txt", "");

        int[] intArray = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            intArray[i] = Integer.parseInt(inputArray[i]);
        }

//        Reconstruct disk
        ArrayList<Integer> disk = new ArrayList<>();
        int nextBlockId = 0;

        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[i]; j++) {
                if (i % 2 == 0) disk.add(nextBlockId);
                else disk.add(null);
            }
            if (i % 2 == 0) nextBlockId++;
        }

//        Fragments disk
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == null) {
                while (disk.getLast() == null) disk.removeLast();
                disk.set(i, disk.removeLast());
            }
        }

//        Calculate checksum
        long ckeckSum = 0;
        for (int i = 0; i < disk.size(); i++) {
            ckeckSum += (long) i * disk.get(i);
        }

        System.out.println("Day 9,Part 1, Checksum: " + ckeckSum);
    }
}
