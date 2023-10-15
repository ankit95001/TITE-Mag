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

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    private ViewPager2 viewPager2;
    private List<RoundImageModel> imageList;
    private HomeImageAdapter adapter;
    private Handler slideHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2=v.findViewById(R.id.viewPager2);
        imageList=new ArrayList<>();

        imageList.add(new RoundImageModel(R.drawable.bfhome));
        imageList.add(new RoundImageModel(R.drawable.gp));
        imageList.add(new RoundImageModel(R.drawable.bfhome));
        imageList.add(new RoundImageModel(R.drawable.gp));
        imageList.add(new RoundImageModel(R.drawable.bfhome));
        imageList.add(new RoundImageModel(R.drawable.gp));

        adapter=new HomeImageAdapter(imageList,viewPager2);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer=new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.14f);
            }
        });
        viewPager2.setPageTransformer(transformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
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
        slideHandler.postDelayed(sliderRunnable,2000);
    }
}