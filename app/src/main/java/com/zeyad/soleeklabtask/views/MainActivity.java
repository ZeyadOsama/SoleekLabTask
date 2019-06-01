package com.zeyad.soleeklabtask.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zeyad.soleeklabtask.R;
import com.zeyad.soleeklabtask.viewmodels.CountryViewModel;
import com.zeyad.soleeklabtask.views.adapters.CountryRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /**
     * View binding
     */
    @BindView(R.id.activity_main_rv_countries)
    RecyclerView rvCountries;

    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
        initViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        authentication = FirebaseAuth.getInstance();
        FirebaseUser currentUser = authentication.getCurrentUser();
        if (currentUser == null)
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void initViews() {
        rvCountries.setLayoutManager(new LinearLayoutManager(this));
        rvCountries.setHasFixedSize(true);
    }

    private void initViewModel() {
        CountryViewModel countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryViewModel.get().observe(this,
                countries -> rvCountries.setAdapter(new CountryRecyclerAdapter(countries, MainActivity.this)));
    }

    /**
     * sign out from current authenticated user
     */
    @OnClick(R.id.activity_main_btn_sign_out)
    public void signOut() {
        authentication.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
