package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class InstructionsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        sharedPreferences= getSharedPreferences("MyPrefs",MODE_PRIVATE);

        firstTime= sharedPreferences.getBoolean("firstTime",true);

        if (firstTime == true) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime",firstTime);
                    editor.apply();

                    Intent mainIntent = new Intent(InstructionsActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 7000);
        }
        else{
            Intent mainIntent = new Intent(InstructionsActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
