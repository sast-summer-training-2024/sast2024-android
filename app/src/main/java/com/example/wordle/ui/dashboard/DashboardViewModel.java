package com.example.wordle.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wordle.data.User;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
    }

    public void updateText(User user) {
        StringBuffer s = new StringBuffer(
                "Win Rounds: \n" + user.winRounds + "\n"
                + "Total Rounds: \n" + user.totalRounds + "\n"
                + "Best Tries Distribution: \n");
        for (int i = 0; i < user.guesses.length; i++) {
            s.append("#").append(i + 1).append(":\t").append(user.guesses[i]).append('\n');
        }
        double winRate = user.totalRounds == 0 ? 0 : ((double) user.winRounds / user.totalRounds);
        s.append("Win Rate: \n").append(String.format("%.2f", winRate * 100)).append('%');
        mText.setValue(String.valueOf(s));
    }

    public LiveData<String> getText() {
        return mText;
    }
}