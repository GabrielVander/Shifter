package com.pdm.shifter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pdm.shifter.ui.main.PunchInFragment;

public class PunchIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.punch_in_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PunchInFragment.newInstance())
                    .commitNow();
        }
    }
}