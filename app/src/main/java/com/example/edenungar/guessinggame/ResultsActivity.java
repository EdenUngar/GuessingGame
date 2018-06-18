package com.example.edenungar.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.edenungar.guessinggame.GameActivity.winningNumber;

public class ResultsActivity extends AppCompatActivity {

    private Button playAgainButton;
    private TextView correctNumberTextview;
    private TextView resultsTextview;
    private ImageView resultImageview;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        playAgainButton = findViewById(R.id.restart_button);
        correctNumberTextview = findViewById(R.id.number_textview);
        resultsTextview = findViewById(R.id.results_textview);
        resultImageview = findViewById(R.id.winning_imageview);
        //get winning number from intent if user has lost
        intent = getIntent();
        if (intent.hasExtra(winningNumber)) {
            setLosingData();
        }

        setListener();
    }

    private void setLosingData() {

        int winningNumber = intent.getIntExtra(GameActivity.winningNumber, 0);
        resultsTextview.setText(R.string.you_lost);
        //set the view that shows the winning number the (getString(R.string.winning_number, winningNumber)) sets the %1$d to the winning number
        correctNumberTextview.setText(getString(R.string.winning_number, winningNumber));
        correctNumberTextview.setVisibility(View.VISIBLE);
        //set losing image
        resultImageview.setImageResource(R.drawable.losingsadface);
    }

    private void setListener() {
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startGame();
                finish();
            }
        });
    }

//    //button will go back to the GameActivity class
//    private void startGame() {
//        Intent playGame = new Intent(this, GameActivity.class);
//        startActivity(playGame);
//    }

}
