package com.pdm.shifter.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pdm.shifter.ViewModel.PunchInViewModel;
import com.pdm.shifter.databinding.PunchInFragmentBinding;

public class PunchInFragment extends Fragment {

    private PunchInFragmentBinding binding;
    private PunchInViewModel mViewModel;

    public static PunchInFragment newInstance() {
        return new PunchInFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PunchInFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(PunchInViewModel.class);

        binding.btnPunchIn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Bundle result = new Bundle();
                result.putString("punchTime", binding.textClock.getText().toString());
                getParentFragmentManager().setFragmentResult("punchAdded", result);

                return true;
            }
        });
        return binding.getRoot();
    }
}