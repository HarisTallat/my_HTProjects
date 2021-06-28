package com.example.htproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class vaccineLayout extends AppCompatActivity {
    Toolbar toolbar;
Vaccine1 vaccine1;
Vaccine2 vaccine2;
Vaccine3 vaccine3;
    TabLayout tabLayout;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_layout);
        init();
        setSupportActionBar(toolbar);
        vaccine1= new Vaccine1();
        vaccine2= new Vaccine2();
        vaccine3= new Vaccine3();
        ViewPagerAdopter viewPagerAdopter= new ViewPagerAdopter(getSupportFragmentManager(), 0);
        viewPagerAdopter.addFragment(vaccine1, "Pfizer");
        viewPagerAdopter.addFragment(vaccine2,"Sinofarm");
        viewPagerAdopter.addFragment(vaccine3, "Moderna");
        viewpager.setAdapter(viewPagerAdopter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_local_hospital_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_local_hospital_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_local_hospital_24);
    }
    public class ViewPagerAdopter extends FragmentPagerAdapter
    {
        List<Fragment> fragments= new ArrayList<>();
        List<String> titles= new ArrayList<>();

        public ViewPagerAdopter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        public  void addFragment(Fragment fragment, String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
   public void init()
    {
        viewpager=findViewById(R.id.viewpager);
        tabLayout= findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewpager);
    }

}
