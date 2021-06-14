package com.example.projectuas;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class halaman_registerAdapter extends FragmentStateAdapter {
private Fragment a;
    public halaman_registerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
public halaman_registerAdapter(@NonNull Fragment fragment){
        super(fragment);
}
public halaman_registerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
}
@NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                a=new halaman_register_guru_fragment();
                break;
            case 1:
                 a= new halaman_register_murid_fragment();
                 break;
        }
return a;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
