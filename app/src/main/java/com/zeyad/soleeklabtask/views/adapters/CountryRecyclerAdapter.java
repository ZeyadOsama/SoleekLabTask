package com.zeyad.soleeklabtask.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zeyad.soleeklabtask.R;
import com.zeyad.soleeklabtask.model.Country;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.CountryViewHolder> {

    private ArrayList<Country> countries;
    private Context context;

    public CountryRecyclerAdapter(ArrayList<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.tvCountry.setText(countries.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return countries != null ? countries.size() : 0;
    }

    /**
     * View Holder
     */
    class CountryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_country_tv_name)
        TextView tvCountry;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
