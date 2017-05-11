package com.example.areyen.mosqueapps1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.CompoundButton;
import android.hardware.SensorManager;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.areyen.mosqueapps1.Adapter.NewsAdapter;
import com.example.areyen.mosqueapps1.Adapter.NewsAdapter1;
import com.example.areyen.mosqueapps1.Alarm.AlarmReceiver;
import com.example.areyen.mosqueapps1.Model.News;
import com.example.areyen.mosqueapps1.Model.News1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    ImageButton about_us, contact, department, donate, facebook, news, twiter, wellcome;
    Button home;
    Switch sButton;
    Button event;
    static String fozarTime, sunriseTime, zohorTime, asosTime, magribTime, esaTime;
    TextView textView, title, description, date, timeTv;
    String url = "http://mosque.7theoremblog.com/Api_json_format/rest_csv";
    String url1="http://mosque.7theoremblog.com/Api_json_format";
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    private ImageView image;
    private List<News1> newsList1 = new ArrayList<>();
    private RecyclerView myRecyclerView;
    private NewsAdapter1 nAdapter1;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecycleView);

        nAdapter1 = new NewsAdapter1(this,newsList1);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(nAdapter1);


        wellcome = (ImageButton) findViewById(R.id.wellcome);
        about_us = (ImageButton) findViewById(R.id.about_us);
        donate = (ImageButton) findViewById(R.id.donate);
        contact = (ImageButton) findViewById(R.id.contact);
        timeTv = (TextView) findViewById(R.id.timeTv);
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

        image = (ImageView) findViewById(R.id.imageViewCompass);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "There is no Compass Sensor in this device", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, QiblaActivity.class);
                startActivity(intent);
            }
        });


        getPrayerTime();
        getMainNews();



