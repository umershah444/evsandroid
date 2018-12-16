package com.example.umer.evsandroid;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    TabLayout tabLayout;
    MyViewPagerAdaptor viewPagerAdaptor;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=findViewById(R.id.mytablayout);
        viewPager=findViewById(R.id.myviewpager);
        viewPagerAdaptor=new MyViewPagerAdaptor(getSupportFragmentManager(),new ArrayList<Fragment>(),new ArrayList<String>());

        Fragment f1=new ContactsFragment();
        Fragment f2=new MyDbContactsFragment();

        String title1="Contacts";
        String title2="My Contacts";

        viewPagerAdaptor.AddFragment(title1,f1);
        viewPagerAdaptor.AddFragment(title2,f2);

        viewPager.setAdapter(viewPagerAdaptor);
        tabLayout.setupWithViewPager(viewPager);




    }


}
