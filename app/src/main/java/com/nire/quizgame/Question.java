package com.nire.quizgame;

public class Question {
    String text;
    String rightAnswer;
    String [] variants;

    public Question(String text, String rightAnswer, String [] variants) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        this.variants = variants;
    }
}
