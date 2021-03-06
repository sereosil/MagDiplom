package com.dipvlom.sereo_000.magdiplom.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dipvlom.sereo_000.magdiplom.Models.User;
import com.dipvlom.sereo_000.magdiplom.R;

public class GridThreeParamAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    public GridThreeParamAdapter(Context c) {
        mContext = c;
        mInflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return User.getInstance().carnoElementsThree.size();
    }

    @Override
    public Object getItem(int position) {
        return User.getInstance().carnoElementsThree.get(position);
    }

    @Override
    public long getItemId(int position) {
        return User.getInstance().carnoElementsThree.get(position).realNumber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if ( view == null ) {
            view = mInflater.inflate(R.layout.grid_element, parent, false);
        }
        view.setBackgroundColor(User.getInstance().carnoElementsThree.get(position).color);
        ((TextView) view.findViewById(R.id.func_value)).setText(User.getInstance().carnoElementsThree.get(position).value);
        ((TextView) view.findViewById(R.id.func_value)).setTextColor(Color.BLACK);


        return view;
    }
}
