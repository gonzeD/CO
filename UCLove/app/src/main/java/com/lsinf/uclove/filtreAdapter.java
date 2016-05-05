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
 */
public class filtreAdapter extends RecyclerView.Adapter<filtreAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    Activity a;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView text;
        public View root;
        public View button;
        public View button1;
        public ViewHolder(ViewGroup v) {
            super(v);
            text = (TextView)v.findViewById(R.id.text);
            button = v.findViewById(R.id.modify);
            button1 = v.findViewById(R.id.rdv);
            root = v.findViewById(R.id.root);
        }
    }

    public filtreAdapter(ArrayList<String> myDataset,Activity ac) {
        mDataset = myDataset;a = ac;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public filtreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        ViewGroup v =(ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.filtre_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.text.setText(mDataset.get(position));
        holder.root.setTag(position);
        //holder.button1.setTag(position);
        holder.button.setTag(position);
        /*holder.root.setTag(position);
        holder.root.findViewById(R.id.rdv).setTag(position);*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}