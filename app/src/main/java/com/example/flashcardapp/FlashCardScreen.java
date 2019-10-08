package com.example.flashcardapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FlashCardScreen extends AppCompatActivity {

    private Button btnToGenerateGame;
    private TextView firstNum;
    private TextView secondNum;
    private EditText answerInput;
    private Button btnToCheckAnswer;
    private TextView ResultsTextView;
    private TextView divisionSymbol;
    private TextView yourAnswerTextView;
    private int roundCount = 0;
    private int numberOfCorrectAnswers = 0;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rounds", roundCount);
        outState.putInt("correct", numberOfCorrectAnswers);
        outState.putString("firstNum", firstNum.getText().toString());
        outState.putString("secondNum", secondNum.getText().toString());
        outState.putString("div", divisionSymbol.getText().toString());
        outState.putString("answer", answerInput.getText().toString());
        outState.putString("your", yourAnswerTextView.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            roundCount = savedInstanceState.getInt("rounds");
            numberOfCorrectAnswers = savedInstanceState.getInt("correct");
            firstNum.setText(savedInstanceState.getString("firstNum"));
            secondNum.setText(savedInstanceState.getString("secondNum"));
            divisionSymbol.setText(savedInstanceState.getString("div"));
            answerInput.setText(savedInstanceState.getString("answer"));
            yourAnswerTextView.setText(savedInstanceState.getString("your"));

        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_screen);

        btnToGenerateGame = findViewById(R.id.btnToGenerateGame);
        ResultsTextView = findViewById(R.id.ResultsTextView);
        divisionSymbol = findViewById(R.id.divisionSymbol);
        firstNum = findViewById(R.id.firstNum);
        secondNum = findViewById(R.id.secondNum);
        answerInput = findViewById(R.id.answerInput);
        yourAnswerTextView = findViewById(R.id.yourAnswerTextView);
        btnToCheckAnswer = findViewById(R.id.btnToCheckAnswer);


        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");

        if (savedInstanceState == null) {
            Toast.makeText(getApplicationContext(), "Welcome " + name, Toast.LENGTH_LONG).show();
        }


        btnToGenerateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                divisionSymbol.setText("÷");
                answerInput.setEnabled(true);
                generateGame();
            }
        });

        btnToCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roundCount < 10) {
                    int firstNumber = Integer.parseInt(firstNum.getText().toString());
                    int secondNumber = Integer.parseInt(secondNum.getText().toString());
                    int realAnswer = firstNumber/secondNumber;
                    int userAnswer = Integer.parseInt(answerInput.getText().toString());
                    if (realAnswer == userAnswer){
                        numberOfCorrectAnswers++;
                        roundCount++;
                        answerInput.setText("");
                        generateGame();
                    }
                    else {
                        roundCount++;
                        answerInput.setText("");
                        generateGame();
                    }

                }
            }
        });
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    public void generateGame(){

        if (roundCount < 10) {
            newRound();
        }
        else {
            hideKeyboardFrom(getApplicationContext(), findViewById(android.R.id.content));
            Toast.makeText(getApplicationContext(),"Game Over! Score: "+numberOfCorrectAnswers+"/10", Toast.LENGTH_LONG).show();
            firstNum.setText("");
            secondNum.setText("");
            answerInput.setText("");
            divisionSymbol.setText("");
            numberOfCorrectAnswers = 0;
            roundCount = 0;
            answerInput.setEnabled(false);

        }
    }

    public void newRound(){
        Random rand = new Random();
        int firstRandomNumber = rand.nextInt(12 - 1) + 1;
        int secondRandomNumber = rand.nextInt(12 - 1) + 1;
        firstNum.setText(String.valueOf(firstRandomNumber * secondRandomNumber));
        secondNum.setText(String.valueOf(firstRandomNumber));
    }

}