package com.pdm.shifter.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pdm.shifter.Fragment.SearchShiftFragment;
import com.pdm.shifter.R;

public class SearchShiftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_shift_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SearchShiftFragment.newInstance())
                    .commitNow();
        }
    }
}