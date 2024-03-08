package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    TextView total_questions;
    TextView question;
    Button ans_a, ans_b, ans_c, ans_d, btn_submit;
    int score = 0;
    int total_Questions = QuestionAns.question.length;
    int currentQuestionIndex =0;
    String selectedAnswer="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home_screen);

        total_questions = findViewById(R.id.total_questions);
        question = findViewById(R.id.question);
        ans_a = findViewById(R.id.ans_a);
        ans_b = findViewById(R.id.ans_b);
        ans_c = findViewById(R.id.ans_c);
        ans_d = findViewById(R.id.ans_d);
        btn_submit = findViewById(R.id.btn_submit);

        ans_a.setOnClickListener(this);
        ans_b.setOnClickListener(this);
        ans_c.setOnClickListener(this);
        ans_d.setOnClickListener(this);
        btn_submit.setOnClickListener(this);


        total_questions.setText("Total Questions: "+ total_Questions);

        loadNewQuestions();

    }

    private void loadNewQuestions() {
        if(currentQuestionIndex==total_Questions){
            finishQuiz();
            return;
        }
        question.setText(QuestionAns.question[currentQuestionIndex]);
        ans_a.setText(QuestionAns.choices[currentQuestionIndex][0]);
        ans_b.setText(QuestionAns.choices[currentQuestionIndex][1]);
        ans_c.setText(QuestionAns.choices[currentQuestionIndex][2]);
        ans_d.setText(QuestionAns.choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        String result;
        if(score>=total_Questions*0.6){
            result ="Passed";
        }else{
            result = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(result)
                .setMessage("Your score is "+score+" out of "+total_Questions)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0 ;
        currentQuestionIndex=0;
        loadNewQuestions();
    }

    @Override
    public void onClick(View view){
        ans_a.setBackgroundColor(Color.WHITE);
        ans_b.setBackgroundColor(Color.WHITE);
        ans_c.setBackgroundColor(Color.WHITE);
        ans_d.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.btn_submit){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(QuestionAns.correctAnswers[currentQuestionIndex])){
                    score++;
                }else{
                    clickedButton.setBackgroundColor((Color.MAGENTA));
                }
                currentQuestionIndex++;
                loadNewQuestions();
            }else{

            }
        }else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }
}