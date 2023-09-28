package com.farez.projectsbp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.farez.projectsbp.R;

public class MainActivity extends AppCompatActivity {
    Button btnCariGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCariGame = findViewById(R.id.button);
        btnCariGame.setOnClickListener( view -> {
            Intent intent = new Intent(this, spekInputActivity.class);
            startActivity(intent);
        });

    }
}