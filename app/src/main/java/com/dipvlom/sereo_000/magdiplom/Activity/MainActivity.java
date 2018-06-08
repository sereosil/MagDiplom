package com.dipvlom.sereo_000.magdiplom.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dipvlom.sereo_000.magdiplom.Models.CarnoElement;
import com.dipvlom.sereo_000.magdiplom.Models.User;
import com.dipvlom.sereo_000.magdiplom.R;

public class MainActivity extends AppCompatActivity {
    public RadioGroup paramsCount;
    public RadioButton twoParams;
    public RadioButton threeParams;
    public RadioButton fourParams;
    public RadioButton sdnfMethod;
    public RadioButton sknfMethod;
    public RadioGroup methodType;
    public EditText f1;
    public EditText f2;
    public EditText f3;
    public EditText f4;
    public EditText f5;
    public EditText f6;
    public EditText f7;
    public EditText f8;
    public EditText f9;
    public EditText f10;
    public EditText f11;
    public EditText f12;
    public EditText f13;
    public EditText f14;
    public EditText f15;
    public EditText f16;
    public Button goNextBtn;
    public boolean isSdnf = true;
    public int paramsCountInt = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        User.createUser();
        setContentView(R.layout.main_activity);
        f1 = findViewById(R.id.f1_param);
        f2 = findViewById(R.id.f2_param);
        f3 = findViewById(R.id.f3_param);
        f4 = findViewById(R.id.f4_param);
        f5 = findViewById(R.id.f5_param);
        f6 = findViewById(R.id.f6_param);
        f7 = findViewById(R.id.f7_param);
        f8 = findViewById(R.id.f8_param);
        f9 = findViewById(R.id.f9_param);
        f10 = findViewById(R.id.f10_param);
        f11 = findViewById(R.id.f11_param);
        f12 = findViewById(R.id.f12_param);
        f13 = findViewById(R.id.f13_param);
        f14 = findViewById(R.id.f14_param);
        f15 = findViewById(R.id.f15_param);
        f16 = findViewById(R.id.f16_param);
        paramsCount = findViewById(R.id.radio_param_count);
        methodType = findViewById(R.id.method_radio_group);
        goNextBtn = findViewById(R.id.go_next_btn);
        twoParams = findViewById(R.id.radio_two_params);
        threeParams = findViewById(R.id.radio_three_params);
        fourParams = findViewById(R.id.radio_four_params);
        sdnfMethod = findViewById(R.id.sdnf_radio);
        sknfMethod = findViewById(R.id.sknf_radio);
        super.onCreate(savedInstanceState);
        twoParams.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    paramsCountInt = 2;
                    findViewById(R.id.f_for_four).setVisibility(View.GONE);
                    findViewById(R.id.f_for_three).setVisibility(View.GONE);
                }
            }
        });
        threeParams.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    paramsCountInt = 3;
                    findViewById(R.id.f_for_four).setVisibility(View.GONE);
                    findViewById(R.id.f_for_three).setVisibility(View.VISIBLE);
                }
            }
        });
        fourParams.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    paramsCountInt = 4;
                    findViewById(R.id.f_for_four).setVisibility(View.VISIBLE);
                    findViewById(R.id.f_for_three).setVisibility(View.VISIBLE);
                }
            }
        });
        sdnfMethod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isSdnf = true;
                }
            }
        });
        sknfMethod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isSdnf = false;
                }
            }
        });
        goNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.getInstance().method = isSdnf;
                User.getInstance().paramsCount = paramsCountInt;
                switch (paramsCountInt){
                    case 2:
                        User.getInstance().istTablTwo[0][2] = Integer.parseInt(f1.getText().toString());
                        User.getInstance().istTablTwo[1][2] = Integer.parseInt(f2.getText().toString());
                        User.getInstance().istTablTwo[2][2] = Integer.parseInt(f3.getText().toString());
                        User.getInstance().istTablTwo[3][2] = Integer.parseInt(f4.getText().toString());
                        CarnoElement carnoElement = new CarnoElement();
                        carnoElement.color = User.getInstance().colors[0];
                        carnoElement.value = " ";
                        carnoElement.realNumber = -1;
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.value = "X1";
                        carnoElement.realNumber = -1;
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.value = "!X1";
                        carnoElement.realNumber = -1;
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.value = "X2";
                        carnoElement.realNumber = -1;
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.realNumber = 3;
                        carnoElement.value = f4.getText().toString();
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.realNumber = 1;
                        carnoElement.value = f2.getText().toString();
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.realNumber = -1;
                        carnoElement.value = "!X2";
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.realNumber = 2;
                        carnoElement.value = f3.getText().toString();
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        carnoElement.realNumber = 0;
                        carnoElement.value = f1.getText().toString();
                        User.getInstance().carnoElementsTwo.add(carnoElement);
                        break;
                    case 3:
                        User.getInstance().istTablThree[0][3] = Integer.parseInt(f1.getText().toString());
                        User.getInstance().istTablThree[1][3] = Integer.parseInt(f2.getText().toString());
                        User.getInstance().istTablThree[2][3] = Integer.parseInt(f3.getText().toString());
                        User.getInstance().istTablThree[3][3] = Integer.parseInt(f4.getText().toString());
                        User.getInstance().istTablThree[4][3] = Integer.parseInt(f5.getText().toString());
                        User.getInstance().istTablThree[5][3] = Integer.parseInt(f6.getText().toString());
                        User.getInstance().istTablThree[6][3] = Integer.parseInt(f7.getText().toString());
                        User.getInstance().istTablThree[7][3] = Integer.parseInt(f8.getText().toString());
                        CarnoElement carnoElementThree = new CarnoElement();
                        carnoElementThree.color = User.getInstance().colors[0];
                        carnoElementThree.value = " ";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.value = "X1";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.value = "X1";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.value = "!X1";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = -1;
                        carnoElementThree.value = "!X1";
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = -1;
                        carnoElementThree.value = "X2";
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 6;
                        carnoElementThree.value = f7.getText().toString();;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 7;
                        carnoElementThree.value = f8.getText().toString();
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 3;
                        carnoElementThree.value = f4.getText().toString();
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 2;
                        carnoElementThree.value = f3.getText().toString();
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = -1;
                        carnoElementThree.value = "!X2";
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 4;
                        carnoElementThree.value = f5.getText().toString();;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 5;
                        carnoElementThree.value = f6.getText().toString();
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 1;
                        carnoElementThree.value = f2.getText().toString();
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = 0;
                        carnoElementThree.value = f1.getText().toString();
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.value = " ";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.value = "!X3";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.value = "X3";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.value = "X3";
                        carnoElementThree.realNumber = -1;
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        carnoElementThree.realNumber = -1;
                        carnoElementThree.value = "!X3";
                        User.getInstance().carnoElementsThree.add(carnoElementThree);
                        break;
                    case 4:
                        User.getInstance().istTablFour[0][4] = Integer.parseInt(f1.getText().toString());
                        User.getInstance().istTablFour[1][4] = Integer.parseInt(f2.getText().toString());
                        User.getInstance().istTablFour[2][4] = Integer.parseInt(f3.getText().toString());
                        User.getInstance().istTablFour[3][4] = Integer.parseInt(f4.getText().toString());
                        User.getInstance().istTablFour[4][4] = Integer.parseInt(f5.getText().toString());
                        User.getInstance().istTablFour[5][4] = Integer.parseInt(f6.getText().toString());
                        User.getInstance().istTablFour[6][4] = Integer.parseInt(f7.getText().toString());
                        User.getInstance().istTablFour[7][4] = Integer.parseInt(f8.getText().toString());
                        User.getInstance().istTablFour[8][4] = Integer.parseInt(f9.getText().toString());
                        User.getInstance().istTablFour[9][4] = Integer.parseInt(f10.getText().toString());
                        User.getInstance().istTablFour[10][4] = Integer.parseInt(f11.getText().toString());
                        User.getInstance().istTablFour[11][4] = Integer.parseInt(f12.getText().toString());
                        User.getInstance().istTablFour[12][4] = Integer.parseInt(f13.getText().toString());
                        User.getInstance().istTablFour[13][4] = Integer.parseInt(f14.getText().toString());
                        User.getInstance().istTablFour[14][4] = Integer.parseInt(f15.getText().toString());
                        User.getInstance().istTablFour[15][4] = Integer.parseInt(f16.getText().toString());
                        CarnoElement carnoElementFour = new CarnoElement();
                        carnoElementFour.color = User.getInstance().colors[0];
                        carnoElementFour.value = " ";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X1";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X1";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X1";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X1";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = " ";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X2";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f13.getText().toString();
                        carnoElementFour.realNumber = 12;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f14.getText().toString();
                        carnoElementFour.realNumber = 13;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f6.getText().toString();
                        carnoElementFour.realNumber = 5;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f5.getText().toString();
                        carnoElementFour.realNumber = 4;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X3";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X2";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f15.getText().toString();
                        carnoElementFour.realNumber = 14;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f16.getText().toString();
                        carnoElementFour.realNumber = 15;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f8.getText().toString();
                        carnoElementFour.realNumber = 7;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f7.getText().toString();
                        carnoElementFour.realNumber = 8;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X3";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X2";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f11.getText().toString();
                        carnoElementFour.realNumber = 10;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f12.getText().toString();
                        carnoElementFour.realNumber = 11;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f4.getText().toString();
                        carnoElementFour.realNumber = 3;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f3.getText().toString();
                        carnoElementFour.realNumber = 2;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X3";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X2";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f9.getText().toString();
                        carnoElementFour.realNumber = 8;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f10.getText().toString();
                        carnoElementFour.realNumber = 9;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f2.getText().toString();
                        carnoElementFour.realNumber = 1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = f1.getText().toString();
                        carnoElementFour.realNumber = 0;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X3";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = " ";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X4";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X4";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "X4";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = "!X4";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);
                        carnoElementFour.value = " ";
                        carnoElementFour.realNumber = -1;
                        User.getInstance().carnoElementsFour.add(carnoElementFour);

                        break;
                }
                Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
