package com.pdm.shifter.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pdm.shifter.ViewModel.SearchShiftModel;
import com.pdm.shifter.databinding.SearchShiftFragmentBinding;

public class SearchShiftFragment extends Fragment {

    private SearchShiftModel mViewModel;
    private SearchShiftFragmentBinding binding;

    public static SearchShiftFragment newInstance() {
        return new SearchShiftFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SearchShiftModel.class);
        binding = SearchShiftFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

}