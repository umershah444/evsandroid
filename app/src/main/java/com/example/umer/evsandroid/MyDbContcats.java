package com.example.umer.evsandroid;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MyDbContcats extends Activity {

    ArrayList<Contact> myArrayListOfContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_db_contcats);

        RecyclerView myrcv=findViewById(R.id.mydbcontactsrcv);
        DatabaseHelper db=new DatabaseHelper(this);
        myArrayListOfContacts=db.getAllContacts();
        ContactAdaptor myAdaptor=new ContactAdaptor(myArrayListOfContacts);
        myrcv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        myrcv.setAdapter(myAdaptor);
    }

}
