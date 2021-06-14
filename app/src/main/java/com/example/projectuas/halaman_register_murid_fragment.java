package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class halaman_register_murid_fragment extends Fragment {
    Toast toast;
    private String jenis_kelamin_murid="";
    TextInputLayout username_register_murid, kota_register_murid, password_register_murid,
            nama_register_murid, nomor_hp_register_murid;
    RadioGroup jenis_kelamin_register_murid;
    RadioButton laki_murid, perempuan_murid;
    Button button_register_murid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.form_register_murid, container, false);

        initialisasi(root);
        ongoingTextInput();
        radio_jenis_kelamin_change();

        button_register_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamemurid = username_register_murid.getEditText().getText().toString().trim();
                String namamurid = nama_register_murid.getEditText().getText().toString().trim();
                String kotamurid = kota_register_murid.getEditText().getText().toString().trim();
                String nomorhpmurid=nomor_hp_register_murid.getEditText().getText().toString().trim();
                String passwordmurid=password_register_murid.getEditText().getText().toString().trim();


                proses_register_murid(usernamemurid, namamurid, kotamurid,nomorhpmurid, passwordmurid, jenis_kelamin_murid);
            }
        });



        return root;
    }

    private void proses_register_murid(String usernamemurid, String namamurid, String kotamurid, String nomorhpmurid, String passwordmurid, String jenis_kelamin_murid) {
   String url = Global.base_url + "controller_proses_addMurid.php";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);

                    if (json.getString("infoberhasilregister_murid").equalsIgnoreCase("berhasil")) {
                        username_register_murid.getEditText().setText("");
                        nama_register_murid.getEditText().setText("");
                        kota_register_murid.getEditText().setText("");
                        nomor_hp_register_murid.getEditText().setText("");
                        password_register_murid.getEditText().setText("");

                        Toast.makeText(getActivity(),"Register Berhasil!",Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getActivity(), halaman_login.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        },2150L);



                    }else{
                        username_register_murid.getEditText().setText("");
                        nomor_hp_register_murid.getEditText().setText("");

                        Toast.makeText(getActivity(),"Register Gagal! Username atau Nomor Hp Telah Tersedia!",Toast.LENGTH_SHORT).show();
                    }
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
        ) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data_user = new HashMap<>();
                data_user.put("username_murid", usernamemurid);
                data_user.put("nama_murid", namamurid);
                data_user.put("kota_murid", kotamurid);

                data_user.put("nomor_hp_murid", nomorhpmurid);
                data_user.put("password_murid", passwordmurid);
                data_user.put("jenis_kelamin_murid", jenis_kelamin_murid);
                return data_user;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    private void radio_jenis_kelamin_change() {

        jenis_kelamin_register_murid.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String usernamemurid = username_register_murid.getEditText().getText().toString().trim();
                String namamurid = nama_register_murid.getEditText().getText().toString().trim();
                String kotamurid = kota_register_murid.getEditText().getText().toString().trim();
                String nomorhpmurid=nomor_hp_register_murid.getEditText().getText().toString().trim();
                String passwordmurid=password_register_murid.getEditText().getText().toString().trim();

                switch (checkedId){
                    case R.id.perempuan_murid:
                        jenis_kelamin_murid="Perempuan";
                        break;

                        case R.id.laki_murid:
                        jenis_kelamin_murid="Laki - Laki";
                        break;
                }
                button_register_murid.setEnabled(!namamurid.isEmpty() &&!usernamemurid.isEmpty()&&!kotamurid.isEmpty()&&
                        !nomorhpmurid.isEmpty()&&!passwordmurid.isEmpty()&&jenis_kelamin_murid!="");
            }
        });
    }

    private void ongoingTextInput(){
        nama_register_murid.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernamemurid = username_register_murid.getEditText().getText().toString().trim();
                String namamurid = nama_register_murid.getEditText().getText().toString().trim();
                String kotamurid = kota_register_murid.getEditText().getText().toString().trim();
                String nomorhpmurid=nomor_hp_register_murid.getEditText().getText().toString().trim();
                String passwordmurid=password_register_murid.getEditText().getText().toString().trim();




                if (namamurid.isEmpty()) {
                    nama_register_murid.setError("Please Fill this Column!");
                } else {
                    nama_register_murid.setError("");
                }
                button_register_murid.setEnabled(!namamurid.isEmpty() &&!usernamemurid.isEmpty()&&!kotamurid.isEmpty()&&
                        !nomorhpmurid.isEmpty()&&!passwordmurid.isEmpty()&&jenis_kelamin_murid!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });

        username_register_murid.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernamemurid = username_register_murid.getEditText().getText().toString().trim();
                String namamurid = nama_register_murid.getEditText().getText().toString().trim();
                String kotamurid = kota_register_murid.getEditText().getText().toString().trim();
                String nomorhpmurid=nomor_hp_register_murid.getEditText().getText().toString().trim();
                String passwordmurid=password_register_murid.getEditText().getText().toString().trim();



                if (usernamemurid.isEmpty()) {
                    username_register_murid.setError("Please Fill this Column!");
                } else {
                    username_register_murid.setError("");
                }
                button_register_murid.setEnabled(!namamurid.isEmpty() &&!usernamemurid.isEmpty()&&!kotamurid.isEmpty()&&
                        !nomorhpmurid.isEmpty()&&!passwordmurid.isEmpty()&&jenis_kelamin_murid!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });


        kota_register_murid.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernamemurid = username_register_murid.getEditText().getText().toString().trim();
                String namamurid = nama_register_murid.getEditText().getText().toString().trim();
                String kotamurid = kota_register_murid.getEditText().getText().toString().trim();
                String nomorhpmurid=nomor_hp_register_murid.getEditText().getText().toString().trim();
                String passwordmurid=password_register_murid.getEditText().getText().toString().trim();





                if (kotamurid.isEmpty()) {
                    kota_register_murid.setError("Please Fill this Column!");
                } else {
                    kota_register_murid.setError("");
                }
                button_register_murid.setEnabled(!namamurid.isEmpty() &&!usernamemurid.isEmpty()&&!kotamurid.isEmpty()&&
                        !nomorhpmurid.isEmpty()&&!passwordmurid.isEmpty()&&jenis_kelamin_murid!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });


        nomor_hp_register_murid.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernamemurid = username_register_murid.getEditText().getText().toString().trim();
                String namamurid = nama_register_murid.getEditText().getText().toString().trim();
                String kotamurid = kota_register_murid.getEditText().getText().toString().trim();
                String nomorhpmurid=nomor_hp_register_murid.getEditText().getText().toString().trim();
                String passwordmurid=password_register_murid.getEditText().getText().toString().trim();



                if (nomorhpmurid.isEmpty()) {
                    nomor_hp_register_murid.setError("Please Fill this Column!");
                } else {
                    nomor_hp_register_murid.setError("");
                }
                button_register_murid.setEnabled(!namamurid.isEmpty() &&!usernamemurid.isEmpty()&&!kotamurid.isEmpty()&&
                        !nomorhpmurid.isEmpty()&&!passwordmurid.isEmpty()&&jenis_kelamin_murid!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });


        password_register_murid.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernamemurid = username_register_murid.getEditText().getText().toString().trim();
                String namamurid = nama_register_murid.getEditText().getText().toString().trim();
                String kotamurid = kota_register_murid.getEditText().getText().toString().trim();
                String nomorhpmurid=nomor_hp_register_murid.getEditText().getText().toString().trim();
                String passwordmurid=password_register_murid.getEditText().getText().toString().trim();


                if (passwordmurid.isEmpty()) {
                    password_register_murid.setError("Please Fill this Column!");
                } else {
                    password_register_murid.setError("");
                }
                button_register_murid.setEnabled(!namamurid.isEmpty() &&!usernamemurid.isEmpty()&&!kotamurid.isEmpty()&&
                        !nomorhpmurid.isEmpty()&&!passwordmurid.isEmpty()&&jenis_kelamin_murid!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });



    }

    private void initialisasi(ViewGroup root) {
        laki_murid=root.findViewById(R.id.laki_murid);
        perempuan_murid=root.findViewById(R.id.perempuan_murid);
        button_register_murid=root.findViewById(R.id.button_register_murid);
        username_register_murid=root.findViewById(R.id.username_register_murid);
        kota_register_murid=root.findViewById(R.id.kota_register_murid);
        password_register_murid=root.findViewById(R.id.password_register_murid);
        nama_register_murid=root.findViewById(R.id.nama_register_murid);
        nomor_hp_register_murid=root.findViewById(R.id.nomor_hp_register_murid);
        jenis_kelamin_register_murid=root.findViewById(R.id.jenis_kelamin_register_murid);
    }
}