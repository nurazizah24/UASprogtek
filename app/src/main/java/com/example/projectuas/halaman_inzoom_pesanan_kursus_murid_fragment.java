package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.list_guru;

import static android.content.Context.MODE_PRIVATE;

public class halaman_inzoom_pesanan_kursus_murid_fragment extends Fragment {
    private RecyclerView itemmodel_rycyclerView;
    private ArrayList<list_guru> dataguru;
    private pesanan_kursus_inzoom_murid_Adapter pesanankursusinzoomadapter;
    private list_guru objguru;
    SharedPreferences getDataSession;
    private TextView textitemnodata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view=(ViewGroup) inflater.inflate(R.layout.activity_halaman_inzoom_pesanan_kursus_murid, container, false);



        inisialisasi(view);
        setRycycleView();




        getAlldataGuru(getDataSession.getString("session_username_user", null));
return view;

    }

    private void setRycycleView() {
        RecyclerView.LayoutManager layout=new LinearLayoutManager(getActivity());
        itemmodel_rycyclerView.setLayoutManager(layout);
        itemmodel_rycyclerView.setAdapter( pesanankursusinzoomadapter);
    }

    private void inisialisasi(ViewGroup view) {
        getDataSession=getActivity().getSharedPreferences("data", MODE_PRIVATE);
        textitemnodata=view.findViewById(R.id.textitemnodata);
        itemmodel_rycyclerView=view.findViewById(R.id.itemmodel_rycyclerView);
        dataguru=new ArrayList<list_guru>();
        pesanankursusinzoomadapter=new pesanan_kursus_inzoom_murid_Adapter(dataguru);
    }

    private void getAlldataGuru(String username_murid){
        String url=Global.base_url+"controller_getAllDataguru_pesanan_murid_inzoom.php";
        RequestQueue myQueue = Volley.newRequestQueue(getActivity());

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonGet=new JSONObject(response);
                            JSONArray jsondataarrayguruall = jsonGet.getJSONArray("data_guru");
                            for(int i = 0; i < jsondataarrayguruall.length(); i++){
                                JSONObject jsondataobjguruall = jsondataarrayguruall.getJSONObject(i);
                                list_guru datagurunew = new list_guru();
                                datagurunew.setNama_guru(jsondataobjguruall.getString("nama_guru"));
                                datagurunew.setKota_guru(jsondataobjguruall.getString("kota_guru"));
                                datagurunew.setKeahlian_guru(jsondataobjguruall.getString("keahlian_guru"));

                                dataguru.add(datagurunew);
                            }
                            pesanankursusinzoomadapter.notifyDataSetChanged();
                            if(dataguru.size()==0){
                                textitemnodata.setText("No Data");
                            }else{
                                textitemnodata.setText("");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("username_user",username_murid);

                return params;
            }
        };

        myQueue.add(request);
    }
}