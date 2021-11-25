package com.nire.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4;
    TextView questionText;
    TextView scoreText;
    int index = 0;
    int score = 0;
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.score);

        questions = new ArrayList<>();
        questions.add(new Question(
                "Сколько байт в гигабайте?",
                "1024",
                new String[] {"1024", "1000", "2000", "8"}
        ));
        questions.add(new Question(
                "Год начала Великой Отечественной войны?",
                "1941",
                new String[] {"1991", "1938", "1917", "1941"}
        ));
        questions.add(new Question(
                "Дата начала Второй мировой войны?",
                "1 сентября 1939",
                new String[] {"24 июня 1941", "1 сентября 1939", "30 января 2001", "11 сентября 2001"}
        ));
        questionText.setText(questions.get(index).text);
        setButtonText(questions.get(index).variants);

        button1.setOnClickListener((e) -> {
            answer(questions.get(index).variants[0]);
        });

        button2.setOnClickListener((e) -> {
            answer(questions.get(index).variants[1]);
        });

        button3.setOnClickListener((e) -> {
            answer(questions.get(index).variants[2]);
        });

        button4.setOnClickListener((e) -> {
            answer(questions.get(index).variants[3]);
        });
    }

    public void answer(String answer){
        checkAnswer(questions.get(index).rightAnswer, answer);
        scoreText.setText("Очков: " + score);
        if (!checkNextQuestion()) {
            return;
        }
        index++;
        setNextQuestion();
    }

    public void setButtonText(String [] variants){
        button1.setText(variants[0]);
        button2.setText(variants[1]);
        button3.setText(variants[2]);
        button4.setText(variants[3]);
    }

    public void setNextQuestion(){
        try {
            questionText.setText(questions.get(index).text);
            setButtonText(questions.get(index).variants);
        } catch (IndexOutOfBoundsException error) {
            Toast.makeText(this, "Вопросов больше нет", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkNextQuestion() {
        if (index >= questions.size() - 1) {
            Toast.makeText(this, "Вопросов больше нет, у вас " + score + " баллов", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void rightAnswer() {
        Toast.makeText(this, "Вы ответили правильно!", Toast.LENGTH_SHORT).show();
        score++;
    }

    public void negativeAnswer() {
        Toast.makeText(this, "Вы ответили не правильно", Toast.LENGTH_SHORT).show();
    }

    public void checkAnswer(String rightAnswer, String currentAnswer) {
        if (rightAnswer.equals(currentAnswer)) {
            rightAnswer();
        } else {
            negativeAnswer();
        }
    }
}