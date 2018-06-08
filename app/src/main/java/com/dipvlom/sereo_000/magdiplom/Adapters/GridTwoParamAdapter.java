package com.dipvlom.sereo_000.magdiplom.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dipvlom.sereo_000.magdiplom.Models.User;
import com.dipvlom.sereo_000.magdiplom.R;

public class GridTwoParamAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    public GridTwoParamAdapter(Context c) {
        mContext = c;
        mInflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return User.getInstance().carnoElementsTwo.size();
    }

    @Override
    public Object getItem(int position) {
        return User.getInstance().carnoElementsTwo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return User.getInstance().carnoElementsTwo.get(position).realNumber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if ( view == null ) {
            view = mInflater.inflate(R.layout.grid_element, parent, false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(25);
        }
        view.setBackgroundColor(User.getInstance().carnoElementsTwo.get(position).color);
        ((TextView) view.findViewById(R.id.func_value)).setText(User.getInstance().carnoElementsTwo.get(position).value);


        return view;
    }
}
