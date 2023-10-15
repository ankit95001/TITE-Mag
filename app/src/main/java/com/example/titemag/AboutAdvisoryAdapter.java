package com.example.titemag;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class AboutAdvisoryAdapter extends RecyclerView.Adapter<AboutAdvisoryAdapter.AboutImageViewHolder>{
    private List<RoundImageModel> imageList;
    private ViewPager2 viewPagerAbout;

    public AboutAdvisoryAdapter(List<RoundImageModel> imageList, ViewPager2 viewPagerAbout) {
        this.imageList = imageList;
        this.viewPagerAbout = viewPagerAbout;
    }

    @NonNull
    @Override
    public AboutImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_image_container,parent,false);
        return new AboutImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutImageViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position).getImage());
        if(position==imageList.size()-2){
            viewPagerAbout.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class AboutImageViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView imageView;
        public AboutImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.roundedImage);
        }
    }
    private Runnable runnable = new Runnable() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            imageList.addAll(imageList);
            notifyDataSetChanged();
        }
    };
}
