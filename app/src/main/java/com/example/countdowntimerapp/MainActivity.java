package com.example.countdowntimerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView seekBarTextView = findViewById(R.id.seekBarTextView);
        SeekBar seekBarTimer = findViewById(R.id.seekBarTime);
        final int max = 1200;
        final int startingProgress = 600;
        seekBarTimer.setMax(max);
        seekBarTimer.setProgress(startingProgress);
        String startingProgressString = displayTimeCorrectly(startingProgress);
        seekBarTextView.setText(startingProgressString);

        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timeToDisplay;
                if (progress < min) {
                    timeToDisplay = min;
                    seekBar.setProgress(min);
                } else {
                    timeToDisplay = progress;
                }
                seekBarTextView.setText(displayTimeCorrectly(timeToDisplay));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void clickStart(View view) {
        startStopHelper(false);
    }

    public void clickStop(View view) {
        startStopHelper(true);
    }

    private void startStopHelper(boolean enable) {
        SeekBar seekBar = findViewById(R.id.seekBarTime);
        seekBar.setEnabled(enable);
        findViewById(R.id.startButton).setEnabled(enable);
    }

    public String displayTimeCorrectly(int i) {
        int minutes = i / 60;
        int seconds = i % 60;
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds);
    }
}
