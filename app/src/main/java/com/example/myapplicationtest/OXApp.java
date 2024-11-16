package com.example.myapplicationtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OXApp extends AppCompatActivity {
    String turnLetter = "X";
    boolean gameOver = false;
    Button[] buttons = new Button[9];
    int turnCounter = 0;
    TextView turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.xo_page);
        buttons[0] = findViewById(R.id.lt);
        buttons[1] = findViewById(R.id.mt);
        buttons[2] = findViewById(R.id.rt);
        buttons[3] = findViewById(R.id.lm);
        buttons[4] = findViewById(R.id.mm);
        buttons[5] = findViewById(R.id.rm);
        buttons[6] = findViewById(R.id.lb);
        buttons[7] = findViewById(R.id.mb);
        buttons[8] = findViewById(R.id.rb);
        for (Button each : buttons) {
            each.setText("-");
            each.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    move(each);
                }
            });
        }
        turn = (TextView) findViewById(R.id.turn);
        String newTurnText = "Turn: "+turnLetter;
        turn.setText(newTurnText);
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnLetter = "X";
                turnCounter = 0;
                String newTurnText = "Turn: "+turnLetter;
                turn.setText(newTurnText);
                for (Button b : buttons) {
                    b.setText("-");
                }
                gameOver = false;
            }
        });
    }

    public void alert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OXApp.this);
        builder.setTitle("Game Over");
        builder.setMessage(message);
        builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {dialog.cancel();});
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void move(Button i) {
        if (!gameOver && i.getText().equals("-")) {
            turnCounter++;
            i.setText(turnLetter);
            AutoMarker currentGrid = new AutoMarker(buttons);
            if (currentGrid.fullCheck()) {
                alert("Status: " + turnLetter + " has won in " + String.valueOf(turnCounter) + " turns.");
                gameOver = true;
            }
            else {
                if (turnCounter >= 9) {
                    alert("Status: Game is a Tie.");
                    //status.setText("Status: Game is a Tie.");
                    gameOver = true;
                }
                else {
                    switch (turnLetter) {
                        case "X":
                            turnLetter = "O";
                            break;
                        case "O":
                            turnLetter = "X";
                            break;
                    }
                    String newTurnText = "Turn: "+turnLetter;
                    turn.setText(newTurnText);
                }
            }
        }
    }
}