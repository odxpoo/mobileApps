package com.example.gambling;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView currentRoll, totalRoll;
    Button resetButton, throwDiesButton;
    ImageView[] dice = new ImageView[5];
    int[] rollTable = new int[5];
    int totalValue, currentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();
        SlotsMachine slotsMachine = new SlotsMachine(5);
        totalValue = 0;

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotsMachine.reset();
                resetDice();
                currentRoll.setText("This Roll:");
                totalRoll.setText("Total Roll:");

                totalValue = 0;
                currentValue = 0;
            }
        });

        throwDiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollTable = slotsMachine.roll();
                currentValue = slotsMachine.countReels();
                totalValue += currentValue;
                setDice();
                setText();
            }
        });
    }

    private void initialize() {
        currentRoll = findViewById(R.id.currentRollsText);
        totalRoll = findViewById(R.id.totalRollsText);
        resetButton = findViewById(R.id.resetButton);
        throwDiesButton = findViewById(R.id.throw_button);

        // I mean... the same as below idk... 🤗🤗🤗
        dice[0] = findViewById(R.id.dice1);
        dice[1] = findViewById(R.id.dice2);
        dice[2] = findViewById(R.id.dice3);
        dice[3] = findViewById(R.id.dice4);
        dice[4] = findViewById(R.id.dice5);
    }

    private void resetDice() {
        // I rewrote them variables into a table and it stays this way for now 😬😬😬
        dice[0].setImageResource(R.mipmap.unknown_roll);
        dice[1].setImageResource(R.mipmap.unknown_roll);
        dice[2].setImageResource(R.mipmap.unknown_roll);
        dice[3].setImageResource(R.mipmap.unknown_roll);
        dice[4].setImageResource(R.mipmap.unknown_roll);
    }

    private void setDice() {
        for(int i = 0; i < dice.length; i++) {
            switch(rollTable[i]) {
                default: dice[i].setImageResource(R.mipmap.unknown_roll); break;
                case 1: dice[i].setImageResource(R.mipmap.roll_1); break;
                case 2: dice[i].setImageResource(R.mipmap.roll_2); break;
                case 3: dice[i].setImageResource(R.mipmap.roll_3); break;
                case 4: dice[i].setImageResource(R.mipmap.roll_4); break;
                case 5: dice[i].setImageResource(R.mipmap.roll_5); break;
                case 6: dice[i].setImageResource(R.mipmap.roll_6); break;
            }
        }
    }

    private void setText() {
        currentRoll.setText("This Roll: " + currentValue);
        totalRoll.setText("Total Roll: " + totalValue);
    }
}