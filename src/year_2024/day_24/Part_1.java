package year_2024.day_24;

import java.util.*;
import java.util.stream.Collectors;

import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {
        ArrayList<String> initialGateValues = fileToArray("src\\year_2024\\day_24\\input.txt");

//        Extract gate values
        HashMap<String, Boolean> gateValues = new HashMap<>();
        while (!initialGateValues.getFirst().isEmpty()) {
            String[] tempArray = initialGateValues.removeFirst().split(": ");
            if (tempArray[1].equals("1")) gateValues.put(tempArray[0], true);
            else gateValues.put(tempArray[0], false);
        }
        initialGateValues.removeFirst();

//        Extract gates
        ArrayList<ArrayList<String>> gates = new ArrayList<>();
        while (!initialGateValues.isEmpty()) {
            gates.add(new ArrayList<>(List.of(initialGateValues.removeFirst().split(" "))));
            gates.getLast().remove(3);
        }

//        Calculate gate values
        boolean change = true;
        while (change) {
            change = false;
            for (ArrayList<String> gate : gates) {
                if (gateValues.containsKey(gate.get(0)) && gateValues.containsKey(gate.get(2)) && !gateValues.containsKey(gate.get(3))) {
                    gateValues.put(gate.getLast(), gateOperation(gate.get(1), gateValues.get(gate.get(0)), gateValues.get(gate.get(2))));
                    change = true;
                }
            }
        }

//        Extract output from gates to string of binary
        String output = gateValues.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .map(Map.Entry::getValue)
                .map(value -> {
                    if(value) return "1";
                    else return "0";
                }).collect(Collectors.joining());

        System.out.println("Day 24, Part 1: \n" +
                "Binary output: " + output + "\n" +
                "Decimal output: " + Long.parseLong(output, 2));
    }


    private static boolean gateOperation(String operation, boolean bol1, boolean bol2) {
        return switch (operation) {
            case "OR" -> bol1 | bol2;
            case "XOR" -> bol1 ^ bol2;
            case "AND" -> bol1 & bol2;
            default -> throw new UnsupportedOperationException("Operation not found");
        };
    }
}
