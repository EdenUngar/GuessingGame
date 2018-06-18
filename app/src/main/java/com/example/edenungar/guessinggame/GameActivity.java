package com.example.edenungar.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Button guessButton;
    private TextView clue;
    private EditText guess;

    private int generatedNumber;
    private int numberOfGuesses = 0;
    private final int MAX_GUESS_COUNT = 4;
    //not 5 bc it starts at 0 (base 0) and so the fifth try would be #4
    public static final String winningNumber = "WINNING_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        guessButton = findViewById(R.id.submit_guess_button);
        clue = findViewById(R.id.clue_textview);
        guess = findViewById(R.id.guess_edittext);

        //generates a random number between 1 and 100
        generatedNumber = (int) Math.ceil(Math.random() * 100);

//        Toast.makeText(this, Integer.toString(generatedNumber), Toast.LENGTH_SHORT).show();

        setListener();

    }

    private void setListener() {

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateGuess();
            }
        });

    }

    //check to make sure the user has put in a valid number (not 0, >100, or negative)
    private void validateGuess() {
        try {
            int userGuess = Integer.parseInt(guess.getText().toString());
            if (userGuess > 100 || userGuess <= 0) {
                clue.setText(R.string.enter_number_1_to_100);
                //makes text above visible
                clue.setVisibility(View.VISIBLE);
                //clears out box to let user enter something else
                guess.setText("");
            } else {
                checkGuess(userGuess);
            }
        } catch (NumberFormatException nfe) {
            clue.setText(R.string.enter_number);
            clue.setVisibility(View.VISIBLE);
        }
    }

    //this method will take the input and check it against the generatedNumber
    //Depending on the outcome it will change accordingly or take us to the ResultsActivity
    private void checkGuess(int userGuess) {

        if (userGuess == generatedNumber) {
            //goes to ResultsActivity, user has guessed correctly
            Intent winner = new Intent(this, ResultsActivity.class);
            startActivity(winner);
        } else if (numberOfGuesses == MAX_GUESS_COUNT) {
            //goes to ResultsActivity, user has run out of chances
            Intent loser = new Intent(this, ResultsActivity.class);
            loser.putExtra(winningNumber, generatedNumber);
            startActivity(loser);
        } else if (userGuess < generatedNumber) {
            //updates clue TextView to say "higher", is VISIBLE, and increments numberOfGuesses by 1
            clue.setText(R.string.higher);
            clue.setVisibility(View.VISIBLE);
            guess.setText("");
            numberOfGuesses++;
            //to show how many tries you have left
            //how I did it
//            Toast.makeText(this, Integer.toString(5 - numberOfGuesses) + " guesses left", Toast.LENGTH_SHORT).show();
            //how the class did it
            Toast.makeText(this, getString(R.string.chances_left, (5 - numberOfGuesses)), Toast.LENGTH_SHORT).show();
        } else if (userGuess > generatedNumber) {
            //updates clue TextView to say "lower", and increments numberOfGuesses by 1
            clue.setText(R.string.lower);
            clue.setVisibility(View.VISIBLE);
            guess.setText("");
            numberOfGuesses++;
            //displays number of guesses left as a toast
            //how I did it
//            Toast.makeText(this, Integer.toString(5 - numberOfGuesses) + " guesses left", Toast.LENGTH_SHORT).show();
            //how the class did it
            Toast.makeText(this, getString(R.string.chances_left,(5 - numberOfGuesses)), Toast.LENGTH_SHORT).show();
        }
    }


    //control + type onBackPressed
    @Override
    public void onBackPressed() {
        //removed super.onBackPressed to make sure if back button is pressed nothing will happen
    }
}
