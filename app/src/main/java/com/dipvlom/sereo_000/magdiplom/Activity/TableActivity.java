package com.dipvlom.sereo_000.magdiplom.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dipvlom.sereo_000.magdiplom.Models.TableElement;
import com.dipvlom.sereo_000.magdiplom.Models.User;
import com.dipvlom.sereo_000.magdiplom.R;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    private TableLayout mTableLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);
        mTableLayout = (TableLayout) findViewById(R.id.tableInvoices);
        mTableLayout.setStretchAllColumns(true);
        int rows = User.getInstance().tableElements.get(User.getInstance().tableElements.size()-1).row+1;
        mTableLayout.removeAllViews();
        for(int i=0;i<rows;i++){
            TableRow tr = new TableRow(this);
            for(int j=0;j<User.getInstance().tableElements.size();j++) {
                if (User.getInstance().tableElements.get(j).row == i) {
                    final LinearLayout layCustomer = new LinearLayout(this);
                    layCustomer.setOrientation(LinearLayout.VERTICAL);
                    layCustomer.setPadding(10, 10, 10, 10);
                    layCustomer.setBackgroundColor(Color.parseColor("#f8f8f8"));

                    final TextView tv3 = new TextView(this);

                        tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv3.setPadding(5, 5, 5, 5);
                    tv3.setGravity(Gravity.TOP);
                    tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    tv3.setTextColor(Color.parseColor("#000000"));
                    tv3.setText(User.getInstance().tableElements.get(j).name);
                    layCustomer.addView(tv3);
                        final TextView tv3b = new TextView(this);
                        tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv3b.setGravity(Gravity.RIGHT);
                        tv3b.setPadding(5, 5, 5, 5);
                        tv3b.setTextColor(Color.parseColor("#aaaaaa"));
                        tv3b.setBackgroundColor(Color.parseColor("#f8f8f8"));
                        tv3b.setText("Номер: "+User.getInstance().tableElements.get(j).id+"\n"+User.getInstance().tableElements.get(j).parents);
                        layCustomer.addView(tv3b);
                        tr.addView(layCustomer);
                }
            }
            mTableLayout.addView(tr);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