//        sButton = (Switch)findViewById(R.id.simpleSwitch);
//        sButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sButton.setChecked(true);
//                if(sButton.isChecked()){
//                    Toast.makeText(MainActivity.this, "Switch in on", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Switch in off", Toast.LENGTH_SHORT).show();
//                }
//
//                sButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        if(isChecked == true){
//                            Toast.makeText(MainActivity.this,"On",Toast.LENGTH_SHORT).show();
//                            sButton.setChecked(true);
//
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this,"Off",Toast.LENGTH_SHORT).show();
//                            sButton.setChecked(false);
//                        }
//                    }
//                });
//            }
//        });



}



    public void getMainNews(){
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray=response.getJSONArray("result");
                    for (int i=0;i<3;i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        //  String eventDate=jsonObject.getString("eventDate");
                        String newsTitle=jsonObject.getString("title");
                        String newsImage=jsonObject.getString("post_iamge");
                        String Image="http://mosque.7theoremblog.com/assets/admin/images/"+newsImage;
                        String newsDescription=jsonObject.getString("description");
                        newsDescription = newsDescription.replace("\n", "").replace("\r", "").replace("&nbsp", "");
                        String newsDate=jsonObject.getString("post_created");
                    //    Toast.makeText(MainActivity.this, newsDate, Toast.LENGTH_SHORT).show();
                        //  String eventDescription=jsonObject.getString("eventDescr");
                        //  Toast.makeText(EventActivity.this, eventTitle, Toast.LENGTH_SHORT).show();

                        News1 news1=new News1(newsTitle,Image,newsDescription,newsDate);
                        newsList1.add(news1);

                        nAdapter1.notifyDataSetChanged();

//
                        //              date.setText(date.getText().toString()+"\n"+eventDate);
                        //                   title.setText(title.getText().toString()+"\n"+eventTitle);


                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError){
                    Toast.makeText(MainActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    //   Prayer time from API
    public void getPrayerTime() {
        setAlarm(11,20,4);
    //    setAlarm(10,40,5);
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("res");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    fozarTime = jsonObject.getString("fazar");
                    sunriseTime = jsonObject.getString("sunrise");
                    zohorTime = jsonObject.getString("zohor");
                    asosTime = jsonObject.getString("asar");
                    magribTime = jsonObject.getString("magrib");
                    esaTime = jsonObject.getString("esa");
//                    setAlarm(1,16,1);
//                    setAlarm(1,20,2);
//                    setAlarm(1,25,3);


                    int len=zohorTime.length();
                  //    Log.d("ourTime", "time" + len + "min" + len);
//                    int fozorHour = Integer.valueOf(fozarTime.substring(0, 2));
//                    int fozorMin = Integer.valueOf(fozarTime.substring(3, 5));
//                    setAlarm(fozorHour,fozorMin,1);
                   // Log.d("Alarm ","fozor Alarm " +fozorHour+ " "+fozorMin);
                    int fozorlen=fozarTime.length();
                    int fozorHour;
                    int fozorMin;
                    if(fozorlen==8){
                        fozorHour = Integer.valueOf(fozarTime.substring(0,2));
                        fozorMin = Integer.valueOf(fozarTime.substring(3,5));
                   //     setAlarm(fozorHour,fozorMin,1);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        fozorHour = Integer.valueOf(fozarTime.substring(0,1));
                        fozorMin = Integer.valueOf(fozarTime.substring(2,4));
                     //   zohorHour+=12;
                   //     setAlarm(fozorHour,fozorMin,1);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }
                    int zohorHour;
                    int zohorMin;
                    if(len==8){
                         zohorHour = Integer.valueOf(zohorTime.substring(0,2));
                         zohorMin = Integer.valueOf(zohorTime.substring(3,5));
                //       setAlarm(zohorHour,zohorMin,2);
                  //  setAlarm(16,55,2);
                      //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                         zohorHour = Integer.valueOf(zohorTime.substring(0,1));
                         zohorMin = Integer.valueOf(zohorTime.substring(2,4));
                        zohorHour+=12;
               //         setAlarm(zohorHour,zohorMin,2);
                //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }

//                    int asorHour = Integer.valueOf(asosTime.substring(0, 1));
//                    int asorMin = Integer.valueOf(asosTime.substring(2, 4));
//                    asorHour+=12;
//                    setAlarm(asorHour, asorMin, 3);
                    int asorlen=asosTime.length();
                    int asorHour;
                    int asorMin;
                    if(asorlen==8){
                        asorHour = Integer.valueOf(asosTime.substring(0,2));
                        asorMin = Integer.valueOf(asosTime.substring(3,5));
                //        setAlarm(asorHour,asorMin,3);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        asorHour = Integer.valueOf(asosTime.substring(0,1));
                        asorMin = Integer.valueOf(asosTime.substring(2,4));
                        //   zohorHour+=12;
               //         setAlarm(asorHour,asorMin,3);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }


                    int magribHour = Integer.valueOf(magribTime.substring(0,1));
                    int magribMin = Integer.valueOf(magribTime.substring(2, 4));
                    magribHour+=12;
              //      setAlarm(magribHour, magribMin, 4);



                //    int eshaHour = Integer.valueOf(esaTime.substring(0, 1));
             //       int eshaMin = Integer.valueOf(esaTime.substring(2, 4));
           //         eshaHour+=12;

                    int esalen=esaTime.length();
                    int eshaHour;
                    int eshaMin;
                    if(esalen==8){
                        eshaHour = Integer.valueOf(esaTime.substring(0,2));
                        eshaMin = Integer.valueOf(esaTime.substring(3,5));
               //         setAlarm(eshaHour,eshaMin,5);

                        //  Log.d("Zohor","Time"+ zohorHour);
                    }
                    else{
                        eshaHour = Integer.valueOf(esaTime.substring(0,1));
                        eshaMin = Integer.valueOf(esaTime.substring(2,4));
                           eshaHour+=12;
              //          setAlarm(eshaHour,eshaMin,3);
                        //        Log.d("time","Zohor Alarm "+zohorHour+" "+zohorMin);

                    }


                  //  Log.d("ourTime", "time" + asorHour + "min" + asorMin);
                    int currentOur = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                   // int currentAmPm=Calendar.getInstance().get(Calendar.AM_PM);
                //    Log.d("Current Our","Time "+ currentOur);

                    if ( currentOur <= fozorHour ) {
                        timeTv.setText("Fajr Prayer Time : "+fozarTime);
                    //    Log.d("Time", "Fozor");
                    }

                    else if (currentOur > fozorHour && currentOur <= zohorHour) {


                   //     Calendar calendar = Calendar.getInstance();
                     //   int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        Calendar calendar = Calendar.getInstance();
                   //     calendar.add(Calendar.DAY_OF_YEAR, noOfDaysFromToday);
                        String name = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
                //        System.out.println("Day Of Week:- " + name);
                   String day="Friday";
                        Log.d("date","Today is" + name);
                        if(name.equals(day)) {
                            timeTv.setText("Zuhr Prayer Time : " + " ");
                        }
                        else {
                            timeTv.setText("Zuhr Prayer Time : "+zohorTime);
                        }



                      //  Log.d("Time", "joghor");
                    }

                    else if (currentOur > zohorHour && currentOur <= asorHour) {
                        timeTv.setText("Asr Prayer Time : "+asosTime);
                       // Log.d("Time", "asor");
                    } else if (currentOur > asorHour && currentOur <= magribHour) {
                        timeTv.setText(" Maghrib Prayer Time : "+magribTime);
                    //    Log.d("Time", "magrib");
                    } else if (currentOur > magribHour && currentOur <= eshaHour) {
                        timeTv.setText("Isha Prayer Time : "+esaTime);
                    //    Log.d("Time", "esha");
                    } else {
                    //    timeTv.setText("Fazar Prayer Time :" +fozarTime);
                    }

//
//                    int hour = Integer.valueOf(fozarTime.substring(0, 1));
//                    int min = Integer.valueOf(fozarTime.substring(2, 4));
//                    // String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 1);
//              //      Log.d("Time", "hour: " + hour + " min: " + min);
//
//
//                    hour = Integer.valueOf(zohorTime.substring(0,1));
//                    min = Integer.valueOf(zohorTime.substring(2, 4));
//                    //   String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 2);
//
//                    hour = Integer.valueOf(asosTime.substring(0, 1));
//                    min = Integer.valueOf(asosTime.substring(2, 4));
//                    //  amPm = asosTime.substring(6, 8);
//                    setAlarm(hour, min, 3);
//
//                    hour = Integer.valueOf(magribTime.substring(0, 1));
//                    min = Integer.valueOf(magribTime.substring(2, 4));
//                    //   String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 4);
//
//                    hour = Integer.valueOf(esaTime.substring(0, 1));
//                    min = Integer.valueOf(esaTime.substring(2, 4));
//                    //   String amPm = time.substring(5, 7);
//                    setAlarm(hour, min, 5);

                    // Log.d("Time", "hour: " + hour + " min: " + min );
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    Toast.makeText(MainActivity.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(request);
        //   getTime();
    }


    //   Ringtone Alam Manager Method
    public void setAlarm(int hour, int min, int requestCode) {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        // calendar.set(Calendar.AM_PM, ampm);


   int rowId=requestCode;
        if (Calendar.getInstance().after(calendar)) {
            return;
        }

        Log.d("Alarm Time:", "Cal hour: " + calendar.get(Calendar.HOUR_OF_DAY));
        Log.d("AlarmTime:", "Cal min: " + calendar.get(Calendar.MINUTE));


        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("row_id", rowId);
        final int alarmId = (int) System.currentTimeMillis();
        PendingIntent sender = PendingIntent.getBroadcast(this, 4, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

    }

    public void about(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void wellcome(View view) {
        Intent intent = new Intent(this, WellcomeActivity.class);
        startActivity(intent);
    }

    public void contact(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
        //  Toast.makeText(this, "testing", Toast.LENGTH_SHORT).show();
    }

    public void donate(View view) {
        Intent intent = new Intent(this, DonationActivity.class);
        startActivity(intent);
    }

    public void donateLink(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/page3.php?section=donate&page=donation"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void twiterLink(View view) {

        Uri uri = Uri.parse("https://twitter.com/iccukorg");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void facebookLink(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/iccuk.org");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void eventLink(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void departmentLink(View view) {
        Intent intent = new Intent(this, DepartmentActivity.class);
        startActivity(intent);
    }

    public void newsLink(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void LinkOrg(View view) {
        Uri uri = Uri.parse("http://www.iccuk.org/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
    AlarmActivity aa=new AlarmActivity();




}
