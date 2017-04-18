package com.example.android.footballscorestracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int teamAGoals = 0;
    private int teamAFouls = 0;
    private int teamBGoals = 0;
    private int teamBFouls = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *this method for update score for team A its add 1 to its goals and display it.
     */
    public void teamAscore(View view) {
        teamAGoals += 1;
        displayGoalForTeamA();
    }

    /**
     *this method for update Fouls for team A its add 1 to its Fouls and display it.
     */
    public void teamAHasAFoul(View view) {
        teamAFouls += 1;
        displayFoulForTeamA();
    }

    /**
     *this method for update score for team B its add 1 to its goals and display it.
     */
    public void teamBscore(View view) {
        teamBGoals += 1;
        displayGoalForTeamB();
    }

    /**
     *this method for update Fouls for team B its add 1 to its Fouls and display it.
     */
    public void teamBHasAFoul(View view) {
        teamBFouls += 1;
        displayFoulForTeamB();
    }

    /**
     *this method reset goals and fouls of teams A and B and make all of them 0 and displays the app
     *  like for first time.
     */
    public void reset(View view) {
        teamAGoals = 0;
        teamAFouls = 0;
        teamBGoals = 0;
        teamBFouls = 0;
        displayGoalForTeamA();
        displayFoulForTeamA();
        displayGoalForTeamB();
        displayFoulForTeamB();
    }

    /**
     * Display goals for team A on screen
     */
    public void displayGoalForTeamA() {
        TextView scoreView = (TextView) findViewById(R.id.team_a_goals);
        scoreView.setText(String.valueOf(teamAGoals));
    }

    /**
     * Display goals for team B on screen
     */
    public void displayGoalForTeamB() {
        TextView scoreView = (TextView) findViewById(R.id.team_b_goals);
        scoreView.setText(String.valueOf(teamBGoals));
    }

    /**
     * Display Fouls for team A on screen
     */
    public void displayFoulForTeamA() {
        TextView scoreView = (TextView) findViewById(R.id.team_a_fouls);
        scoreView.setText(String.valueOf(teamAFouls));
    }

    /**
     * Display Fouls for team B on screen
     */
    public void displayFoulForTeamB() {
        TextView scoreView = (TextView) findViewById(R.id.team_b_fouls);
        scoreView.setText(String.valueOf(teamBFouls));
    }
}
