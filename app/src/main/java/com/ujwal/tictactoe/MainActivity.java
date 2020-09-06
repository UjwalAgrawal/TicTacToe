package com.ujwal.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    //0: cross 1:zero 2: Have not been tapped
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8} ,{0,3,6},{1,4,7},{2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameActive = true;

    public void appear(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive){    //Only one time tapping
        gameState[tappedCounter] = activePlayer;

        if(activePlayer==0){
        counter.setImageResource(R.drawable.cross);
        activePlayer=1;
        }
        else {
            counter.setImageResource(R.drawable.zero);
            activePlayer=0;
        }
        counter.animate().alpha(1).setDuration(500);

        for(int[] winningPositon: winningPositions){
            if(gameState[winningPositon[0]]== gameState[winningPositon[1]] && gameState[winningPositon[1]]== gameState[winningPositon[2]] && gameState[winningPositon[0]]!=2){
                gameActive = false;
                TextView winnerMsg = (TextView) findViewById(R.id.winnerTextView);
                Button playAgainButton  = (Button) findViewById(R.id.playAgainButton);
                if(activePlayer==0){
                    winnerMsg.setText("Zero has won!!!");
                }
                else {
                    winnerMsg.setText("Cross has won!!!");
                }
            }
        }
        }
    }

    public void play(View view){
        TextView winnerMsg = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton  = (Button) findViewById(R.id.playAgainButton);
        GridLayout boardGridLayout  = findViewById(R.id.gridLayout);
        for(int i=0; i<boardGridLayout.getChildCount(); i++){
            ImageView imgView = (ImageView) boardGridLayout.getChildAt(i);
            imgView.setImageDrawable(null);
        }
        activePlayer = 0;
        for(int a = 0; a<9; a++){
            gameState[a]= 2;
        }
        winnerMsg.setText("");
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}