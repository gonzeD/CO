package com.lsinf.uclove;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
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
 * Affiche une demande d'ami de l'utilisateur
 */
public class requestAdapter extends RecyclerView.Adapter<requestAdapter.ViewHolder> {
    private ArrayList<String> name;
    private ArrayList<Integer> id;
    Activity a;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text;
        public View block;
        public View accept;
        public ViewHolder(ViewGroup v) {
            super(v);
            text = (TextView)v.findViewById(R.id.name);
            block = (TextView)v.findViewById(R.id.block);
            accept = (TextView)v.findViewById(R.id.accept);
        }
    }

    public requestAdapter(ArrayList<String> mname, ArrayList<Integer> mid, Activity ac) {
        name = mname;
        id = mid;
        a = ac;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public requestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        ViewGroup v =(ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.request_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(name.get(position));
        holder.block.setTag(position);
        holder.accept.setTag(position);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       /* Resources r = a.getResources();
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
        return name.size();
    }
}