package org.example;

import java.io.IOException;
import java.util.Random;

public class TuringMachineMonteCarlo2 {
    private static final int NUM_TESTS = 1000;
    public static void main(String[] args) {
        int MAX_INPUT_LENGTH = 100;
        int falses = 0;
        int trues = 0;
        //TuringMachine2 turingMachine = new TuringMachine2("q0", "");

        // Добавляем переходы
        String[] states = {"start", "state1","state2", "halt"};
        char[] symbols = {'0', '1'};
        TuringMachine2.Direction[] directions = new TuringMachine2.Direction[]{TuringMachine2.Direction.RIGHT, TuringMachine2.Direction.LEFT};
        TuringMachine2 turingMachine = new TuringMachine2("start", "");
        Random random = new Random();
        int k = 0;
        for (int i = 0; i < NUM_TESTS; i++) {

            int inputLength = random.nextInt(MAX_INPUT_LENGTH) + 1;
            StringBuilder inputBuilder = new StringBuilder();
            for (int j = 0; j < inputLength; j++) {
                inputBuilder.append(random.nextInt(2));

            }
            for (int j = 0; j < inputLength; j++) {
                turingMachine.addTransition(states[(int) (Math.random() * states.length)],
                        symbols[(int) (Math.random() * symbols.length)],
                        states[(int) (Math.random() * states.length)],
                        symbols[(int) (Math.random() * symbols.length)],
                        directions[(int) (Math.random() * directions.length)]);
            }
            String input = inputBuilder.toString();

            turingMachine.setTape(input);
            boolean halted = turingMachine.runWithHaltCheck();
            System.out.println("Input: " + input);
            System.out.println("Halted: " + halted);

            if(!halted)
            {
                falses++;
            } else
            {
                trues++;
            }
            turingMachine.cleanDirection();
        }
        System.out.println("Not stop " + falses);
        System.out.println("Stop " + trues);

    }
}
