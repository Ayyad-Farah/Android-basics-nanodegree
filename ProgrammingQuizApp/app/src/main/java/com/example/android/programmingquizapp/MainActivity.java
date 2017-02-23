package com.example.android.programmingquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *
     * @return 1 if is the answer of question 1 correct 0 otherwise
     */
    private int question1(){
        EditText editText = (EditText)findViewById(R.id.question_1_value);
        String answer = editText.getText().toString();
        for (char ch : answer.toCharArray()) {
            if(ch < '0' || ch > '9')
                return 0;
        }
        if(answer.length() == 0)
            return 0;
        return 1;
    }
    /**
     *
     * @return 1 if is the answer of question 1 correct 0 otherwise
     */
    private int question2(){
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.question_2_value_A);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.question_2_value_2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.question_2_value_char);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.question_2_value_45);

        if(checkBox1.isChecked() && checkBox2.isChecked() && !checkBox3.isChecked() && !checkBox4.isChecked())
            return 1;
        return 0;
    }

    /**
     *
     * @return 1 if is the answer of question 1 correct 0 otherwise
     */
    private int question3() {
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.question_3_value_203);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.question_3_value_write);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.question_3_value_Twenty_one);

        if(radioButton2.isChecked())
            return 1;
        return 0;
    }

    /**
     *
     * @return 1 if is the answer of question 1 correct 0 otherwise
     */
    private int question4(){
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.question_4_value_100);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.question_4_value_299_01);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.question_4_value_one_two_three);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.question_4_value_String);
        if(checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked())
            return 1;
        return 0;
    }

    /**
     *
     * @return make toast for the score
     */
    public void showScore(View view) {
        int score = question1() + question2() + question3() + question4();
        Toast.makeText(this, "Your score is " + score, Toast. LENGTH_SHORT).show();
        Log.v("question 1 score","" + question1());
        Log.v("question 2 score","" + question2());
        Log.v("question 3 score","" + question3());
        Log.v("question 4 score","" + question4());
        Log.v("score","" + score);


    }
}
