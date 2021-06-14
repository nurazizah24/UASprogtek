package com.example.projectuas;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.list_murid_login;

import static android.content.Context.MODE_PRIVATE;

public class home_murid_fragment extends Fragment {
    SharedPreferences getDataSession;
    private Intent intent;
    private int get_id_murid, get_saldo_murid;
    private Intent i;
    private ArrayList<list_murid_login> arraymuridlogin=new ArrayList<list_murid_login>();
    private list_murid_login listmuridlogin;
    private String get_username_murid, get_nama_murid, get_jenis_kelamin_murid,  get_nomor_hp_murid, get_kota_murid;
    private TextView textview_id_murid, textview_kota_murid, textview_username_murid, textview_jumlah_inwaiting_murid, textview_jumlah_inzoom_murid,
            textview_jumlah_inhistori_murid;
    CardView cardView_inzoom_murid, cardView_inpembayaran_murid, cardView_inhistori_murid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=(View) inflater.inflate(R.layout.activity_home_murid, container, false);


        inisialisasi(view);

getDataAllMurid_byUsername(getDataSession.getString("session_username_user", null));
        cardView_inhistori_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getActivity(), halaman_inhistori_pesanan_kursus_murid_fragment.class);
                startActivity(i);
            }
        });


        cardView_inpembayaran_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getActivity(), halaman_inpembayaran_pesanan_kursus_murid_fragment.class);
                startActivity(i);
            }
        });


        cardView_inzoom_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=new Intent(getActivity(), halaman_inzoom_pesanan_kursus_murid_fragment.class);
                startActivity(i);
            }
        });



return view;
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
                            get_username_murid= json.getJSONObject("dataAll_murid").getString("username_murid");
                            get_saldo_murid= json.getJSONObject("dataAll_murid").getInt("saldo_murid");
                            get_nama_murid= json.getJSONObject("dataAll_murid").getString("nama_murid");
                            get_jenis_kelamin_murid= json.getJSONObject("dataAll_murid").getString("jenis_kelamin_murid");
                            get_nomor_hp_murid= json.getJSONObject("dataAll_murid").getString("nomor_hp_murid");
                            get_kota_murid= json.getJSONObject("dataAll_murid").getString("kota_murid");
                            listmuridlogin=new list_murid_login(get_id_murid,get_username_murid,get_saldo_murid,get_nama_murid,get_jenis_kelamin_murid,get_nomor_hp_murid,get_kota_murid);
arraymuridlogin.add(listmuridlogin);
                            textview_id_murid.setText("Your ID: " +listmuridlogin.getId_murid());
                            textview_kota_murid.setText("Kota: " + listmuridlogin.getKota_murid());
                            textview_username_murid.setText("Username: " + listmuridlogin.getUsername_murid());


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
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
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
                data_user_pesanan.put("id_murid", String.valueOf(get_id_murid));

                return data_user_pesanan;
            }
        };
        RequestQueue requestQueue_pesanan=Volley.newRequestQueue(getActivity());
        requestQueue_pesanan.add(request_pesanan);

    }

    public void inisialisasi(View root){
        cardView_inhistori_murid=(CardView) root.findViewById(R.id.cardView_inhistori_murid);
        cardView_inpembayaran_murid=(CardView) root.findViewById(R.id.cardView_inpembayaran_murid);
        cardView_inzoom_murid=(CardView) root.findViewById(R.id.cardView_inzoom_murid);
        getDataSession=getActivity().getSharedPreferences("data", MODE_PRIVATE);
        textview_id_murid=root.findViewById(R.id.textview_id_murid);
        textview_kota_murid=root.findViewById(R.id.textview_kota_murid);
        textview_username_murid=root.findViewById(R.id.textview_username_murid);
        textview_jumlah_inwaiting_murid=root.findViewById(R.id.textview_jumlah_inwaiting_murid);
        textview_jumlah_inzoom_murid=root.findViewById(R.id.textview_jumlah_inzoom_murid);
        textview_jumlah_inhistori_murid=root.findViewById(R.id.textview_jumlah_inhistori_murid);
    }
}