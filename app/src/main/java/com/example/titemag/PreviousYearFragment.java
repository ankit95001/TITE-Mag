package com.example.titemag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class PreviousYearFragment extends Fragment {

    public PreviousYearFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    PreviousYearAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_privious_year, container, false);
        recyclerView=v.findViewById(R.id.Previous_Year_Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<PreviousData> options =
                new FirebaseRecyclerOptions.Builder<PreviousData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Year"), PreviousData.class)
                        .build();

        adapter=new PreviousYearAdapter(options);
        recyclerView.setAdapter(adapter);
        return v;
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