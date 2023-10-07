package org.example;
import java.util.HashMap;
import java.util.Map;

public class TuringMachine {
    private String currentState;
    private String tape;
    private Map<String, Map<Character, Transition>> transitions;

    public TuringMachine(String initialState, String initialTape) {
        currentState = initialState;
        tape = initialTape;
        transitions = new HashMap<>();
    }

    public void addTransition(String currentState, char currentSymbol, String nextState, char newSymbol, Direction direction) {
        Map<Character, Transition> stateTransitions = transitions.getOrDefault(currentState, new HashMap<>());
        stateTransitions.put(currentSymbol, new Transition(nextState, newSymbol, direction));
        transitions.put(currentState, stateTransitions);
    }

    public void setTape(String newTape) {
        tape = newTape;
    }

    public boolean runWithHaltCheck() {
        int headPosition = 0;

        while (true) {
            if (!transitions.containsKey(currentState)) {
                return false; // Нет переходов для текущего состояния
            }

            Map<Character, Transition> stateTransitions = transitions.get(currentState);
            if (!stateTransitions.containsKey(tape.charAt(headPosition))) {
                return false; // Нет переходов для текущего символа на ленте
            }

            Transition transition = stateTransitions.get(tape.charAt(headPosition));
            currentState = transition.getNextState();

            StringBuilder tapeBuilder = new StringBuilder(tape);
            tapeBuilder.setCharAt(headPosition, transition.getNewSymbol());
            tape = tapeBuilder.toString();

            if (transition.getDirection() == Direction.LEFT) {
                headPosition--;
                if (headPosition < 0) {
                    tapeBuilder.insert(0, ' '); // Расширяем ленту влево
                    tape = tapeBuilder.toString();
                    headPosition = 0;
                }
            } else if (transition.getDirection() == Direction.RIGHT) {
                headPosition++;
                if (headPosition >= tape.length()) {
                    tapeBuilder.append(' '); // Расширяем ленту вправо
                    tape = tapeBuilder.toString();
                }
            }

            if (currentState.equals("halt")) {
                return true; // Машина Тьюринга остановилась
            }
        }
    }

    public enum Direction {
        LEFT,
        RIGHT
    }

    private static class Transition {
        private String nextState;
        private char newSymbol;
        private Direction direction;

        public Transition(String nextState, char newSymbol, Direction direction) {
            this.nextState = nextState;
            this.newSymbol = newSymbol;
            this.direction = direction;
        }

        public String getNextState() {
            return nextState;
        }

        public char getNewSymbol() {
            return newSymbol;
        }

        public Direction getDirection() {
            return direction;
        }
    }
}
