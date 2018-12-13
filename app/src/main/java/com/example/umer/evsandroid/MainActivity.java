package com.example.umer.evsandroid;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
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

    ArrayList<Contact> myArrayListOfContacts;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=findViewById(R.id.mybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MyDbContcats.class);
                startActivity(i);
            }
        });
        db=new DatabaseHelper(getBaseContext());
        RecyclerView myrcv=findViewById(R.id.myrcv);
        myArrayListOfContacts=fillContactsList();
        ContactAdaptor myAdaptor=new ContactAdaptor(myArrayListOfContacts);
        myrcv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        myrcv.setAdapter(myAdaptor);
        myrcv.addOnItemTouchListener(new RecyclerTouchListener(this,
                myrcv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(getApplicationContext(),"Clicked "+myArrayListOfContacts.get(position).getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
                Toast.makeText(getApplicationContext(),"Long Clicked "+myArrayListOfContacts.get(position).getName(),Toast.LENGTH_LONG).show();

            }
        }));
       // myrcv.setAdapter(new ContactAdaptor(fillContactsList()));


    }


    private void showActionsDialog(final int position) {
        CharSequence options[] = new CharSequence[]{"Add to DB", "Cancle"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int optionPressed) {
                if (optionPressed == 0) {

                    Contact contact=myArrayListOfContacts.get(position);

                    db.insertContact(contact.getPhoneNo(),contact.getName());
                    Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();

                } else {

                }
            }
        });
        builder.show();
    }

    ArrayList<Contact> fillContactsList()
    {
        ArrayList<Contact> ls = new ArrayList<Contact>();
        try {


            /* Code To Query Data From Contact App of Phone*/
            ContentResolver contentResolver = this.getContentResolver();
            Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                /* Contact extraction logic goes here*/

                Contact c1 = new Contact();
                c1.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                c1.setPhoneNo(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));


                ls.add(c1);

                cursor.moveToNext();
            }
            // cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));


        }catch (Exception ex)
        {

            Log.d("evsandroid",ex.toString());
        }

        return  ls;

    }
}
