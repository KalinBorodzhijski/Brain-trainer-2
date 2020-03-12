package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    Button playAgain;
    TextView answerTextView;
    TextView time;
    TextView trueTotal;
    TextView questionTextView;
    int secondNumber;
    int firstNumber;
    Button zero;
    Button one;
    Button two;
    Button three;
    int userAnswer;
    int rightCount = 0, qCount = 0;
    int randomGeneratePosition;
    CountDownTimer countDownTimer;

    public int randomGenerator(int min,int max){
        return rand.nextInt((max - min) + 1) + min;
    }

    public void playAgain(View view){
        rightCount = 0;
        qCount = 0;
        countDownTimer.start();
        playAgain.setVisibility(View.INVISIBLE);
        trueTotal.setText("0/0");
        rightTotal(rightCount,qCount);
        zero.setEnabled(true);
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        answerTextView.setVisibility(View.INVISIBLE);


    }
    public void question(){
        questionTextView.setVisibility(View.VISIBLE);
        firstNumber = randomGenerator(1,50);
        secondNumber = randomGenerator(1,50);
        questionTextView.setText(firstNumber + " + " + secondNumber);
    }
//Генерира грешни отговори
    public void ans(int position){
        Button[] buttons = {zero,one,two,three};
        for (int i = 0; i < buttons.length; i++) {
            if(i != position){
                int random = randomGenerator(1,100);
                while(Integer.parseInt(buttons[position].getText().toString()) == random){
                    random = randomGenerator(1,100);
                }
                buttons[i].setText(random+ "");
            }
        }

    }
    public void rightTotal(int rightCount,int total){

        trueTotal.setText(rightCount+"/" + total);
    }
    //Генерира отговори, когато е цъкнат някой от бутоните
    public void generateTrueAnswers(View view){
        question();
        answerTextView.setVisibility(View.VISIBLE);
        if(userAnswer == randomGeneratePosition) {
            rightCount++;
            answerTextView.setText("Correct");
        }
        else{
            answerTextView.setText("Wrong");
        }


        randomGeneratePosition = randomGenerator(0,3);

        if(randomGeneratePosition == 0)zero.setText(""+(firstNumber + secondNumber));
        else if(randomGeneratePosition == 1)one.setText(""+(firstNumber + secondNumber));
        else if(randomGeneratePosition == 2)two.setText(""+(firstNumber + secondNumber));
        else if(randomGeneratePosition == 3) three.setText(""+(firstNumber + secondNumber));
        ans(randomGeneratePosition);
        qCount++;
        rightTotal(rightCount,qCount);

    }
    public void goClick(View view){
        TextView goTextView = findViewById(R.id.textView);
        goTextView.setVisibility(View.INVISIBLE);
        question();

        zero.setVisibility(View.VISIBLE);
        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        trueTotal.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);


        trueTotal.setText("0/0");

        randomGeneratePosition = randomGenerator(0,3);
        if(randomGeneratePosition == 0)zero.setText(""+(firstNumber + secondNumber));
        else if(randomGeneratePosition == 1)one.setText(""+(firstNumber + secondNumber));
        else if(randomGeneratePosition == 2)two.setText(""+(firstNumber + secondNumber));
        else if(randomGeneratePosition == 3) three.setText(""+(firstNumber + secondNumber));

        ans(randomGeneratePosition);

        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int timeInSeconds =(int) (millisUntilFinished/1000);
                time.setText(timeInSeconds + "s");
            }

            @Override
            public void onFinish() {
                answerTextView.setText("Done!");
                finish();
                playAgain.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void finish(){
        zero.setEnabled(false);
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setVisibility(View.INVISIBLE);
        answerTextView = findViewById(R.id.textView2);
        playAgain = findViewById(R.id.playAgainButton);

        zero = findViewById(R.id.button0);
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        trueTotal = findViewById(R.id.wrongTotalTextView);
        time = findViewById(R.id.timerTextView);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = 0;
                generateTrueAnswers(answerTextView);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = 1;
                generateTrueAnswers(answerTextView);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = 2;
                generateTrueAnswers(answerTextView);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = 3;
                generateTrueAnswers(answerTextView);
            }
        });
    }
}
