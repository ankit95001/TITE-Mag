package com.example.titemag;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }
    private ViewPager2 viewPagerAbout,viewPagerAboutEditor;
    private List<RoundImageModel> imageList,imageListEditor;
    private AboutAdvisoryAdapter adapter,adapterEditor;
    private Handler slideHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_about, container, false);
        viewPagerAbout=v.findViewById(R.id.viewPagerAbout);
        viewPagerAboutEditor=v.findViewById(R.id.viewPagerAboutEditor);
        imageList=new ArrayList<>();
        imageListEditor=new ArrayList<>();
        imageList.add(new RoundImageModel(R.drawable.director));
        imageList.add(new RoundImageModel(R.drawable.pankaj));
        imageList.add(new RoundImageModel(R.drawable.pankaj));

        //Add image
        imageListEditor.add(new RoundImageModel(R.drawable.e2));
        imageListEditor.add(new RoundImageModel(R.drawable.e1));
        imageListEditor.add(new RoundImageModel(R.drawable.e4));
        imageListEditor.add(new RoundImageModel(R.drawable.e3));

        adapter=new AboutAdvisoryAdapter(imageList,viewPagerAbout);
        viewPagerAbout.setAdapter(adapter);

        adapterEditor=new AboutAdvisoryAdapter(imageListEditor,viewPagerAboutEditor);
        viewPagerAboutEditor.setAdapter(adapterEditor);

        viewPagerAbout.setOffscreenPageLimit(3);
        viewPagerAbout.setClipToPadding(false);
        viewPagerAbout.setClipChildren(false);
        viewPagerAbout.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        viewPagerAboutEditor.setOffscreenPageLimit(3);
        viewPagerAboutEditor.setClipToPadding(false);
        viewPagerAboutEditor.setClipChildren(false);
        viewPagerAboutEditor.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer=new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.14f);
            }
        });
        viewPagerAbout.setPageTransformer(transformer);
        viewPagerAbout.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000);
            }
        });
        viewPagerAboutEditor.setPageTransformer(transformer);
        viewPagerAboutEditor.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,2000);
            }
        });

        return v;
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPagerAbout.setCurrentItem(viewPagerAbout.getCurrentItem()+1);
            viewPagerAboutEditor.setCurrentItem(viewPagerAbout.getCurrentItem()+1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,3000);
    }
}