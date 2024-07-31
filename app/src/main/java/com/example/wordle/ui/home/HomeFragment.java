package com.example.wordle.ui.home;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.example.wordle.utils.WordleGame.TOTAL_CHANCES;
import static com.example.wordle.utils.WordleGame.WORD_LENGTH;
import static com.example.wordle.utils.WordleGame.guess;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wordle.MainActivity;
import com.example.wordle.R;
import com.example.wordle.data.*;
import com.example.wordle.databinding.FragmentHomeBinding;
import com.example.wordle.utils.Color;
import com.example.wordle.utils.State;
import com.example.wordle.utils.WordSet;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private final StringBuffer guessWord = new StringBuffer();
    private WordSet wordSet;
    private State state;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        wordSet = new WordSet(getContext());
        state = new State(wordSet.randomAnswer());

        // 完成 inputLayout
        GridLayout inputLayout = binding.InputLayout;

        inputLayout.setRowCount(TOTAL_CHANCES);
        inputLayout.setColumnCount(WORD_LENGTH);
        inputLayout.setForegroundGravity(Gravity.CENTER);
        for (int row = 0; row < inputLayout.getRowCount(); row++) {
            for (int col = 0; col < inputLayout.getColumnCount(); col++) {
                TextView textView = new TextView(getContext());
                textView.setText(" ");
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(50); // 设置文本大小
                textView.setTextColor(Color.WHITE.getRgbCode());
                textView.setBackgroundResource(R.drawable.gray_border); // 设置背景，默认为灰色
                // 设置参数
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.columnSpec = GridLayout.spec(col, 1); // 设置 TextView 占据一个整列
                params.rowSpec = GridLayout.spec(row, 1); // 设置 TextView 占据一个整行

                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                params.width = screenWidth / inputLayout.getColumnCount();
                params.height = WRAP_CONTENT;

                // 将 TextView 添加到 GridLayout
                inputLayout.addView(textView, params);
            }
        }

        // 完成 keyboardLayout
        TableLayout keyboardLayout = binding.KeyboardLayout;
        for (int i = 0; i < keyboardLayout.getChildCount(); i++) {
            View child = keyboardLayout.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) child;
                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    View buttonView = linearLayout.getChildAt(j);
                    if (buttonView instanceof Button) {
                        Button button = (Button) buttonView;
                        // TODO begin
                        // 给 button 安装上监听器，使得按下 button 时出发 handleButtonPress
                        // TODO end
                    }
                }
            }
        }
        startNewGame();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private char getCharFromButton(Button button) {
        int buttonId = button.getId();
        if (buttonId == R.id.button_A) {
            return 'A';
        } else if (buttonId == R.id.button_B) {
            return 'B';
        } else if (buttonId == R.id.button_C) {
            return 'C';
        } else if (buttonId == R.id.button_D) {
            return 'D';
        } else if (buttonId == R.id.button_E) {
            return 'E';
        } else if (buttonId == R.id.button_F) {
            return 'F';
        } else if (buttonId == R.id.button_G) {
            return 'G';
        } else if (buttonId == R.id.button_H) {
            return 'H';
        } else if (buttonId == R.id.button_I) {
            return 'I';
        } else if (buttonId == R.id.button_J) {
            return 'J';
        } else if (buttonId == R.id.button_K) {
            return 'K';
        } else if (buttonId == R.id.button_L) {
            return 'L';
        } else if (buttonId == R.id.button_M) {
            return 'M';
        } else if (buttonId == R.id.button_N) {
            return 'N';
        } else if (buttonId == R.id.button_O) {
            return 'O';
        } else if (buttonId == R.id.button_P) {
            return 'P';
        } else if (buttonId == R.id.button_Q) {
            return 'Q';
        } else if (buttonId == R.id.button_R) {
            return 'R';
        } else if (buttonId == R.id.button_S) {
            return 'S';
        } else if (buttonId == R.id.button_T) {
            return 'T';
        } else if (buttonId == R.id.button_U) {
            return 'U';
        } else if (buttonId == R.id.button_V) {
            return 'V';
        } else if (buttonId == R.id.button_W) {
            return 'W';
        } else if (buttonId == R.id.button_X) {
            return 'X';
        } else if (buttonId == R.id.button_Y) {
            return 'Y';
        } else if (buttonId == R.id.button_Z) {
            return 'Z';
        } else if (buttonId == R.id.button_BACKSPACE) {
            return '-';
        } else if (buttonId == R.id.button_NEW_GAME) {
            return ' ';
        } else if (buttonId == R.id.button_ENTER) {
            return '\n';
        } else {
            return 0;
        }
    }

    private void handleButtonPress(Button button) {
        // 根据按钮的 ID 或文本执行不同的逻辑
        char ch = getCharFromButton(button);
        switch (ch) {
            case '-': processBackspace(); break;
            case ' ': processNewGame(); break;
            case '\n': processEnter(); break;
            default: processInput(ch); break;
        }
    }

    void processInput(char ch) {
        // TODO begin
        // 如果按下的是字母按键，且此时输入的字母数量不足 5，则需要修改 guessWord
        // TODO end
        displayWord();
    }

    void processBackspace() {
        // TODO begin
        // 如果按下的是字母按键，且此时输入的字母数量大于 0，则需要修改 guessWord
        // TODO end
        displayWord();
    }

    void startNewGame() {
        state.clear(wordSet.randomAnswer());
        // TODO begin
        // 清空 guessWord
        // TODO end
        // 清理所有 input
        GridLayout inputLayout = binding.InputLayout;
        for (int i = 0; i < inputLayout.getChildCount(); i++) {
            View child = inputLayout.getChildAt(i);
            if (child instanceof TextView) {
                ((TextView) child).setText(" ");
                child.setBackgroundResource(R.drawable.gray_border);
            }
        }
        // 清理所有 keyboard
        displayState();
    }

    void processNewGame() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle("新的游戏")
            .setMessage("你确定要放弃此轮游戏吗？")
            .setPositiveButton("是，并查看答案", (dialogInterface, i) -> {
                Toast.makeText(getContext(), "答案为 " + state.answer.toLowerCase() + "。", Toast.LENGTH_SHORT).show();
                User user = ((MainActivity) getActivity()).loadUser();
                user.totalRounds += 1;
                ((MainActivity) getActivity()).saveUser(user);
                // 重新开始游戏
                startNewGame();
            })
            .setNegativeButton("否", (dialog, which) -> Log.i("DialogBuilder","点击了否"))
            .create().show();
    }

    void processEnter() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        if (guessWord.length() == WORD_LENGTH && wordSet.isNotAccWord(String.valueOf(guessWord))) {
            dialogBuilder.setTitle("错误")
                .setMessage("未找到这个单词！")
                .setPositiveButton("好的", (dialog, which) -> Log.i("DialogBuilder","点击了好的"))
                .create().show();
            return;
        }
        state.word = String.valueOf(guessWord);
        guessWord.delete(0, guessWord.length());
        state = guess(state);
        displayState();

        User user = ((MainActivity) getActivity()).loadUser();
        switch (state.status) {
            case WON:
                // TODO begin
                // 弹出对话框，问是否再来一局
                // 对 user 数据进行修改
                // TODO end
                // 保存用户数据
                ((MainActivity) getActivity()).saveUser(user);
                break;
            case LOST:
                // TODO begin
                // 弹出对话框，问是否再来一局
                // 对 user 数据进行修改
                // TODO end
                // 保存用户数据
                ((MainActivity) getActivity()).saveUser(user);
                break;
            default:
                break;
        }

    }

    void displayWord() {
        // 在对应行显示 guessWord
        GridLayout inputLayout = binding.InputLayout;

        int row = TOTAL_CHANCES - state.chancesLeft;
        for (int col = 0; col < inputLayout.getColumnCount(); col++) {
            int i = row * inputLayout.getColumnCount() + col;
            View child = inputLayout.getChildAt(i);
            if (child instanceof TextView) {
                try {
                    Log.d("Guess Word", "guessWord[i] = " + guessWord.charAt(col));
                    ((TextView) child).setText(String.valueOf(guessWord.charAt(col)));
                } catch (IndexOutOfBoundsException e) {
                    Log.d("Guess Word", "Filling space.");
                    ((TextView) child).setText(" ");
                }
            }
        }
    }

    void displayState() {
        // 根据 state 更改当前行 guessWord 字母的颜色
        GridLayout inputLayout = binding.InputLayout;
        Log.d("Display State", "Displaying InputLayout...");

        int row = TOTAL_CHANCES - state.chancesLeft - 1;
        for (int col = 0; col < inputLayout.getColumnCount(); col++) {
            int i = row * inputLayout.getColumnCount() + col;
            View child = inputLayout.getChildAt(i);
            if (child instanceof TextView) {
                char ch = ((TextView) child).getText().charAt(0);
                if (ch == ' ') {
                    child.setBackgroundResource(R.drawable.gray_border);
                } else {
                    switch (state.wordState[col]) {
                        case RED: child.setBackgroundResource(R.drawable.red_border); break;
                        case YELLOW: child.setBackgroundResource(R.drawable.yellow_border); break;
                        case GREEN: child.setBackgroundResource(R.drawable.green_border); break;
                        default: child.setBackgroundResource(R.drawable.gray_border); break;
                    }
                }
            }
        }

        // 根据 state 更改当前 26 个字母的颜色
        TableLayout keyboardLayout = binding.KeyboardLayout;
        Log.d("Display State", "Displaying KeyboardLayout...");
        // 不处理最后一行的按钮，因为它们是 BACKSPACE、NEW GAME 和 ENTER，所以这里 -1
        for (int i = 0; i < keyboardLayout.getChildCount() - 1; i++) {
            View child = keyboardLayout.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) child;
                for (int j = 0; j < linearLayout.getChildCount(); j++) {
                    View buttonView = linearLayout.getChildAt(j);
                    if (buttonView instanceof Button) {
                        Button button = (Button) buttonView;
                        char ch = getCharFromButton(button);
                        // TODO begin
                        // 修改 button 的背景颜色
                        // 提示：使用 state.alphabetState[ch - 'A'].getRgbCode()
                        // TODO end
                    }
                }
            }
        }
    }
}
