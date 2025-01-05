package year2024.day09;

import java.util.*;

import static common.ImportFile.fileToSplittedString;
import static common.PathConstructor.getInputPath;

public class Part_2 {
    public static void main(String[] args) {

//        Import file as array and convert to int array
        String[] inputDiskMap = fileToSplittedString(getInputPath(Part_2.class));

        int[] diskMap = new int[inputDiskMap.length];
        for (int i = 0; i < inputDiskMap.length; i++) {
            diskMap[i] = Integer.parseInt(inputDiskMap[i]);
        }

//        Store index of start of each block
        Map<Integer, Integer> blockStartIndex = CreateBlockStartIndex(diskMap);

        List<Integer> disk = reconstructDisk(diskMap);
        fragmentDisk(disk, diskMap, blockStartIndex);

//        Calculate checksum
        long ckeckSum = 0;
        for (int i = 0; i < disk.size(); i++) {
            if ( disk.get(i) != null) ckeckSum += (long) i * disk.get(i);
        }

        System.out.println("Day 9, Part 2, Disk Checksum: " + ckeckSum);
    }

//    Reconstruct disk from diskMap
    private static List<Integer> reconstructDisk(int[] diskMap) {
        List<Integer> disk = new ArrayList<>();
        int nextBlockId = 0;

        for (int i = 0; i < diskMap.length; i++) {
            for (int j = 0; j < diskMap[i]; j++) {
                if (i % 2 == 0) disk.add(nextBlockId);
                else disk.add(null);
            }
            if (i % 2 == 0) nextBlockId++;
        }
        return disk;
    }

    private static Map<Integer, Integer> CreateBlockStartIndex(int[] inputArray) {
        Map<Integer, Integer> blockStartIndex= new HashMap<>();
        int blockNumber = 0;
        int spaceNumber = 0;
        for (int i = 0; i < inputArray.length; i = i + 2) {
            blockStartIndex.put(blockNumber++, spaceNumber);
            if (i < inputArray.length - 1) spaceNumber = spaceNumber + inputArray[i] + inputArray[i + 1];
        }

        return blockStartIndex;
    }

//    Moves blocks from the end of the disk, to the first free space on disk
    private static void fragmentDisk(List<Integer> disk, int[] diskMap, Map<Integer, Integer> blockStartIndex) {
        int lastBlockNumber = disk.getLast();
        for (int blockNumber = lastBlockNumber; blockNumber >= 0 ; blockNumber--) {
            int requiredSpace = diskMap[blockNumber * 2];
            int startEmptyIndex = findSpace(disk, requiredSpace);
            if (startEmptyIndex < blockStartIndex.get(blockNumber)) {
                moveBlock(disk, blockStartIndex.get(blockNumber), startEmptyIndex);
            }
        }
    }

//    Finds first index on disk where there are available number of spaces in a row
    private static int findSpace(List<Integer> disk, int numberOfSpaces) {
        int startIndex = -1;

        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == null) {
                if (startIndex == -1) startIndex = i;
            } else if (startIndex != -1){
                if (i - startIndex >= numberOfSpaces) return startIndex;
                startIndex = -1;
            }
        }

        return startIndex;
    }

    private static void moveBlock(List<Integer> disk, int getFromIndex, int fillFromIndex) {
        int blockNumber = disk.get(getFromIndex);
        while (getFromIndex < disk.size() && disk.get(fillFromIndex) == null &&
                disk.get(getFromIndex) != null && disk.get(getFromIndex).equals(blockNumber)) {
            disk.set(fillFromIndex++, disk.get(getFromIndex));
            disk.set(getFromIndex++, null);
        }
    }
}
