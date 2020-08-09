package com.pdm.shifter.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.shifter.Adapter.TimelineAdapter;
import com.pdm.shifter.R;
import com.pdm.shifter.ViewModel.TodaysPunchesViewModel;
import com.pdm.shifter.databinding.TodaysPunchesFragmentBinding;
import com.pdm.shifter.dummy.DummyContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class TodaysPunchesFragment extends Fragment {

    private TodaysPunchesFragmentBinding binding;
    private TimelineAdapter timelineAdapter;
    private TodaysPunchesViewModel mViewModel;

    public static TodaysPunchesFragment newInstance() {
        return new TodaysPunchesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TodaysPunchesFragmentBinding.inflate(inflater, container, false);
        timelineAdapter = new TimelineAdapter(DummyContent.ITEMS, getContext());
        mViewModel = new ViewModelProvider(this).get(TodaysPunchesViewModel.class);

        RecyclerView recyclerView = binding.timelineCurrent;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(timelineAdapter);

        getParentFragmentManager().setFragmentResultListener("punchAdded", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String punchTime = bundle.getString("punchTime");

                DummyContent.DummyItem dummyItem = new DummyContent.DummyItem();
                dummyItem.setId(UUID.randomUUID().toString());
                assert punchTime != null;
                try {
                    dummyItem.setContent(new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).parse(punchTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                timelineAdapter.addItem(dummyItem);

                Toast.makeText(getContext(), R.string.punch_added, Toast.LENGTH_SHORT).show();
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