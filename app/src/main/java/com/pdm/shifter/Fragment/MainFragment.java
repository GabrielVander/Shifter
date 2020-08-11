package com.pdm.shifter.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.joaquimley.faboptions.FabOptions;
import com.pdm.shifter.Activity.HistoryActivity;
import com.pdm.shifter.R;
import com.pdm.shifter.ViewModel.MainViewModel;
import com.pdm.shifter.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainFragmentBinding binding;
    private MainViewModel mViewModel;
    private MainFragment fragment = this;

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
                switch (view.getId()) {
                    case R.id.fab_options_clock_in:
                        onClockInButton();
                        break;
                    case R.id.fab_options_share:
                        onShareButton();
                        break;
                    case R.id.fab_options_history:
                        onHistoryButton();
                        break;
                }
            }
        });

        return binding.getRoot();
    }

    private void onClockInButton() {
        Toast.makeText(getContext(), R.string.fab_menu_clock_in, Toast.LENGTH_SHORT).show();

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.container, PunchInFragment.newInstance())
                .addToBackStack("container")
                .commit();
    }

    private void onShareButton() {
        Toast.makeText(getContext(), R.string.fab_menu_share, Toast.LENGTH_SHORT).show();

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ShareFragment.newInstance())
                .addToBackStack("container")
                .commit();
    }

    private void onHistoryButton() {
        Toast.makeText(getContext(), R.string.fab_menu_history, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}