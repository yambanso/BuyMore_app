package com.example.buymore_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.buymore_app.home_fragments.fashion;
import com.example.buymore_app.home_fragments.homefrag;
import com.example.buymore_app.home_fragments.laptops;
import com.example.buymore_app.home_fragments.phones;

import org.jetbrains.annotations.NotNull;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1 :
                return  new laptops();
            case 2 :
                return new fashion();
            case 3 :
                return new phones();
        }
        return new homefrag() ;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
