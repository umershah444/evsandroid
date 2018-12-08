package com.example.umer.evsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView myrcv=findViewById(R.id.myrcv);
        ArrayList<Contact> myArrayListOfContacts=fillContactsList();
        ContactAdaptor myAdaptor=new ContactAdaptor(myArrayListOfContacts);
        myrcv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        myrcv.setAdapter(myAdaptor);
       // myrcv.setAdapter(new ContactAdaptor(fillContactsList()));


    }


    ArrayList<Contact> fillContactsList()
    {
        ArrayList<Contact> ls=new ArrayList<Contact>();
        Contact c1=new Contact();
        c1.setName("Shahmeer");
        c1.setName("03001123456");

        ls.add(c1);

        Contact c2=new Contact();
        c1.setName("ABC");
        c1.setName("03001124564");

        ls.add(c2);


        return  ls;

    }
}
