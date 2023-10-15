package com.example.titemag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewPoetry extends AppCompatActivity {
    RecyclerView recyclerView;
    PoemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_poetry);

        recyclerView = findViewById(R.id.PoetryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerOptions<ImageData> options =
                new FirebaseRecyclerOptions.Builder<ImageData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Poetry"), ImageData.class)
                        .build();

        adapter = new PoemAdapter(options);
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