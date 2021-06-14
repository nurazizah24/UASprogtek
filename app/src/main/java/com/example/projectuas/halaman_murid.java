package com.example.projectuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class halaman_murid extends AppCompatActivity {
    FrameLayout konten;
BottomNavigationView bottom_navigasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_murid);

        konten=findViewById(R.id.konten);
        bottom_navigasi=findViewById(R.id.bottom_navigasi);


        if(konten!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.konten,new home_murid_fragment()).commit();
        }
        bottom_navigasi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.homeku){
                    buka_fragment(new home_murid_fragment());
                    return true;
                }
                if(item.getItemId()==R.id.temukan_guru){
                    buka_fragment(new halaman_temukan_guru_fragment());
                    return true;
                }
                if(item.getItemId()==R.id.list){
                    buka_fragment(new halaman_list_pesanan_kursus_murid_fragment());
                    return true;
                }
                if(item.getItemId()==R.id.profile){
                    buka_fragment(new halaman_profile_murid_fragment());
                    return true;
                }
                return false;
            }
        });


    }
Boolean buka_fragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.konten,fragment).commit();
            return true;
        }
        return false;
}

}