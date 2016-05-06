package com.lsinf.uclove;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by damien on 29/04/16.
 * Affiche un message spécifique
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Message> mDataset;
    Activity a;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text;
        public LinearLayout ll;
        public ViewHolder(ViewGroup v) {
            super(v);
            text = (TextView)v.findViewById(R.id.text);
            ll = (LinearLayout) v.findViewById(R.id.layout);
        }
    }

    public MessageAdapter(ArrayList<Message> myDataset,Activity ac) {
        mDataset = myDataset;a = ac;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        ViewGroup v =(ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.message_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Resources r = a.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
        float ratioDp = px/100;
        holder.text.setText(mDataset.get(position).text);
        if(mDataset.get(position).sender)
        {
            holder.text.setBackground(ContextCompat.getDrawable(a, R.drawable.getter));
            holder.ll.setPadding((int)ratioDp*100,(int)ratioDp*10,(int)ratioDp*10,(int)ratioDp*10);
        }
        else
        {
            holder.text.setBackground(ContextCompat.getDrawable(a, R.drawable.sender));
            holder.ll.setPadding((int)ratioDp*10,(int)ratioDp*10,(int)ratioDp*100,(int)ratioDp*10);
        }
        /*holder.root.setTag(position);
        holder.root.findViewById(R.id.rdv).setTag(position);*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}