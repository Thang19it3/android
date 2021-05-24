package com.example.googlemap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnbroadingAdapter extends RecyclerView.Adapter<OnbroadingAdapter.OnbroadingViewHolder>{

    private List<OnbroadinItem> onbroadinItems;

    public OnbroadingAdapter(List<OnbroadinItem> onbroadinItems) {
        this.onbroadinItems = onbroadinItems;
    }

    @NonNull

    @Override
    public OnbroadingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnbroadingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onbroading, parent ,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull  OnbroadingViewHolder holder, int position) {
        holder.setOnbroadingData(onbroadinItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onbroadinItems.size();
    }

    class OnbroadingViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textTitle;
        private TextView textSlogan;
        private ImageView imageOnbroading;

        OnbroadingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSlogan = itemView.findViewById(R.id.textSlogan);
            imageOnbroading = itemView.findViewById(R.id.imageBroading);
        }
        void setOnbroadingData(OnbroadinItem onbroadinItem)
        {
            textTitle.setText(onbroadinItem.getTitle());
            textSlogan.setText(onbroadinItem.getSlogan());
            imageOnbroading.setImageResource(onbroadinItem.getImage());
        }
    }
}