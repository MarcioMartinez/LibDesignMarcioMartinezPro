package com.dev.marciomartinez.libdesignmarciomartinez;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorBuscadorAutoCompleteTextView extends ArrayAdapter<Object> {

    Context context;
    int resource, textViewResourceId;
    List<Object> items, tempItems, suggestions;
    public AdaptadorBuscadorAutoCompleteTextView(@NonNull Context context, int resource, @NonNull List<Object> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = objects;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.autocomplete_item, parent, false);
        }
        Object names = items.get(position);
        if (names != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            if (lblName != null)
                lblName.setText(names.toString());
        }
        return view;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        //return super.getFilter();
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Object) resultValue).toString();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Object names : tempItems) {
                    if (names.toString().toLowerCase().toString().trim().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").trim().contains(constraint.toString().toLowerCase().toString().trim().toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").trim())) {
                        suggestions.add(names);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Object> filterList = (ArrayList<Object>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Object names : filterList) {
                    add(names);
                    notifyDataSetChanged();
                }
            }
        }
    };
}