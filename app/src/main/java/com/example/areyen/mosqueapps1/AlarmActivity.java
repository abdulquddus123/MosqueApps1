package com.example.areyen.mosqueapps1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.areyen.mosqueapps1.Alarm.AlarmReceiver;

import java.io.IOException;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    MediaPlayer mPlayer;
    int a=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
       starAzan();
    }
    public void starAzan(){
        if(a==2){
            int currentOur = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            if(currentOur >= 1 && 8 >= currentOur){
                mPlayer = MediaPlayer.create(this, R.raw.azan);
                mPlayer.start();
                a=1;
            }
            else{
                mPlayer = MediaPlayer.create(this, R.raw.azanzohor);
                      mPlayer.setVolume(0.05f,0.05f);
                mPlayer.start();
                a=1;
            }

        }
        else {

        }



    }
    public void stopAzan(View view) {
      //  mPlayer = MediaPlayer.create(this, R.raw.azanzohor);
     //   mPlayer.pause();

//        Intent intent = new Intent(this, Mote.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1253, intent, 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.cancel(pendingIntent);


        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this,4, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        am.cancel(sender);
        mPlayer.stop();

                Intent intent7 = new Intent(AlarmActivity.this, MainActivity.class);
                startActivity(intent7);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
