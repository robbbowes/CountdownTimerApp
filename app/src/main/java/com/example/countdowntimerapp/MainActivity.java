package com.example.countdowntimerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    int timeToDisplayInt;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView seekBarTextView = findViewById(R.id.seekBarTextView);
        SeekBar seekBarTimer = findViewById(R.id.seekBarTime);
        final int max = 1200;
        final int startingProgress = 600;
        timeToDisplayInt = startingProgress;
        seekBarTimer.setMax(max);
        seekBarTimer.setProgress(startingProgress);
        String startingProgressString = displayTimeCorrectly(startingProgress);
        seekBarTextView.setText(startingProgressString);

        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                if (progress < min) {
                    timeToDisplayInt = min;
                    seekBar.setProgress(min);
                } else {
                    timeToDisplayInt = progress;
                }
                seekBarTextView.setText(displayTimeCorrectly(timeToDisplayInt));

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
        countDownTimer = createTimer();
        countDownTimer.start();
    }

    public void clickStop(View view) {
        startStopHelper(true);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startStopHelper(boolean enable) {
        SeekBar seekBar = findViewById(R.id.seekBarTime);
        seekBar.setEnabled(enable);
        findViewById(R.id.startButton).setEnabled(enable);
    }

    private String displayTimeCorrectly(int i) {
        int minutes = i / 60;
        int seconds = i % 60;
        DecimalFormat decimalFormat = new DecimalFormat("00");
        return decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds);
    }

    private CountDownTimer createTimer() {
        return new CountDownTimer(timeToDisplayInt * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeToDisplayInt -= 1;
                Log.i("Counting", String.valueOf(timeToDisplayInt));
            }

            @Override
            public void onFinish() {

            }
        };
    }


}
