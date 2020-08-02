package com.pdm.shifter.ui.main;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joaquimley.faboptions.FabOptions;
import com.pdm.shifter.PunchIn;
import com.pdm.shifter.R;
import com.pdm.shifter.TimelineAdapter;
import com.pdm.shifter.databinding.MainFragmentBinding;
import com.pdm.shifter.dummy.DummyContent;

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
        FabOptions fabOptions = binding.fabOptions;
        fabOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.fab_options_clock_in:
                        Intent intent = new Intent(getActivity(), PunchIn.class);
                        startActivity(intent);
                        break;
                    default:
                        break;

                }
            }
        });
        RecyclerView recyclerView = binding.timelineCurrent;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TimelineAdapter(DummyContent.ITEMS, getContext()));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}