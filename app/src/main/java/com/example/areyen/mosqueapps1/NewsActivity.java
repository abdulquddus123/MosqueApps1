package com.example.areyen.mosqueapps1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.areyen.mosqueapps1.Adapter.EventAdapter;
import com.example.areyen.mosqueapps1.Adapter.NewsAdapter;
import com.example.areyen.mosqueapps1.Model.Event;
import com.example.areyen.mosqueapps1.Model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    TextView iccuk;
    Button home;
    ImageButton twiter,facebook;
    String url="http://mosque.7theoremblog.com/Api_json_format";
    String title;
    private List<News> newsList = new ArrayList<>();
    private RecyclerView myRecyclerView;
    private NewsAdapter nAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecycleView);

        nAdapter = new NewsAdapter(this,newsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(mLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(nAdapter);
        iccuk=(TextView)findViewById(R.id.iccuk);
        iccuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.iccuk.org/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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

        home=(Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        getNews();
    }

    public void getNews(){
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray=response.getJSONArray("result");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                      //  String eventDate=jsonObject.getString("eventDate");
                        String newsTitle=jsonObject.getString("title");
                        String newsImage=jsonObject.getString("post_iamge");
                        String Image="http://mosque.7theoremblog.com/assets/admin/images/"+newsImage;
                       String newsDescription=jsonObject.getString("description");
                        String newsDate=jsonObject.getString("post_created");
                      //  String eventDescription=jsonObject.getString("eventDescr");
                        //  Toast.makeText(EventActivity.this, eventTitle, Toast.LENGTH_SHORT).show();

                        News news=new News(newsTitle,Image,newsDescription,newsDate);
                        newsList.add(news);

                        nAdapter.notifyDataSetChanged();

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
                    Toast.makeText(NewsActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(MainActivity.this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

}
