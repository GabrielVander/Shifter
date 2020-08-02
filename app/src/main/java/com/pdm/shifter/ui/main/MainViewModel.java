package com.pdm.shifter.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.pdm.shifter.R;
import com.pdm.shifter.dummy.DummyContent;

import java.util.List;

public class MainViewModel extends ViewModel {
    private List<DummyContent.DummyItem> history = DummyContent.ITEMS;

    public List<DummyContent.DummyItem> getHistory() {
        return history;
    }

}