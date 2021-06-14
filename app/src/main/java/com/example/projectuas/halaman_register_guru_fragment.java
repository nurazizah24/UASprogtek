package com.example.projectuas;

import android.content.Intent;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;

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

public class halaman_register_guru_fragment extends Fragment {
    Toast toast;
    private String jenis_kelamin_guru="";
   private String keahlian_guru="";
    TextInputLayout username_register_guru, kota_register_guru, password_register_guru,
            keterangan_register_guru, nama_register_guru, nomor_hp_register_guru;
    RadioGroup jenis_kelamin_register_guru, keahlian_register_guru;
    RadioButton perempuan, laki, bahasa_arab, bahasa_perancis, bahasa_inggris;
    Button button_register_guru;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.form_register_guru, container, false);

        initialisasi(root);
        ongoingTextInput();
        radio_keahlian_change();
        radio_jenis_kelamin_change();

        button_register_guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String passwordguru = password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru = keterangan_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru = nomor_hp_register_guru.getEditText().getText().toString().trim();

                proses_register_guru(usernameguru, namaguru, kotaguru, keahlian_guru, nomorhpguru, passwordguru, jenis_kelamin_guru,
                        keteranganguru);
            }
        });


        return root;
    }

    private void radio_keahlian_change() {

        keahlian_register_guru.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String passwordguru = password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru = keterangan_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru = nomor_hp_register_guru.getEditText().getText().toString().trim();

                switch (checkedId){
                    case R.id.bahasa_arab:
                        keahlian_guru="Bahasa Arab";
                        break;
                    case R.id.bahasa_inggris:
                        keahlian_guru="Bahasa Inggris";
                        break;
                    case R.id.bahasa_perancis:
                        keahlian_guru="Bahasa Perancis";
                        break;
                }
                button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                        !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                        keahlian_guru!="");
            }
        });
    }

    private void radio_jenis_kelamin_change() {

        jenis_kelamin_register_guru.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String passwordguru = password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru = keterangan_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru = nomor_hp_register_guru.getEditText().getText().toString().trim();

                switch (checkedId){
                    case R.id.perempuan:
                        jenis_kelamin_guru="Perempuan";

                        break;
                    case R.id.laki:
                        jenis_kelamin_guru="Laki - Laki";
                        break;


                }
                button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                        !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                        keahlian_guru!="");
            }
        });
    }

    private void initialisasi(ViewGroup root) {
        perempuan = root.findViewById(R.id.perempuan);
        laki = root.findViewById(R.id.laki);
        bahasa_arab = root.findViewById(R.id.bahasa_arab);
        bahasa_inggris = root.findViewById(R.id.bahasa_inggris);
        bahasa_perancis = root.findViewById(R.id.bahasa_perancis);
        button_register_guru = root.findViewById(R.id.button_register_guru);
        username_register_guru = root.findViewById(R.id.username_register_guru);
        kota_register_guru = root.findViewById(R.id.kota_register_guru);
        password_register_guru = root.findViewById(R.id.password_regsiter_guru);
        keahlian_register_guru = root.findViewById(R.id.keahlian_register_guru);
        keterangan_register_guru = root.findViewById(R.id.keterangan_register_guru);
        nama_register_guru = root.findViewById(R.id.nama_register_guru);
        jenis_kelamin_register_guru = root.findViewById(R.id.jenis_kelamin_register_guru);
        nomor_hp_register_guru = root.findViewById(R.id.nomor_hp_register_guru);
    }

    private void proses_register_guru(String usernameguru, String namaguru, String kotaguru, String keahlianguru,
                                      String nomorhpguru, String passwordguru, String jeniskelaminguru, String keteranganguru) {

        String url = Global.base_url + "controller_proses_addGuru.php";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);

                    if (json.getString("infoberhasilregister_guru").equalsIgnoreCase("berhasil")) {

                        username_register_guru.getEditText().setText("");
                        nama_register_guru.getEditText().setText("");
                        kota_register_guru.getEditText().setText("");
                        nomor_hp_register_guru.getEditText().setText("");
                       password_register_guru.getEditText().setText("");
                        keterangan_register_guru.getEditText().setText("");




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
                        username_register_guru.getEditText().setText("");
                        nomor_hp_register_guru.getEditText().setText("");

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
                data_user.put("username_guru", usernameguru);
                data_user.put("nama_guru", namaguru);
                data_user.put("kota_guru", kotaguru);
                data_user.put("keahlian_guru", keahlian_guru);
                data_user.put("nomor_hp_guru", nomorhpguru );
                data_user.put("password_guru", passwordguru);
                data_user.put("jenis_kelamin_guru", jenis_kelamin_guru);
                data_user.put("keterangan_guru", keteranganguru);

                return data_user;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }
    private void ongoingTextInput(){
       nama_register_guru.getEditText().addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged (CharSequence s,int start, int count, int after){

        }

        @Override
        public void onTextChanged (CharSequence s,int start, int before, int count){
            String usernameguru = username_register_guru.getEditText().getText().toString().trim();
            String namaguru = nama_register_guru.getEditText().getText().toString().trim();
            String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
String nomorhpguru=nomor_hp_register_guru.getEditText().getText().toString().trim();
String passwordguru=password_register_guru.getEditText().getText().toString().trim();
String keteranganguru=keterangan_register_guru.getEditText().getText().toString().trim();



            if (namaguru.isEmpty()) {
                nama_register_guru.setError("Please Fill this Column!");
            } else {
               nama_register_guru.setError("");
            }
            button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                    !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                    keahlian_guru!="");
        }

        @Override
        public void afterTextChanged (Editable s){

        }
    });

        username_register_guru.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru=nomor_hp_register_guru.getEditText().getText().toString().trim();
                String passwordguru=password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru=keterangan_register_guru.getEditText().getText().toString().trim();



                if (usernameguru.isEmpty()) {
                    username_register_guru.setError("Please Fill this Column!");
                } else {
                    username_register_guru.setError("");
                }
               button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                        !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                        keahlian_guru!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });


        kota_register_guru.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru=nomor_hp_register_guru.getEditText().getText().toString().trim();
                String passwordguru=password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru=keterangan_register_guru.getEditText().getText().toString().trim();



                if (kotaguru.isEmpty()) {
                    kota_register_guru.setError("Please Fill this Column!");
                } else {
                    kota_register_guru.setError("");
                }
               button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                        !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                        keahlian_guru!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });


        nomor_hp_register_guru.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru=nomor_hp_register_guru.getEditText().getText().toString().trim();
                String passwordguru=password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru=keterangan_register_guru.getEditText().getText().toString().trim();



                if (nomorhpguru.isEmpty()) {
                    nomor_hp_register_guru.setError("Please Fill this Column!");
                } else {
                    nomor_hp_register_guru.setError("");
                }
              button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                        !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                        keahlian_guru!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });


        password_register_guru.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru=nomor_hp_register_guru.getEditText().getText().toString().trim();
                String passwordguru=password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru=keterangan_register_guru.getEditText().getText().toString().trim();



                if (passwordguru.isEmpty()) {
                    password_register_guru.setError("Please Fill this Column!");
                } else {
                    password_register_guru.setError("");
                }
              button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                        !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                        keahlian_guru!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });


        keterangan_register_guru.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){

            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
                String usernameguru = username_register_guru.getEditText().getText().toString().trim();
                String namaguru = nama_register_guru.getEditText().getText().toString().trim();
                String kotaguru = kota_register_guru.getEditText().getText().toString().trim();
                String nomorhpguru=nomor_hp_register_guru.getEditText().getText().toString().trim();
                String passwordguru=password_register_guru.getEditText().getText().toString().trim();
                String keteranganguru=keterangan_register_guru.getEditText().getText().toString().trim();



                if (keteranganguru.isEmpty()) {
                    keterangan_register_guru.setError("Please Fill this Column!");
                } else {
                    keterangan_register_guru.setError("");
                }
                button_register_guru.setEnabled(!namaguru.isEmpty() &&!usernameguru.isEmpty()&&!kotaguru.isEmpty()&&
                        !nomorhpguru.isEmpty()&&!passwordguru.isEmpty()&&!keteranganguru.isEmpty()&&jenis_kelamin_guru!=""&&
                        keahlian_guru!="");
            }

            @Override
            public void afterTextChanged (Editable s){

            }
        });
}
}
