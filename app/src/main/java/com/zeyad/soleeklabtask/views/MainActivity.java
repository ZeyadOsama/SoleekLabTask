package com.zeyad.soleeklabtask.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zeyad.soleeklabtask.R;
import com.zeyad.soleeklabtask.model.Country;
import com.zeyad.soleeklabtask.viewmodels.CountryViewModel;
import com.zeyad.soleeklabtask.views.adapters.CountryRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_rv_countries)
    RecyclerView rvCountries;

    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewModel();
    }

    private void initViewModel() {
        rvCountries.setLayoutManager(new LinearLayoutManager(this));
        rvCountries.setHasFixedSize(true);

        CountryViewModel countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryViewModel.get().observe(this,
                countries -> rvCountries.setAdapter(new CountryRecyclerAdapter(countries, MainActivity.this)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        authentication = FirebaseAuth.getInstance();
        FirebaseUser currentUser = authentication.getCurrentUser();
        if (currentUser == null)
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.activity_main_btn_sign_out)
    public void signOut() {
        authentication.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
