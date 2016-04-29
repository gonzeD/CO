package com.lsinf.uclove;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by damien on 29/04/16.
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private ArrayList<Friend> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView photo;
        public TextView name;
        public ViewGroup root;
        public ViewHolder(ViewGroup v) {
            super(v);
            name = (TextView)v.findViewById(R.id.name);
            root = v;
        }
    }

    public FriendAdapter(ArrayList<Friend> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        ViewGroup v =(ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset.get(position).name);
        holder.root.setTag(position);
        holder.root.findViewById(R.id.rdv).setTag(position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}