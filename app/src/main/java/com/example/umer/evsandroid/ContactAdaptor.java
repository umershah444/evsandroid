package com.example.umer.evsandroid;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdaptor extends RecyclerView.Adapter<ContactAdaptor.ContactViewHolder>{

      ArrayList<Contact> myContactList;


      public ContactAdaptor(ArrayList<Contact> list)
      {

          myContactList=list;
      }
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int i) {

        LayoutInflater MylayoutInflater=LayoutInflater.from(vg.getContext());
        View inflatedView=MylayoutInflater.inflate(R.layout.contact_list_item,vg,false);
        ContactViewHolder contactViewHolder=new ContactViewHolder(inflatedView);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {

       Contact contact=myContactList.get(i);
        contactViewHolder.NameTextView.setText(contact.getName());
        contactViewHolder.PhoneNoTextView.setText(contact.getPhoneNo());
    }

    @Override
    public int getItemCount() {
        return myContactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder
    {

        TextView PhoneNoTextView,NameTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            PhoneNoTextView=itemView.findViewById(R.id.phoneno);
            NameTextView=itemView.findViewById(R.id.name);

        }
    }
}
