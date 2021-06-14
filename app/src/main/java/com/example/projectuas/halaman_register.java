package com.example.projectuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class halaman_register extends AppCompatActivity {
TabLayout tabLayout_login_register;
ViewPager2 ViewPager2_login_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_register);

        initialisasi();
ViewPager2_login_register.setAdapter(
        new halaman_registerAdapter(this)
);
new TabLayoutMediator(
        tabLayout_login_register, ViewPager2_login_register,
        new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("Guru");
                        break;
                    case 1:
                        tab.setText("Murid");
                        BadgeDrawable badgeDrawable=tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(
                                ContextCompat.getColor(getApplicationContext(), R.color.blue)

                        );
                                badgeDrawable.setVisible(true);
                                badgeDrawable.setNumber(100);
                                badgeDrawable.setMaxCharacterCount(3);
                        break;
                }

            }
        }
).attach();

ViewPager2_login_register.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        BadgeDrawable badgeDrawable=tabLayout_login_register.getTabAt(position).getOrCreateBadge();
        badgeDrawable.setVisible(true);
    }
});
    }

    private void initialisasi() {
        tabLayout_login_register=findViewById(R.id.tabLayout_login_register);
        ViewPager2_login_register=findViewById(R.id.ViewPager2_login_register);
    }

}