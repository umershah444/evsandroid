package com.example.umer.evsandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyDbContactsFragment extends Fragment {
    ArrayList<Contact> myArrayListOfContacts;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.my_db_contacts_fragment,container,false);
        RecyclerView myrcv=v.findViewById(R.id.mydbcontactsrcv);
        DatabaseHelper db=new DatabaseHelper(getContext());
        myArrayListOfContacts=db.getAllContacts();
        ContactAdaptor myAdaptor=new ContactAdaptor(myArrayListOfContacts);
        myrcv.setLayoutManager(new LinearLayoutManager(getContext()));
        myrcv.setAdapter(myAdaptor);
        return v;
    }
}
