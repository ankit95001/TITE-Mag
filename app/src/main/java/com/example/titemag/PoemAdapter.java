package com.example.titemag;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PoemAdapter extends FirebaseRecyclerAdapter<ImageData,PoemAdapter.PoemViewHolder> {

    public PoemAdapter(@NonNull FirebaseRecyclerOptions<ImageData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PoemViewHolder holder, int position, @NonNull ImageData model) {
        holder.enrollment.setText(model.getEnrollment());
        holder.student_name.setText(model.getStudent_name());
        holder.theme.setText(model.getName());

        holder.layout.setOnClickListener(view -> {
            Intent intent=new Intent(holder.layout.getContext(), PoemDocumentViewer.class);
            intent.putExtra("filename",model.getName());
            intent.putExtra("fileUrl",model.getImage());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.layout.getContext().startActivity(intent);

        });

    }

    @NonNull
    @Override
    public PoemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_view,parent,false);
        return new PoemViewHolder(view);
    }

    public static class PoemViewHolder extends RecyclerView.ViewHolder{
        TextView theme,student_name,enrollment;
        LinearLayout layout;

        public PoemViewHolder(@NonNull View itemView) {
            super(itemView);
            theme=itemView.findViewById(R.id.view_article_name);
            student_name=itemView.findViewById(R.id.view_article_student_name);
            enrollment=itemView.findViewById(R.id.view_article_student_enrollment);
            layout=itemView.findViewById(R.id.layout_article);
        }
    }
}
