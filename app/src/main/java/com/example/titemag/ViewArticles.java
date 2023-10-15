package com.example.titemag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewArticles extends AppCompatActivity {
    RecyclerView recyclerView;
    ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_articles);

        recyclerView = findViewById(R.id.ArticleRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerOptions<ImageData> options =
                new FirebaseRecyclerOptions.Builder<ImageData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Articles"), ImageData.class)
                        .build();

        adapter = new ArticleAdapter(options);
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
