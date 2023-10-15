package com.example.titemag;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewImages extends AppCompatActivity {

    RecyclerView recyclerView;

    PaintingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_images);
        recyclerView=findViewById(R.id.paintingRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerOptions<ImageData> options =
                new FirebaseRecyclerOptions.Builder<ImageData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Paintings"), ImageData.class)
                        .build();

        adapter= new PaintingAdapter(options);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}