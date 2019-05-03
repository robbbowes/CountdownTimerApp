package com.example.countdowntimerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        seekBarTimer.setMax(1200);

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

    public String displayTimeCorrectly(int i) {
        int minutes = i / 60;
        int seconds = i % 60;
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return minutes + ":" + decimalFormat.format(seconds);
    }
}
