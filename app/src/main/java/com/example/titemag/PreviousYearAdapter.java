package com.example.titemag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PreviousYearAdapter extends FirebaseRecyclerAdapter<PreviousData,PreviousYearAdapter.PreviousViewHolder> {

    Context context;

    public PreviousYearAdapter(@NonNull FirebaseRecyclerOptions<PreviousData> options, Context context) {
        super(options);
        this.context = context;
    }

    public PreviousYearAdapter(@NonNull FirebaseRecyclerOptions<PreviousData> options) {
        super(options);
    }

    @SuppressLint("IntentReset")
    @Override
    protected void onBindViewHolder(@NonNull PreviousViewHolder holder, int position, @NonNull PreviousData model) {

        holder.theme.setText(model.getTheme());
        holder.chef_editor.setText(model.getChief());
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);
        setAnimation(holder.itemView);
        holder.layout.setOnClickListener(view -> {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setType("application/pdf");
            intent.setData(Uri.parse(model.getPdf()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
            ((Activity)holder.layout.getContext()).finish();
        });

    }

    @NonNull
    @Override
    public PreviousViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_year_view,parent,false);
        return new PreviousViewHolder(view);
    }

    public static class PreviousViewHolder extends RecyclerView.ViewHolder{
        TextView theme,chef_editor;
        ImageView img;
        LinearLayout layout;
        public PreviousViewHolder(@NonNull View itemView) {
            super(itemView);
            theme=itemView.findViewById(R.id.view_Previous_theme_name);
            chef_editor=itemView.findViewById(R.id.view_Previous_Chef_name);
            img=itemView.findViewById(R.id.previous_year_image);
            layout=itemView.findViewById(R.id.layout_previous);
        }
    }
    private void setAnimation(View VtA) {
        Animation slidin = AnimationUtils.loadAnimation(VtA.getContext(), android.R.anim.slide_in_left);
        VtA.startAnimation(slidin);
    }
}
