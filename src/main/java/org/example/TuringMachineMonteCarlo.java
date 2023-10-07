package org.example;
import java.util.Random;

public class TuringMachineMonteCarlo {
    private static final int MAX_INPUT_LENGTH = 1000;
    private static final int NUM_TESTS = 10000000;

    public static void main(String[] args) {
        int falses = 0;
        int trues = 0;
        TuringMachine turingMachine = new TuringMachine("q0", "");

        // Добавляем переходы
        turingMachine.addTransition("q0", '0', "q1", '1', TuringMachine.Direction.RIGHT);
        turingMachine.addTransition("q0", '1', "q0", '0', TuringMachine.Direction.LEFT);
        turingMachine.addTransition("q1", '0', "q1", '0', TuringMachine.Direction.RIGHT);
        turingMachine.addTransition("q1", '1', "q0", '1', TuringMachine.Direction.LEFT);

        Random random = new Random();

        for (int i = 0; i < NUM_TESTS; i++) {
            int inputLength = random.nextInt(MAX_INPUT_LENGTH) + 1;
            StringBuilder inputBuilder = new StringBuilder();
            for (int j = 0; j < inputLength; j++) {
                inputBuilder.append(random.nextInt(2));
            }
            String input = inputBuilder.toString();

            turingMachine.setTape(input);
            boolean halted = turingMachine.runWithHaltCheck();

            System.out.println("Input: " + input);
            System.out.println("Halted: " + halted);
            if(halted == false)
            {
                falses++;
            } else
            {
                trues++;
            }
        }
        System.out.println("Not stop " + falses);
        System.out.println("Stop " + trues);
    }
}
