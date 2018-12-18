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

public class LoginActivity extends Activity {
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.etuname);
        password=findViewById(R.id.etpasswordi);

        Button btn=findViewById(R.id.gotosignup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

        Button signupBtn=findViewById(R.id.loginbtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user_name=username.getText().toString();
                String _password=password.getText().toString();
                String url="http://belockchain.com/api/signin.php?user_name="+user_name+"&password="+_password;

                JsonObjectRequest jsonObjectRequest=
                        new JsonObjectRequest(
                                Request.Method.GET,
                                url,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        try {

                                            if(response.isNull("user"))
                                            {
                                                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                                            }
                                            else
                                            {
                                                JSONObject myResponse = response.getJSONObject("user");

                                                UserSession userSession=new UserSession(LoginActivity.this);
                                                userSession.createUserLoginSession(myResponse.get("user_name").toString(),myResponse.get("user_id").toString());

                                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                                startActivity(i);
                                                // Toast.makeText(LoginActivity.this, myResponse.get("user_name").toString(), Toast.LENGTH_LONG).show();
                                            }
                                            //String myResponse.get()
                                            //Toast.makeText(SignupActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                                        }catch (JSONException e)
                                        {
                                            Toast.makeText(LoginActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue rq= Volley.newRequestQueue(LoginActivity.this);
                rq.add(jsonObjectRequest);

            }
        });
    }

}
