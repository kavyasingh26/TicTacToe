package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean isWinner = false;
    int imageClicked = -1;
    int player = 1;//player 1 is cross
    int[][] winningStates = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public void load(View view) {
        ImageView v = (ImageView) view;
        int tag = Integer.parseInt(v.getTag().toString());
        imageClicked = gameState[tag];
        v.setImageResource(R.drawable.Cross);
        if (!isWinner && imageClicked == -1) {
            if (player == 1) {
                v.setImageResource(R.drawable.Cross);
                gameState[tag] = player;
                Toast.makeText(this, tag + "" + "cross", Toast.LENGTH_SHORT).show();
                player = 0;
            } else {
                v.setImageResource(R.drawable.Zero);
                gameState[tag] = player;
                Toast.makeText(this, tag + "" + "zero", Toast.LENGTH_SHORT).show();
                player = 1;
            }
            for (int[] winningState : winningStates) {
                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] > -1) {
                    Toast.makeText(this, "Winner is " + (player == 0 ? 1 : 0), Toast.LENGTH_SHORT).show();
                    isWinner = true;

                }
            }
        }
    }


    public void reset(View view){
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        int total_images= gridLayout.getChildCount();
        for(int i=0;i<total_images;i++){
            ImageView v= (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
        }
        isWinner=false;
        imageClicked=-1;
        player=1;
        Arrays.fill(gameState, -1);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}