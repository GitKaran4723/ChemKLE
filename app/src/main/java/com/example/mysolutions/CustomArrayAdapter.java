package com.example.mysolutions;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Compound> {
    private List<Compound> originalData;
    private List<Compound> filteredData;

    public CustomArrayAdapter(Context context, List<Compound> data) {
        super(context, android.R.layout.simple_dropdown_item_1line, data);
        this.originalData = new ArrayList<>(data);
        this.filteredData = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Compound getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Compound> filteredList = new ArrayList<>();

                if (constraint != null && constraint.length() > 0) {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Compound compound : originalData) {
                        if (compound.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(compound);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredData = (List<Compound>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
