package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerText;
    SeekBar timerseekBar;
    CountDownTimer countDownTimer;
    boolean counterIsActive = false;
    Button button;

    public void resetTimer(){
        timerText.setText("00:30");
        timerseekBar.setProgress(30);
        timerseekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go!");
        counterIsActive = false;

    }

    public void buttonClick(View view){
        if(counterIsActive) {

            resetTimer();

            } else {

            counterIsActive = true;
            timerseekBar.setEnabled(false);
            button.setText("STOP!");

              countDownTimer = new CountDownTimer(timerseekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dixiehorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes*60);
        String secondsString = Integer.toString(seconds);
        String minuteString = Integer.toString(minutes);
        if(seconds<=9){
            secondsString = "0"+secondsString;
        }

        if(minutes<=9){
            minuteString = "0"+minuteString;
        }
        timerText.setText(minuteString+":"+secondsString);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button)findViewById(R.id.button);
        timerseekBar = (SeekBar) findViewById(R.id.seekBar);
        timerText = (TextView) findViewById(R.id.textView);

        timerseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}