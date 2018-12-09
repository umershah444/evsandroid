package com.example.umer.evsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;

public class splash extends Activity {




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        },4000);



    }

}
