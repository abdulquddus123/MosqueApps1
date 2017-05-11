package com.example.areyen.mosqueapps1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.areyen.mosqueapps1.AppController;
import com.example.areyen.mosqueapps1.Model.Event;
import com.example.areyen.mosqueapps1.Model.News;
import com.example.areyen.mosqueapps1.R;
import com.example.areyen.mosqueapps1.SinglePageEvent;
import com.example.areyen.mosqueapps1.SinglePageNews;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Android Dev on 4/25/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<News> newsList;
   Context context;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_model, parent, false);


        return new MyViewHolder(itemView);

    }
    public NewsAdapter(Context context,List<News> newsList) {
        this.context=context;
        this.newsList = newsList;

    }
    public  NewsAdapter(Context context){
        this.context=context;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final News news = newsList.get(position);
        holder.title.setText(news.getNewsTitle());
      //  Picasso.with(context).load(news.getNewsImage()).resize(120, 60).into(holder.imageView);
        Picasso.with(context).load(news.getNewsImage()).resize(80, 80).into(holder.imageView);


      //  Log.d("News title","testing" +title);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            imageView=(ImageView)view.findViewById(R.id.imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(v.getContext(),SinglePageNews.class);
                    i.putExtra("Image",newsList.get(getAdapterPosition()).getNewsImage());
                    i.putExtra("Title",newsList.get(getAdapterPosition()).getNewsTitle());
                    i.putExtra("Description",newsList.get(getAdapterPosition()).getNewsDescription());

                    v.getContext().startActivity(i);

                }
            });
        }
    }
}
