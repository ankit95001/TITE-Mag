package com.example.titemag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ArticleAdapter extends FirebaseRecyclerAdapter<ImageData,ArticleAdapter.ArticleViewHolder> {

    public ArticleAdapter(@NonNull FirebaseRecyclerOptions<ImageData> options) {
        super(options);
    }

    @SuppressLint("IntentReset")
    @Override
    protected void onBindViewHolder(@NonNull ArticleViewHolder holder, int position, @NonNull ImageData model) {
        holder.enrollment.setText(model.getEnrollment());
        holder.student_name.setText(model.getStudent_name());
        holder.theme.setText(model.getName());

            holder.layout.setOnClickListener(view -> {
                Intent intent=new Intent(holder.layout.getContext(), DocumentViewer.class);
                intent.putExtra("filename",model.getName());
                intent.putExtra("fileUrl",model.getImage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
                ((Activity)view.getContext()).finish();


            });

        setAnimation(holder.itemView);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view,parent,false);
        return new ArticleViewHolder(view);
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView theme,student_name,enrollment;
        LinearLayout layout;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            theme=itemView.findViewById(R.id.view_article_name);
            student_name=itemView.findViewById(R.id.view_article_student_name);
            enrollment=itemView.findViewById(R.id.view_article_student_enrollment);
            layout=itemView.findViewById(R.id.layout_article);
        }
    }
    private void setAnimation(View VtA) {
        Animation slidin = AnimationUtils.loadAnimation(VtA.getContext(), android.R.anim.slide_in_left);
        VtA.startAnimation(slidin);
    }
}
