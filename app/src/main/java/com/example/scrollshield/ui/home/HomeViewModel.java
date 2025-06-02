package com.example.scrollshield.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scrollshield.ShortsData;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<ShortsData>> shortsDataList;

    public HomeViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        shortsDataList = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<List<ShortsData>> getShortsDataList() {
        return shortsDataList;
    }

    public void setShortsDataList(List<ShortsData> data) {
        shortsDataList.setValue(data);
    }

}