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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.list_guru;

import static android.content.Context.MODE_PRIVATE;

public class halaman_temukan_guru_fragment extends Fragment {

    private RecyclerView itemmodel_rycyclerView_temukan_guru;
    private ArrayList<list_guru> dataguru_temukan_guru;
    private temukan_guru_Adapter temukanguruadapter;
    private list_guru objguru_temukan_guru;
    SharedPreferences getDataSession;
    private TextView textitemnodata_temukan_guru;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_halaman_temukan_guru, container, false);

        inisialisasi(view);
        setRycycleView();

        getAlldataGuru(getDataSession.getString("session_username_user", null));
        return view;

    }

    private void setRycycleView() {
        RecyclerView.LayoutManager layout=new LinearLayoutManager(getActivity());
        itemmodel_rycyclerView_temukan_guru.setLayoutManager(layout);
        itemmodel_rycyclerView_temukan_guru.setAdapter(temukanguruadapter);
    }

    private void inisialisasi(ViewGroup view) {
        getDataSession=getActivity().getSharedPreferences("data", MODE_PRIVATE);
        textitemnodata_temukan_guru=view.findViewById(R.id.textitemnodata_temukan_guru);
        itemmodel_rycyclerView_temukan_guru=view.findViewById(R.id.itemmodel_rycyclerView_temukan_guru);
        dataguru_temukan_guru=new ArrayList<list_guru>();
        temukanguruadapter=new temukan_guru_Adapter(dataguru_temukan_guru);
    }
    private void getAlldataGuru(String username_murid) {
        String url=Global.base_url+"controller_getAllDataguru.php";
        RequestQueue myQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsondataarrayguruall = response.getJSONArray("data_guru");
                            for(int i = 0; i < jsondataarrayguruall.length(); i++){
                                JSONObject jsondataobjguruall = jsondataarrayguruall.getJSONObject(i);
                                list_guru datagurunew = new list_guru(jsondataobjguruall.getString("nama_guru"),
                                        jsondataobjguruall.getString("kota_guru"),
                                        jsondataobjguruall.getString("keahlian_guru"),
                                        jsondataobjguruall.getString("keterangan_tambahan_guru"),
                                        jsondataobjguruall.getString("jenis_kelamin_guru"));
                                dataguru_temukan_guru.add(datagurunew);
                            }
                            temukanguruadapter.notifyDataSetChanged();
                            if(dataguru_temukan_guru.size()==0){
                                textitemnodata_temukan_guru.setText("No Data");
                            }else{
                                textitemnodata_temukan_guru.setText("");
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
        );

        myQueue.add(request);
    }
}
