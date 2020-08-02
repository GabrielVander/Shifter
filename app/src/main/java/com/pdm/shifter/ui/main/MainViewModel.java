package com.pdm.shifter.ui.main;

import androidx.lifecycle.ViewModel;

import com.pdm.shifter.dummy.DummyContent;

import java.util.List;

public class MainViewModel extends ViewModel {
    public List<DummyContent.DummyItem> history = DummyContent.ITEMS;

    public List<DummyContent.DummyItem> getHistory() {
        return history;
    }
}