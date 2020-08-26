package com.pdm.shifter.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.shifter.Adapter.TimelineAdapter;
import com.pdm.shifter.databinding.HistoryActivityBinding;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private HistoryActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HistoryActivityBinding.inflate(getLayoutInflater());

        RecyclerView recyclerView = binding.historyList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getBaseContext()));
        recyclerView.setAdapter(new TimelineAdapter(new ArrayList<>(), this.getBaseContext()));

        setContentView(binding.getRoot());
    }

}