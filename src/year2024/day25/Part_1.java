package year2024.day25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

// I feel like this code isn't the best code, but it works.
public class Part_1 {
    public static void main(String[] args) {
        List<String> keyLockInput = fileToArray(getInputPath(Part_1.class));

//        Split the locks/keys
        List<List<String>> keyLockArray = new ArrayList<>();

        List<String> tempList = new ArrayList<>();
        for (String line : keyLockInput) {
            if (line.isEmpty()) {
                keyLockArray.add(new ArrayList<>(tempList));
                tempList.clear();
            } else {
                tempList.add(line);
            }
        }
        if (!tempList.isEmpty()) {
            keyLockArray.add(new ArrayList<>(tempList));
            tempList.clear();
        }

//        Convert to Arrays of pin length and separate keys from locks
        List<int[]> keys = new ArrayList<>();
        List<int[]> locks = new ArrayList<>();

        for (List<String> keyLock : keyLockArray) {
            int[] pinLengthArray = new int[keyLock.getFirst().length()];
            for (int i = 0; i < keyLock.getFirst().length(); i++) {
                int counter = 0;
                for (String string : keyLock) {
                    if(string.charAt(i) == '#') counter++;
                }
                pinLengthArray[i] = counter;
            }
            if (keyLock.getFirst().charAt(0) == '#') keys.add(pinLengthArray);
            else locks.add(pinLengthArray);
        }

        int maxHeight = keyLockArray.getFirst().size();
        int countMatches = 0;
        for (int[] key : keys) {
            for (int[] lock : locks) {
                if (checkKeyLock(key, lock, maxHeight)) countMatches++;
            }
        }

        System.out.println("Day 25, Part 1, Number of combinations where keys fit in to the lock: " + countMatches);
    }

//    Check if key and lock fits
    private static boolean checkKeyLock(int[] key, int[] lock, int maxHeight) {
        int[] tempArray = new int[key.length];
        for (int i = 0; i < key.length; i++) {
            tempArray[i] = key[i] + lock[i];
        }
        return Arrays.stream(tempArray).allMatch(integer -> integer <= maxHeight);
    }
}
