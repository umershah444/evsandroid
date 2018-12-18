package com.example.umer.evsandroid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends Activity {

    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button btn=findViewById(R.id.gotosignin);
        username=findViewById(R.id.etusername);
        password=findViewById(R.id.etpassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        Button signupBtn=findViewById(R.id.signupbtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name=username.getText().toString();
                String _password=password.getText().toString();
                String url="http://belockchain.com/api/signup.php?user_name="+user_name+"&password="+_password;

                JsonObjectRequest jsonObjectRequest=
                        new JsonObjectRequest(
                                Request.Method.GET,
                                url,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                                        startActivity(i);

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(SignupActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue rq=Volley.newRequestQueue(SignupActivity.this);
                rq.add(jsonObjectRequest);

            }
        });
    }

}
