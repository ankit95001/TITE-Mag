package com.example.titemag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewSketches extends AppCompatActivity {
    RecyclerView recyclerView;

    PaintingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sketches);

        recyclerView=findViewById(R.id.sketchesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerOptions<ImageData> options =
                new FirebaseRecyclerOptions.Builder<ImageData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Sketches"), ImageData.class)
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