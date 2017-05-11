package com.example.areyen.mosqueapps1.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.areyen.mosqueapps1.R;

/**
 * Created by Android Dev on 4/25/2017.
 */

public class NewsHoder extends RecyclerView.ViewHolder {
    TextView titleTv;
    NetworkImageView imageView;
    ImageView imageViewTv;
    public NewsHoder(View itemView) {
        super(itemView);

        titleTv=(TextView)itemView.findViewById(R.id.title);
        imageView=(NetworkImageView)itemView.findViewById(R.id.imageView);
      //  imageViewTv=(ImageView) itemView.findViewById(R.id.imageView);
    }
}
