package com.zeyad.soleeklabtask.datamanagers;

import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zeyad.soleeklabtask.R;
import com.zeyad.soleeklabtask.model.Country;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.zeyad.soleeklabtask.utils.LogMessages.FAILED;
import static com.zeyad.soleeklabtask.utils.LogMessages.LOADING;
import static com.zeyad.soleeklabtask.utils.LogMessages.SUCCEEDED;

public class CountryDataManager {

    /**
     * Class tag
     */
    private String TAG = CountryDataManager.class.getSimpleName();

    private MutableLiveData<ArrayList<Country>> countriesList;

    public CountryDataManager() {
        countriesList = new MutableLiveData<>();
    }

    /**
     * loads countries from given API
     *
     * @return list of countries
     */
    public MutableLiveData<ArrayList<Country>> load() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountriesAPI countriesAPI = retrofit.create(CountriesAPI.class);
        Call<ArrayList<Country>> call = countriesAPI.getCountriesNames();
        call.enqueue(new Callback<ArrayList<Country>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Country>> call, @NonNull Response<ArrayList<Country>> response) {
                Log.d(TAG, LOADING + SUCCEEDED);
                countriesList.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Country>> call, @NonNull Throwable throwable) {
                Log.e(TAG, LOADING + FAILED + throwable.toString());
            }
        });
        return countriesList;
    }

    /**
     * Countries API Interface
     */
    private interface CountriesAPI {

        @GET("all?fields=name")
        Call<ArrayList<Country>> getCountriesNames();
    }
}
