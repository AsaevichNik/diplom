package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest  {

    private static final int MAX_INPUT_LENGTH = 1000;
    private static final int NUM_TESTS = 10000000;
    @Test
    public void test() throws IOException {
        int falses = 0;
        int trues = 0;
        TuringMachine2 turingMachine = new TuringMachine2("q0", "");

        // Добавляем переходы
        turingMachine.addTransition("q0", '0', "q1", '1', TuringMachine2.Direction.RIGHT);
        turingMachine.addTransition("q1", '1', "halt", '0', TuringMachine2.Direction.LEFT);
        turingMachine.addTransition("halt", '0', "q1", '0', TuringMachine2.Direction.RIGHT);
        turingMachine.addTransition("q0", '1', "halt", '1', TuringMachine2.Direction.LEFT);

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

            if(!halted)
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
