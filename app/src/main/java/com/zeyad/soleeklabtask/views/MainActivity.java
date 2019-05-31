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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCountries;
//    private CountriesViewModel countriesViewModel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        init();
    }

//    private void init() {
//        rvCountries = findViewById(R.id.rv_countries);
//        rvCountries.setLayoutManager(new LinearLayoutManager(this));
//        rvCountries.setHasFixedSize(true);
//
//        countriesViewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
//        countriesViewModel.getCountries().observe(this, new Observer<ArrayList<Country>>() {
//            @Override
//            public void onChanged(ArrayList<Country> countries) {
//                rvCountries.setAdapter(new CountriesRecyclerAdapter(countries, HomeActivity.this));
//
//            }
//        });
//    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null)
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.action_logout) {
//            mAuth.signOut();
//            startActivity(new Intent(this, LoginActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
