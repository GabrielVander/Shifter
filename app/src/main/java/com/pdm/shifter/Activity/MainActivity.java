package com.pdm.shifter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pdm.shifter.Fragment.MainFragment;
import com.pdm.shifter.Fragment.TodaysPunchesFragment;
import com.pdm.shifter.R;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    assert user != null;
                    Toast.makeText(MainActivity.this, getString(R.string.signed_in_as, user.getDisplayName()), Toast.LENGTH_SHORT).show();
                    setupView();
                } else {
                    Toast.makeText(MainActivity.this, R.string.sign_in_failed, Toast.LENGTH_SHORT).show();
                }

            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build()
                );

                mLauncher.launch(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .setTheme(R.style.AppTheme)
                                .build()
                );
            } else {
                setupView();
            }
        }
    }

    private void setupView() {
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