package com.example.umer.evsandroid;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ContactsFragment extends Fragment{

    ArrayList<Contact> myArrayListOfContacts;
    DatabaseHelper db;
    RecyclerView myrcv;
    ContactAdaptor myAdaptor;
    private int temp=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.contacts_fragment,container,false);



        db=new DatabaseHelper(getContext());
        myrcv=v.findViewById(R.id.myrcv);


        /*Button btn=v.findViewById(R.id.logoutbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        myArrayListOfContacts=db.getAllContacts();//fillContactsList();
         myAdaptor=new ContactAdaptor(myArrayListOfContacts);
        myrcv.setLayoutManager(new LinearLayoutManager(getContext()));
        myrcv.setAdapter(myAdaptor);
        myrcv.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                myrcv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(getContext(),"Clicked "+myArrayListOfContacts.get(position).getName(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
                //Toast.makeText(getContext(),"Long Clicked "+myArrayListOfContacts.get(position).getName(),Toast.LENGTH_LONG).show();

            }
        }));
        // myrcv.setAdapter(new ContactAdaptor(fillContactsList()));
/*
db.getAllContacts()
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_CONTACTS},
                1);*/


        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.



                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "Permission denied to read your Contacts", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    private void showActionsDialog(final int position) {
        CharSequence options[] = new CharSequence[]{"Add to DB","Add Image","Call","Capture From Camera", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int optionPressed) {
                if (optionPressed == 0) {

                    Contact contact=myArrayListOfContacts.get(position);

                    db.insertContact(contact.getPhoneNo(),contact.getName());
                    Toast.makeText(getContext(),"Inserted",Toast.LENGTH_LONG).show();

                } else if (optionPressed == 1){

                    Intent intent=new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    temp=position;
                    startActivityForResult(intent,1);
                }else if (optionPressed == 2)
                {
                    Intent i=new Intent();
                    i.setAction(Intent.ACTION_DIAL);

                    Contact contact=myArrayListOfContacts.get(position);
                    i.setData(Uri.parse("tel:"+contact.getPhoneNo()));
                    startActivity(i);
                }else if(optionPressed==3)
                {

                    Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(i,2);
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==1&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            int position=temp;//data.getIntExtra("position",position);
            myArrayListOfContacts.get(position).setImageUrl(data.getData());

            myAdaptor.notifyDataSetChanged();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    ArrayList<Contact> fillContactsList()
    {
        ArrayList<Contact> ls = new ArrayList<Contact>();
        try {


            /* Code To Query Data From Contact App of Phone*/
            ContentResolver contentResolver = getActivity().getContentResolver();
            Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                /* Contact extraction logic goes here*/

                Contact c1 = new Contact(getContext());
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

    public void getContactsFromServer()
    {
       // JsonObjectRequest
    }

}
