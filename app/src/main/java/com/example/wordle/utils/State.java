package com.example.wordle.utils;

import java.util.Arrays;
import java.util.Objects;

import static com.example.wordle.utils.WordleGame.*;

public class State {

    public Color[] wordState;                 // The guess state of word
    public Color[] alphabetState;             // The guess state of the alphabet
    public int chancesLeft;                   // The chances left
    public String answer;
    public String word;               // The final answer, and the current guess word
    public GameStatus status;                 // The current game status

    public State(String answer) {
        wordState = new Color[WORD_LENGTH];
        Arrays.fill(wordState, Color.GRAY);
        alphabetState = new Color[ALPHABET_SIZE];
        Arrays.fill(alphabetState, Color.GRAY);
        chancesLeft = TOTAL_CHANCES;
        this.answer = answer;
        word = "";
        status = GameStatus.RUNNING;
    }
    @Override
    public String toString() {
        return Arrays.toString(wordState) + "$" + Arrays.toString(alphabetState) + "$"
                + chancesLeft + "$" + answer + "$" + word + "$" + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.toString(), o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(wordState),
                Arrays.hashCode(alphabetState), chancesLeft, answer, word, status);
    }

    public void clear(String answer) {
        Arrays.fill(wordState, Color.GRAY);
        Arrays.fill(alphabetState, Color.GRAY);
        chancesLeft = TOTAL_CHANCES;
        this.answer = answer;
        word = "";
        status = GameStatus.RUNNING;
    }
}
