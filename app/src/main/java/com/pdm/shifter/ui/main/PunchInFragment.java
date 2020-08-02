package com.pdm.shifter.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.shifter.TimelineAdapter;
import com.pdm.shifter.databinding.PunchInFragmentBinding;
import com.pdm.shifter.dummy.DummyContent;
import com.pdm.shifter.dummy.PunchType;

import java.util.ArrayList;
import java.util.UUID;

public class PunchInFragment extends Fragment {

    private PunchInFragmentBinding binding;
    private TimelineAdapter timelineAdapter;
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
        RecyclerView recyclerView = binding.timelineCurrent;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        timelineAdapter = new TimelineAdapter(new ArrayList<DummyContent.DummyItem>(), getContext());
        recyclerView.setAdapter(timelineAdapter);

        binding.btnPunchIn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                timelineAdapter.addItem(new DummyContent.DummyItem(UUID.randomUUID().toString(), binding.textClock.getText().toString(), PunchType.IN));
                return true;
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PunchInViewModel.class);
        // TODO: Use the ViewModel
    }

}