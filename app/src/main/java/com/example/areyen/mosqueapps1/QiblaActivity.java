package com.example.areyen.mosqueapps1;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QiblaActivity extends AppCompatActivity implements SensorEventListener {
    Button home;
    TextView iccuk,webLink;
    ImageButton twiter,facebook;
    private Location userLocation;
    private LocationValues loc;
    private SensorManager mSensorManager;
    private float degreeDiff = -280.23f;
    private ImageView imgBg, imgQiblaDirTip;
    private float currentDegree = 0f;
    private static float prevDegree = -999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla);
        imgBg = (ImageView) findViewById(R.id.imgBg);
        imgQiblaDirTip = (ImageView) findViewById(R.id.compassDi);
        imgQiblaDirTip.setVisibility(View.VISIBLE);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        home=(Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MapsActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        twiter=(ImageButton)findViewById(R.id.twiterLink);
        twiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://twitter.com/iccukorg");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        facebook=(ImageButton)findViewById(R.id.facebookLink);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.facebook.com/iccuk.org");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });
        iccuk=(TextView)findViewById(R.id.iccuk);
        iccuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.iccuk.org/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();



        boolean isAvailable = Utiities.checkCompatibility(this);
        if (!isAvailable) {
            setDefaultImage();
            showErrorDialog();
        } else {
            mSensorManager.registerListener(this,
                    mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                    SensorManager.SENSOR_DELAY_GAME);
            loc = new LocationValues(this);
            userLocation = loc.calcCurrentLocation();
            if (userLocation != null) {

                degreeDiff = Utiities.findQiblaAngle(loc);
            } else
            {
                degreeDiff=0.0f;
                Toast.makeText(
                        this,
                        "Last location is not known. Run Google maps application to get current location",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showErrorDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Compass Sensor not available on this device").setCancelable(false);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.cancel();
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void setDefaultImage() {
        imgQiblaDirTip.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        // create a rotation animation (reverse turn degree degrees)

        if (prevDegree == -999 || Math.abs(prevDegree - degree) > 1) {
            RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setDuration(210);
            ra.setFillAfter(true);
            imgBg.startAnimation(ra);
            prevDegree = degree;
        }
        if (prevDegree == -999 || Math.abs(currentDegree - degree) > 1) {
            RotateAnimation rb = new RotateAnimation(
                    currentDegree + degreeDiff, -degree+degreeDiff,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rb.setDuration(210);
            rb.setFillAfter(true);
            imgQiblaDirTip.startAnimation(rb);
            currentDegree = -degree;
        }

    }

}