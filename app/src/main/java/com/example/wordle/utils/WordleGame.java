package com.example.wordle.utils;

import java.util.*;

public class WordleGame {
    public static final int ALPHABET_SIZE = 26;            // The size of the alphabet
    public static final int WORD_LENGTH = 5;               // The length of words
    public static final int TOTAL_CHANCES = 6;             // The chances in total

    // Guess `word` at state `s`
    public static State guess(State s) {
        Arrays.fill(s.wordState, Color.GRAY);
        int[] guessAlphabetCount = new int[ALPHABET_SIZE];
        int[] answerAlphabetCount = new int[ALPHABET_SIZE];

        for (int i = 0; i < WORD_LENGTH; i++) {
            int answerIndex = s.answer.charAt(i) - 'A';
            answerAlphabetCount[answerIndex]++;
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (s.word.charAt(i) == s.answer.charAt(i)) {
                int wordIndex = s.word.charAt(i) - 'A';
                guessAlphabetCount[wordIndex]++;
            }
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            int wordIndex = s.word.charAt(i) - 'A';
            if (s.word.charAt(i) == s.answer.charAt(i)) {
                s.wordState[i] = Color.GREEN;
                s.alphabetState[wordIndex] = Color.GREEN;
            } else {
                if (s.answer.contains(String.valueOf(s.word.charAt(i)))
                        && guessAlphabetCount[wordIndex] < answerAlphabetCount[wordIndex]) {
                    s.wordState[i] = Color.YELLOW;
                } else {
                    s.wordState[i] = Color.RED;
                }
                if (s.alphabetState[wordIndex] == Color.GRAY || s.alphabetState[wordIndex] == Color.RED) {
                    if (s.answer.contains(String.valueOf(s.word.charAt(i)))) {
                        s.alphabetState[wordIndex] = Color.YELLOW;
                    } else {
                        s.alphabetState[wordIndex] = Color.RED;
                    }
                }
                guessAlphabetCount[wordIndex]++;
            }
        }

        if (Objects.equals(s.word, s.answer)) {
            s.status = GameStatus.WON;
        } else if (s.chancesLeft == 1) {
            s.status = GameStatus.LOST;
        }
        s.chancesLeft--;
        return s;
    }
}
