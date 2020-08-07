package com.pdm.shifter.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.joaquimley.faboptions.FabOptions;
import com.pdm.shifter.R;
import com.pdm.shifter.ViewModel.MainViewModel;
import com.pdm.shifter.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainFragmentBinding binding;
    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        FabOptions fabOptions = binding.fabOptions;
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.fab_options_clock_in) {
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, PunchInFragment.newInstance())
                            .addToBackStack("main_fragment")
                            .commit();
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}