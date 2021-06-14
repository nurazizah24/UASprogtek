package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import model.list_murid_login;

public class halaman_login extends AppCompatActivity {

    private TextInputLayout username_login;
    private TextInputLayout password_login;
    private Button button_login;
    private TextView teks_to_register;
private   String username_user, infologin;
SharedPreferences.Editor setDataSession;
SharedPreferences getDataSession;

private  Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);

        inisialisasi();
        ongoingTextInput();
        onClicked();




}

    private void onClicked() {
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objpasswordlogin = password_login.getEditText().getText().toString().trim();
                String objusernamelogin = username_login.getEditText().getText().toString().trim();
                ceklogin(objusernamelogin, objpasswordlogin);
            }
        });


        teks_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), halaman_register.class);
                startActivity(intent);
            }
        });
    }

    private void ongoingTextInput() {
        password_login.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String objpasswordlogin = password_login.getEditText().getText().toString().trim();
                String objusernamelogin = username_login.getEditText().getText().toString().trim();

                button_login.setEnabled(!objpasswordlogin.isEmpty() && !objusernamelogin.isEmpty());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        username_login.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String objpasswordlogin = password_login.getEditText().getText().toString().trim();
                String objusernamelogin = username_login.getEditText().getText().toString().trim();

                button_login.setEnabled(!objpasswordlogin.isEmpty() && !objusernamelogin.isEmpty());


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void ceklogin(String objusernamelogin, String objpasswordlogin) {

String url=Global.base_url+"controller_proses_login.php";

        StringRequest request=new StringRequest(
                Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);

                    username_user=json.getJSONObject("data_user").getString("username_login");
                    infologin=json.getJSONObject("data_user").getString("infologin");
                    setDataSession.putString("session_username_user",username_user);
                    setDataSession.putString("session_infologin", infologin);
                    setDataSession.apply();
                    if(getDataSession.getString("session_infologin", null).equalsIgnoreCase("murid")) {

                        intent = new Intent(getApplicationContext(), halaman_murid.class);
                        startActivity(intent);
                        password_login.getEditText().setText("");
                        username_login.getEditText().setText("");
                    }else if(infologin.equalsIgnoreCase("") && username_user.equalsIgnoreCase("")){
                        password_login.getEditText().setText("");
                        username_login.getEditText().setText("");
                        Toast.makeText(getApplicationContext(),"Akun Tidak Tersedia!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                //else if(getDataSession.getString("session_infologin", null).equalsIgnoreCase("guru")) {
                   // intent = new Intent(getApplicationContext(), home_guru.class);
                //}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            }
        }
        ){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> datalogin_user=new HashMap<>();
                datalogin_user.put("username_user", objusernamelogin);
                datalogin_user.put("password_user", objpasswordlogin);
                return datalogin_user;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void inisialisasi() {
        teks_to_register=findViewById(R.id.teks_to_register);
        button_login = findViewById(R.id.button_login);
        setDataSession=getSharedPreferences("data", MODE_PRIVATE).edit();
        getDataSession=getSharedPreferences("data", MODE_PRIVATE);
        username_login = findViewById(R.id.username_login);
        password_login = findViewById(R.id.password_login);
    }
}