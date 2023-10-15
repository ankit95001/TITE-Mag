package com.example.titemag;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PaintingAdapter extends FirebaseRecyclerAdapter<ImageData,PaintingAdapter.paintingViewHolder> {

    public PaintingAdapter(@NonNull FirebaseRecyclerOptions<ImageData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull paintingViewHolder holder, int position, @NonNull ImageData model) {
        holder.enrollment.setText(model.getEnrollment());
        holder.student_name.setText(model.getStudent_name());
        holder.theme.setText(model.getName());
        Glide.with(holder.painting.getContext()).load(model.getImage()).into(holder.painting);
        setAnimation(holder.itemView);

    }

    @NonNull
    @Override
    public paintingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.painting_view,parent,false);
        return new paintingViewHolder(view);
    }

    public static class paintingViewHolder extends RecyclerView.ViewHolder{
        TextView theme,student_name,enrollment;
        ImageView painting;

        public paintingViewHolder(@NonNull View itemView) {
            super(itemView);
            painting=itemView.findViewById(R.id.view_painting_image_image);
            theme=itemView.findViewById(R.id.view_painting_image_name);
            student_name=itemView.findViewById(R.id.view_painting_image_student_name);
            enrollment=itemView.findViewById(R.id.view_painting_image_student_enrollment);
        }
    }
    private void setAnimation(View VtA) {
        Animation slidin = AnimationUtils.loadAnimation(VtA.getContext(), android.R.anim.slide_in_left);
        VtA.startAnimation(slidin);
    }

}
