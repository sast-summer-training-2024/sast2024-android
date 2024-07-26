package com.example.wordle.utils;

import android.content.Context;
import android.util.Log;

import com.example.wordle.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

public class WordSet {

    private Set<String> finalSet = new HashSet<>();           // Words that are allowed to be answers
    private Set<String> accSet = new HashSet<>();             // Words that are allowed to be guessed
    private final Context context;

    public WordSet(Context context) {
        this.context = context;
        finalSet = retrieve_set_from_file(R.raw.final_words);
        accSet = retrieve_set_from_file(R.raw.acc_words);
    }

    private Set<String> retrieve_set_from_file(int resourceId) {
        Set<String> set = new HashSet<>();
        BufferedReader reader = null;

        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                set.add(line.strip().toUpperCase());
            }
        } catch (IOException e) {
            Log.e("WordSet", "Error reading file", e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Log.e("WordSet", "Error closing reader", e);
            }
        }
        return set;
    }

    public boolean isNotAccWord(String word) {
        return !accSet.contains(word);
    }

    public String randomAnswer() {
        // TODO begin
        // 从 finalSet 里随机出一个 String
        // TODO end
    }
}
