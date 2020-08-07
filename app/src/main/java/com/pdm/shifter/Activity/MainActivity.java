package com.pdm.shifter.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pdm.shifter.Fragment.MainFragment;
import com.pdm.shifter.Fragment.TodaysPunchesFragment;
import com.pdm.shifter.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.bottom_container, TodaysPunchesFragment.newInstance())
                    .commitNow();
        }
    }
}