package year2024.day24;

import java.util.*;
import java.util.stream.Collectors;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Part_1 {
    public static void main(String[] args) {
        List<String> initialGateValues = fileToArray(getInputPath(Part_1.class));

//        Extract gate values
        Map<String, Boolean> gateValues = new HashMap<>();
        while (!initialGateValues.getFirst().isEmpty()) {
            String[] tempArray = initialGateValues.removeFirst().split(": ");
            if (tempArray[1].equals("1")) gateValues.put(tempArray[0], true);
            else gateValues.put(tempArray[0], false);
        }
        initialGateValues.removeFirst();

//        Extract gates
        List<List<String>> gates = new ArrayList<>();
        while (!initialGateValues.isEmpty()) {
            gates.add(new ArrayList<>(List.of(initialGateValues.removeFirst().split(" "))));
            gates.getLast().remove(3);
        }

//        Calculate gate values
        calcGateValues(gateValues, gates);

//        Extract output from gates to string of binary
        String output = gateToBinary(gateValues, "z");

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

    private static void calcGateValues(Map<String, Boolean> gateValues, List<List<String>> gates) {
        boolean change = true;
        while (change) {
            change = false;
            for (List<String> gate : gates) {
                if (gateValues.containsKey(gate.get(0)) && gateValues.containsKey(gate.get(2)) && !gateValues.containsKey(gate.get(3))) {
                    gateValues.put(gate.getLast(), gateOperation(gate.get(1), gateValues.get(gate.get(0)), gateValues.get(gate.get(2))));
                    change = true;
                }
            }
        }
    }

    private static String gateToBinary(Map<String, Boolean> gateValues, String startsWith){
        return gateValues.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .map(Map.Entry::getValue)
                .map(value -> {
                    if(value) return "1";
                    else return "0";
                }).collect(Collectors.joining());
    }
}
