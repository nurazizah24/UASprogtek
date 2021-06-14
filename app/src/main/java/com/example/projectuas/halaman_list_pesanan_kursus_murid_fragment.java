package com.example.projectuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class halaman_list_pesanan_kursus_murid_fragment extends Fragment {
    TabLayout tabLayout_list_pesanan_kursus_murid;
    ViewPager2 ViewPager2_list_pesanan_kursus_murid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view=(ViewGroup) inflater.inflate(R.layout.activity_halaman_list_pesanan_kursus_murid, container, false);
        initialisasi(view);

        ViewPager2_list_pesanan_kursus_murid.setAdapter(
                new halaman_list_pesanan_kursus_muridAdapter(this)
        );
        new TabLayoutMediator(
                tabLayout_list_pesanan_kursus_murid, ViewPager2_list_pesanan_kursus_murid,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch(position){
                            case 0:
                                tab.setText("Transaksi Pembayaran");
                                BadgeDrawable badgeDrawable=tab.getOrCreateBadge();
                                badgeDrawable.setBackgroundColor(
                                        ContextCompat.getColor(getActivity(), R.color.blue)

                                );
                                badgeDrawable.setVisible(true);
                                badgeDrawable.setNumber(100);
                                badgeDrawable.setMaxCharacterCount(3);
                                break;

                            case 1:
                                tab.setText("Mulai Kelas");
                                break;

                            case 2:
                                tab.setText("Histori Kursus");
                                break;
                        }

                    }
                }
        ).attach();

        ViewPager2_list_pesanan_kursus_murid.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable=tabLayout_list_pesanan_kursus_murid.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(true);
            }
        });
    return view;
    }

    private void initialisasi(ViewGroup view) {
        tabLayout_list_pesanan_kursus_murid=view.findViewById(R.id.tabLayout_list_pesanan_kursus_murid);
        ViewPager2_list_pesanan_kursus_murid=view.findViewById(R.id.ViewPager2_list_pesanan_kursus_murid);
    }
}