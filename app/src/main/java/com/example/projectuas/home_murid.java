package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class home_murid extends AppCompatActivity {
    SharedPreferences getDataSession;
    private Intent intent;
    private int get_id_murid;
    private Intent i;
    private TextView textview_id_murid, textview_kota_murid, textview_username_murid, textview_jumlah_inwaiting_murid, textview_jumlah_inzoom_murid,
            textview_jumlah_inhistori_murid;
    CardView cardView_inzoom_murid, cardView_inpembayaran_murid, cardView_inhistori_murid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_murid);

inisialisasi();
getDataAllMurid_byUsername(getDataSession.getString("session_username_user", null));
        cardView_inhistori_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getApplicationContext(), halaman_inhistori_pesanan_kursus_murid.class);
                startActivity(i);
            }
        });


        cardView_inpembayaran_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getApplicationContext(), halaman_inpembayaran_pesanan_kursus_murid.class);
                startActivity(i);
            }
        });


        cardView_inzoom_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getApplicationContext(), halaman_inzoom_pesanan_kursus_murid.class);
                startActivity(i);
            }
        });

    }



    public void getDataAllMurid_byUsername(String username_login) {
        String url=Global.base_url+"controller_getALlData_murid_byUsername.php";

        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
 get_id_murid= json.getJSONObject("dataAll_murid").getInt("id_murid");
                            textview_id_murid.setText("Your ID: " + json.getJSONObject("dataAll_murid").getInt("id_murid"));
                            textview_kota_murid.setText("Kota: " + json.getJSONObject("dataAll_murid").getString("kota_murid"));
                            textview_username_murid.setText("Username: " + json.getJSONObject("dataAll_murid").getString("username_murid"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                Map<String, String> data_user=new HashMap<>();
                data_user.put("username_murid", username_login);

                return data_user;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);



        url=Global.base_url+"controller_list_pesanan_kursus_getJumlah.php";
        StringRequest request_pesanan= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json_pesanan = new JSONObject(response);

                            textview_jumlah_inzoom_murid.setText(json_pesanan.getJSONObject("jumlah_pesanan_kursus_murid").getInt("jumlah_inzoom_murid")+" Pesanan");
                            textview_jumlah_inwaiting_murid.setText(json_pesanan.getJSONObject("jumlah_pesanan_kursus_murid").getInt("jumlah_inpembayaran_murid")+" Pesanan");
                            textview_jumlah_inhistori_murid.setText(json_pesanan.getJSONObject("jumlah_pesanan_kursus_murid").getInt("jumlah_inhistori_murid")+" Pesanan");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data_user_pesanan=new HashMap<>();
                data_user_pesanan.put("id_murid", "get_id_murid");

                return data_user_pesanan;
            }
        };
        RequestQueue requestQueue_pesanan=Volley.newRequestQueue(this);
        requestQueue_pesanan.add(request_pesanan);

    }

    public void inisialisasi(){
        cardView_inhistori_murid=(CardView) findViewById(R.id.cardView_inhistori_murid);
        cardView_inpembayaran_murid=(CardView) findViewById(R.id.cardView_inpembayaran_murid);
        cardView_inzoom_murid=(CardView) findViewById(R.id.cardView_inzoom_murid);
        getDataSession=getSharedPreferences("data", MODE_PRIVATE);
        textview_id_murid=findViewById(R.id.textview_id_murid);
        textview_kota_murid=findViewById(R.id.textview_kota_murid);
        textview_username_murid=findViewById(R.id.textview_username_murid);
        textview_jumlah_inwaiting_murid=findViewById(R.id.textview_jumlah_inwaiting_murid);
        textview_jumlah_inzoom_murid=findViewById(R.id.textview_jumlah_inzoom_murid);
        textview_jumlah_inhistori_murid=findViewById(R.id.textview_jumlah_inhistori_murid);
    }
}