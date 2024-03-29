package com.example.titemag;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;

public class LogoutFragment extends Fragment {


    FirebaseAuth mAuth;
    Button btn_logout;
    FirebaseUser user;
    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_logout, container, false);
        mAuth=FirebaseAuth.getInstance();
        btn_logout=v.findViewById(R.id.fragment_logout);
        user=mAuth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        }
       btn_logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FirebaseAuth.getInstance().signOut();
               Intent intent=new Intent(getContext(), LoginActivity.class);
               startActivity(intent);
               requireActivity().finish();
           }
       });


        return v;
    }
}