package com.example.titemag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;


public class AddContributionFragment extends Fragment {
    View v;
    Button upload_painting,upload_sketches,upload_article,upload_poem;


    public AddContributionFragment() {
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
        v =inflater.inflate(R.layout.fragment_add_contribution, container, false);
        upload_painting=v.findViewById(R.id.upload_painting);
        upload_painting.setOnClickListener(view -> {
            Intent ipainting = new Intent(v.getContext(), UploadImage.class);
            activityResultLauncher.launch(ipainting);
        });
        upload_sketches=v.findViewById(R.id.upload_sketches);
        upload_sketches.setOnClickListener(view -> {
            Intent iSketch = new Intent(v.getContext(), UplaodSketches.class);
            activityResultLauncher.launch(iSketch);
        });
        upload_article=v.findViewById(R.id.upload_articles);
        upload_article.setOnClickListener(view -> {
            Intent iArticles=new Intent(v.getContext(),Upload_Articles.class);
            activityResultLauncher.launch(iArticles);
        });
        upload_poem=v.findViewById(R.id.upload_poem);
        upload_poem.setOnClickListener(view -> {
            Intent ipoem=new Intent(v.getContext(),UploadPoems.class);
            activityResultLauncher.launch(ipoem);
        });




        return v;
    }
}