package com.example.umer.evsandroid;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
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
        /* Code To Query Data From Contact App of Phone*/
        ContentResolver contentResolver=this.getContentResolver();
        Cursor   cursor=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            /* Contact extraction logic goes here*/

            Contact c1=new Contact();
            c1.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            c1.setPhoneNo(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));


            ls.add(c1);

            cursor.moveToNext();
        }
       // cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));





        return  ls;

    }
}
