package com.example.areyen.mosqueapps1.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.areyen.mosqueapps1.R;

/**
 * Created by Android Dev on 4/26/2017.
 */

public class NewsHolder1 extends RecyclerView.ViewHolder{
    TextView titleTv;
    ImageView imageView;
    ImageView imageViewTv;

    public NewsHolder1(View itemView){
        super(itemView);

        titleTv=(TextView)itemView.findViewById(R.id.title);
        imageView=(ImageView)itemView.findViewById(R.id.imageView);

    }
}
