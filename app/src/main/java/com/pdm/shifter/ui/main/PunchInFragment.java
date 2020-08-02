package com.pdm.shifter.ui.main;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pdm.shifter.R;
import com.pdm.shifter.TimelineAdapter;
import com.pdm.shifter.databinding.PunchInFragmentBinding;
import com.pdm.shifter.dummy.DummyContent;

public class PunchInFragment extends Fragment {

    private PunchInFragmentBinding binding;
    private Button mPunchInButton;
    private PunchInViewModel mViewModel;

    public static PunchInFragment newInstance() {
        return new PunchInFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PunchInFragmentBinding.inflate(inflater, container, false);
        RecyclerView recyclerView = binding.timelineCurrent;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TimelineAdapter(DummyContent.ITEMS, getContext()));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PunchInViewModel.class);
        // TODO: Use the ViewModel
    }

}