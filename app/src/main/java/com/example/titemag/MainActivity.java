package com.example.titemag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    FragmentManager fm =getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null) {
            loadFragment(new HomeFragment(),0);
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==R.id.nav_pre) {
           loadFragment(new PreviousYearFragment(),1);
        }
       else if(item.getItemId()==R.id.nav_add_contributions){
            loadFragment(new AddContributionFragment(),1);}
       else if(item.getItemId()==R.id.nav_view_contributions){
            loadFragment(new ViewContributionFragment(),1);
            }
       else if(item.getItemId()==R.id.nav_setting){
            loadFragment(new SettingFragment(),1);}
       else if(item.getItemId()==R.id.nav_about) {
            loadFragment(new AboutFragment(),1);}
       else if (item.getItemId() == R.id.nav_logout) {
            loadFragment(new LogoutFragment(),1);
            }
       else{
           loadFragment(new HomeFragment(),0);
       }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("CommitTransaction")
    public void loadFragment(Fragment fragment, int flag){

        FragmentTransaction ft=fm.beginTransaction();
        if(flag==0){
            ft.add(R.id.frame_container,fragment);
            fm.popBackStack("root_fragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else{
            ft.replace(R.id.frame_container,fragment);
            ft.addToBackStack(null);
        }

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            MainActivity.super.onBackPressed();
        }

    }

}