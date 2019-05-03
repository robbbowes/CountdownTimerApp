package com.example.countdowntimerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    int timeToDisplayInt;
    boolean manuallyStopped;
    boolean isPaused;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView seekBarTextView = findViewById(R.id.seekBarTextView);
        SeekBar seekBarTimer = findViewById(R.id.seekBarTime);
        final int max = 600;
        final int startingProgress = max / 2;
        timeToDisplayInt = startingProgress;
        seekBarTimer.setMax(max);
        seekBarTimer.setProgress(startingProgress);
        String startingProgressString = displayTimeCorrectly(startingProgress);
        seekBarTextView.setText(startingProgressString);

        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 0;
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
        manuallyStopped = false;
        SeekBar seekBar = findViewById(R.id.seekBarTime);
        timeToDisplayInt = seekBar.getProgress();
        countDownTimer = createTimer();
        countDownTimer.start();
    }

    public void clickStop(View view) {
        startStopHelper(true);
        manuallyStopped = true;
        SeekBar seekBar = findViewById(R.id.seekBarTime);
        timeToDisplayInt = seekBar.getProgress();
        TextView seekBarTextView = findViewById(R.id.seekBarTextView);
        seekBarTextView.setText(displayTimeCorrectly(timeToDisplayInt));
        if (countDownTimer != null) {
            setSolid(seekBarTextView);
            countDownTimer.cancel();
            countDownTimer.onFinish();
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void clickPause(View view) {
        if (countDownTimer != null) {
            TextView seekBarTextView = findViewById(R.id.seekBarTextView);
            if (!isPaused) {
                countDownTimer.cancel();
                makeBlink(seekBarTextView);
                isPaused = true;
            } else {
                countDownTimer.start();
                setSolid(seekBarTextView);
                isPaused = false;
            }
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

    private void setSolid(TextView textview) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        textview.startAnimation(animation);
    }

    private void makeBlink(TextView textView) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        textView.startAnimation(animation);
    }

    private void playBeeper() {
        mediaPlayer = MediaPlayer.create(this, R.raw.beeper);
        mediaPlayer.start();
    }

    private CountDownTimer createTimer() {
        return new CountDownTimer(timeToDisplayInt * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeToDisplayInt -= 1;
                TextView seekBarTextView = findViewById(R.id.seekBarTextView);
                seekBarTextView.setText(displayTimeCorrectly(timeToDisplayInt));
                SeekBar seekBar = findViewById(R.id.seekBarTime);
                seekBar.setProgress(timeToDisplayInt);
                Log.i("Counting", String.valueOf(timeToDisplayInt));
            }

            @Override
            public void onFinish() {
                if (!manuallyStopped) {
                    Log.i("onFinish()", "Ran to end");
                    playBeeper();
                } else {
                    Log.i("onFinish()", "Manually stopped");
                }
            }
        };
    }


}
