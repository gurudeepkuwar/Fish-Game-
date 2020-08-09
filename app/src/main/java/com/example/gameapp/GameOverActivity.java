package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private Button StartAgainBtn;
    private Button ExitGameBtn;
    private TextView DisplayScore;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

          StartAgainBtn = (Button) findViewById(R.id.play_again_btn);
          ExitGameBtn = (Button) findViewById(R.id.exit_btn);
          DisplayScore = (TextView) findViewById(R.id.displayscore);

          score = getIntent().getExtras().get("Score").toString();

          StartAgainBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(GameOverActivity.this,MainActivity.class);
                  startActivity(intent);
              }
          });

          DisplayScore.setText("Score: "+score);

          ExitGameBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  finish();
              }
          });
    }
}
