package com.zeyad.soleeklabtask.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zeyad.soleeklabtask.datamanagers.CountryDataManager;
import com.zeyad.soleeklabtask.model.Country;

import java.util.ArrayList;

public class CountryViewModel extends ViewModel {

    private CountryDataManager countryDataManager;

    public CountryViewModel() {
        countryDataManager = new CountryDataManager();
    }

    public LiveData<ArrayList<Country>> get() {
        return countryDataManager.load();
    }
}
