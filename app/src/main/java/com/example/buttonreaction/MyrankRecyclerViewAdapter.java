package com.example.buttonreaction;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buttonreaction.databinding.ObjectRankBinding;
import com.example.buttonreaction.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.buttonreaction.databinding.FragmentRankBinding;

import java.util.List;

public class MyrankRecyclerViewAdapter extends RecyclerView.Adapter<MyrankRecyclerViewAdapter.ViewHolder> {

    private final List<Record> rankList;
    private MainViewModel mainViewModel;

    public MyrankRecyclerViewAdapter(List<Record> items, MainViewModel mvm)
    {
        rankList = items;
        mainViewModel = mvm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ViewHolder(ObjectRankBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.name.setText(rankList.get(position).getUserId());
        holder.content.setText(""+rankList.get(position).getRecord());
    }

    @Override
    public int getItemCount()
    {
        return rankList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView name;
        public final TextView content;

        public ViewHolder(ObjectRankBinding binding)
        {
            super(binding.getRoot());
            name = binding.rankTvUserId;
            content = binding.rankTvRecode;
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + content.getText() + "'";
        }
    }
}