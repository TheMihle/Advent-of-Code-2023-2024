package year_2024.day_17;

import java.util.ArrayList;
import java.util.StringJoiner;

import static common.ImportFile.fileToArray;

public class Part_1 {
    public static void main(String[] args) {
        ArrayList<String> programInfo = fileToArray("src\\year_2024\\day_17\\testinput.txt");

//        Extract registers to values
        String tempRegisA = programInfo.get(0).substring(programInfo.get(0).lastIndexOf(" ") + 1);
        String tempRegisB = programInfo.get(1).substring(programInfo.get(1).lastIndexOf(" ") + 1);
        String tempRegisC = programInfo.get(2).substring(programInfo.get(2).lastIndexOf(" ") + 1);

        long regisA = Integer.parseInt(tempRegisA);
        long regisB = Integer.parseInt(tempRegisB);
        long regisC = Integer.parseInt(tempRegisC);

//        Extract program and convert to int
        String tempProgram = programInfo.getLast().substring(programInfo.getLast().lastIndexOf(" ") + 1);
        String[] stringProgram = tempProgram.split(",");
        int[] program = new int[stringProgram.length];
        for (int i = 0; i < stringProgram.length; i++) program[i] = Integer.parseInt(stringProgram[i]);


//        Run the program
        String programOutput = runProgram(program, regisA, regisB, regisC);
        System.out.println("Day 17, Part 1, program output: " + programOutput);
    }

//    Runs the program
    private static String runProgram(int[] program, long regisA, long regisB, long regisC) {
         StringJoiner stringJoiner = new StringJoiner(",");

        for (int i = 0; i < program.length; i += 2) {
            int opcode = program[i];
            int operand = program[i+1];

            switch (opcode) {
                case 0 -> regisA = divRegAByTwoPowOperand(operand, regisA, regisB, regisC);
//                Bitwise XOR of register B and literal operand
                case 1 -> regisB = regisB ^ operand;
                case 2 -> regisB = operandModEight(operand, regisA, regisB, regisC);
//                Sets instructions pointer to value of literal operand, do not increase it by 2
                case 3 -> { if (regisA != 0) i = operand - 2; }
//                Bitwise XOR on register B and C
                case 4 -> regisB = regisB ^ regisC;
                case 5 -> stringJoiner.add(operandModEight(operand, regisA, regisB, regisC) + "");
                case 6 -> regisB = divRegAByTwoPowOperand(operand, regisA, regisB, regisC);
                case 7 -> regisC = divRegAByTwoPowOperand(operand, regisA, regisB, regisC);
            }
        }
        return stringJoiner.toString();
    }

//    Divides register A by register or operand based on operand
    private static long divRegAByTwoPowOperand(int operand, long regisA, long regisB, long regisC) {
        return switch (operand) {
            case 4 -> (long) (regisA / Math.pow(2 , regisA));
            case 5 -> (long) (regisA / Math.pow(2 , regisB));
            case 6 -> (long) (regisA / Math.pow(2 , regisC));
            default -> (long) (regisA / Math.pow(2 , operand));
        };
    }

//    Register or operand modulo 8 based on operand
    private static long operandModEight(int operand, long regisA, long regisB, long regisC) {
        return switch (operand) {
            case 4 -> regisA % 8;
            case 5 -> regisB % 8;
            case 6 -> regisC % 8;
            default -> operand % 8;
        };
    }
}
