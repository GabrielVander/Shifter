package com.pdm.shifter.ViewModel;

import androidx.lifecycle.ViewModel;

import com.pdm.shifter.dummy.DummyContent;

import java.util.List;

public class MainViewModel extends ViewModel {
    private List<DummyContent.DummyItem> history = DummyContent.ITEMS;

    public List<DummyContent.DummyItem> getHistory() {
        return history;
    }

}