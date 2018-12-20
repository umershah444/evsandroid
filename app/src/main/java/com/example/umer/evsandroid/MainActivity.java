package com.example.umer.evsandroid;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    TabLayout tabLayout;
    ProgressDialog pd;
    MyViewPagerAdaptor viewPagerAdaptor;
    ViewPager viewPager;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.item4)
        {
            UserSession userSession=new UserSession(getBaseContext());
            userSession.logoutUser();
        }else if(id==R.id.item3)
        {
            pd=new ProgressDialog(this);

            pd.setTitle("Sending Contacts to Server");
            pd.show();
            JSONObject jsonObject=new JSONObject();
            UserSession userSession=new UserSession(this);
            DatabaseHelper databaseHelper=new DatabaseHelper(this);
            try {
                jsonObject.put("userID", userSession.getUserDetails().get(userSession.KEY_ID).toString());


                ArrayList<Contact> listContacts=databaseHelper.getAllContacts();

                JSONArray jsonArray=new JSONArray();
                for(Contact contact : listContacts)
                {

                    JSONObject cnt=new JSONObject();
                    cnt.put("contactName",contact.getName());
                    cnt.put("phone",contact.getPhoneNo());
                    jsonArray.put(cnt);

                }

                jsonObject.put("contactList", jsonArray);

                String url="http://belockchain.com/api/save_contacts.php";
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                pd.hide();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);

            }catch (JSONException e)
            {
                /* WRITE CODE TO HANDLE EXCEPTION */
            }
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  SharedPreferences sharedPreferences=this.getSharedPreferences("mysp",0);
        SharedPreferences.Editor myEditor=sharedPreferences.edit();
        myEditor.putString("user_name","abc");

        myEditor.commit();
        myEditor.clear();

        sharedPreferences.getString("user_name","nothing");
*/


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
