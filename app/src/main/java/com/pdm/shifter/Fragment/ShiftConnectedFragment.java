package com.pdm.shifter.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pdm.shifter.R;
import com.pdm.shifter.databinding.ShiftConnectedFragmentBinding;

public class ShiftConnectedFragment extends Fragment {

    private ShiftConnectedViewModel mViewModel;
    private ShiftConnectedFragmentBinding binding;

    public static ShiftConnectedFragment newInstance() {
        return new ShiftConnectedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ShiftConnectedViewModel.class);
        binding = ShiftConnectedFragmentBinding.inflate(inflater, container, false);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDisconnect();
            }
        });

        return inflater.inflate(R.layout.shift_connected_fragment, container, false);
    }

    private void onDisconnect() {
        getParentFragmentManager()
                .popBackStackImmediate();
    }
}