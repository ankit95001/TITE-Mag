package com.example.titemag;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ViewContributionFragment extends Fragment {
    View v;
    Button view_painting,view_sketches,view_article,view_poem;


    public ViewContributionFragment() {
        // Required empty public constructor
    }
    ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> Log.d("Ankit","onActivityResult")
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_view_contribution, container, false);
        view_painting=v.findViewById(R.id.view_painting);
        view_painting.setOnClickListener(view -> {
            Intent ipainting = new Intent(v.getContext(), ViewImages.class);
            activityResultLauncher.launch(ipainting);
        });
        view_sketches=v.findViewById(R.id.view_sketches);
        view_sketches.setOnClickListener(view -> {
            Intent isketches = new Intent(v.getContext(), ViewSketches.class);
            activityResultLauncher.launch(isketches);
        });
        view_article=v.findViewById(R.id.view_articles);
        view_article.setOnClickListener(view -> {
            Intent iarticle = new Intent(v.getContext(), ViewArticles.class);
            activityResultLauncher.launch(iarticle);
        });
        view_poem=v.findViewById(R.id.view_poem);
        view_poem.setOnClickListener(view -> {
            Intent iPoem = new Intent(v.getContext(), ViewPoetry.class);
            activityResultLauncher.launch(iPoem);
        });


        return v;
    }
}