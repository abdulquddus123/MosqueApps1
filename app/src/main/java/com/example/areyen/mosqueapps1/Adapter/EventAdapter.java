package com.example.areyen.mosqueapps1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.areyen.mosqueapps1.ContactActivity;
import com.example.areyen.mosqueapps1.EventActivity;
import com.example.areyen.mosqueapps1.ItemClickListener;
import com.example.areyen.mosqueapps1.Model.Event;
import com.example.areyen.mosqueapps1.R;
import com.example.areyen.mosqueapps1.SinglePageEvent;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Android Dev on 4/15/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private List<Event> eventList;
    ItemClickListener itemClickListener;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_model, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       final Event event = eventList.get(position);
        holder.date.setText(event.getDate());
        holder.title.setText(event.getTitle());



    }
    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public MyViewHolder(View view) {
            super(view);
            date = (TextView)view.findViewById(R.id.date);
            title = (TextView) view.findViewById(R.id.title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(v.getContext(),SinglePageEvent.class);
                    i.putExtra("Date",eventList.get(getAdapterPosition()).getDate());
                    i.putExtra("Title",eventList.get(getAdapterPosition()).getTitle());
                   i.putExtra("Description",eventList.get(getAdapterPosition()).getDescription());
                    v.getContext().startActivity(i);

                }
            });
                   }


    }

}
