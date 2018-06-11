package com.dipvlom.sereo_000.magdiplom.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dipvlom.sereo_000.magdiplom.Adapters.GridFourParamAdapter;
import com.dipvlom.sereo_000.magdiplom.Adapters.GridThreeParamAdapter;
import com.dipvlom.sereo_000.magdiplom.Adapters.GridTwoParamAdapter;
import com.dipvlom.sereo_000.magdiplom.Models.IstElement;
import com.dipvlom.sereo_000.magdiplom.Models.TableElement;
import com.dipvlom.sereo_000.magdiplom.Models.User;
import com.dipvlom.sereo_000.magdiplom.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    public int paramsCount;
    public boolean isSdnf;
    public GridView karnoKard;
    public Button goBackBtn;
    public TextView resultView;
    public GridTwoParamAdapter twoParamAdapter;
    public GridThreeParamAdapter threeParamAdapter;
    public GridFourParamAdapter fourParamAdapter;
    public TextView showDiagramBtn;
    public ArrayList<TableElement> tableElements = new ArrayList<>();
    public ArrayList<IstElement> istTablTwoParams = new ArrayList<>();
    public ArrayList<IstElement> istTablThreeParams = new ArrayList<>();
    public ArrayList<IstElement> istTablFourParams = new ArrayList<>();
    public int counter = 0;
    public int countOfImplic = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.karno_result);
        karnoKard = findViewById(R.id.karno_grid);
        goBackBtn = findViewById(R.id.go_back_btn);
        resultView = findViewById(R.id.func_result);
        showDiagramBtn = findViewById(R.id.open_diagram_btn);
        showDiagramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,TableActivity.class);
                startActivity(intent);
            }
        });
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        if(User.getInstance().paramsCount==2){
            karnoKard.setNumColumns(3);
            twoParamAdapter = new GridTwoParamAdapter(getApplicationContext());
            karnoKard.setAdapter(twoParamAdapter);
            methodForTwoParams();
        }
        if(User.getInstance().paramsCount==3){
            karnoKard.setNumColumns(5);
            threeParamAdapter = new GridThreeParamAdapter(getApplicationContext());
            karnoKard.setAdapter(threeParamAdapter);
            methodForThreeParams();
        }
        if(User.getInstance().paramsCount==4){
            karnoKard.setNumColumns(6);
            fourParamAdapter = new GridFourParamAdapter(getApplicationContext());
            karnoKard.setAdapter(fourParamAdapter);
            methodForFourParams();
        }
        super.onCreate(savedInstanceState);
    }
    public ArrayList<IstElement> makeIterTwoParams(ArrayList<IstElement> implic){
        ArrayList<IstElement> newImplic = new ArrayList<>();
        IstElement tempElement = new IstElement();
        for(int i=0;i<implic.size();i++){
            for (int j=1;j<implic.size()-1;j++){
                if(implic.get(i).element[3]+1==implic.get(j).element[3]){
                    if (implic.get(i).element[0]==implic.get(j).element[0]){
                        tempElement.element[0]=implic.get(i).element[0];
                        tempElement.element[1]=2;
                        tempElement.element[2]=1;
                        tempElement.element[3]=implic.get(i).element[0];
                        implic.get(i).element[4]++;
                        implic.get(j).element[4]++;
                        countOfImplic++;
                        newImplic.add(tempElement);
                    }
                    else {
                        if (implic.get(i).element[1]==implic.get(j).element[1]){
                            tempElement.element[0]=2;
                            tempElement.element[1]=implic.get(i).element[1];
                            tempElement.element[2]=1;
                            tempElement.element[3]=implic.get(i).element[1];
                            implic.get(i).element[4]++;
                            implic.get(j).element[4]++;
                            countOfImplic++;
                            newImplic.add(tempElement);
                        }
                    }
                }
            }
        }
        for(int i=0;i<implic.size();i++){
            if(implic.get(i).element[4]<1){
                tempElement.element=implic.get(i).element;
                newImplic.add(tempElement);
            }

        }
        int row = User.getInstance().tableElements.get(User.getInstance().tableElements.size()-1).row+1;
        if(countOfImplic>0){
            getTableElementsTwoParams(newImplic,row);
            return newImplic;
        }
        else
            return implic;
    }
    public ArrayList<IstElement> makeIterThreeParams(ArrayList<IstElement> implic){
        ArrayList<IstElement> newImplic = new ArrayList<>();
        IstElement tempElement = new IstElement();
        for(int i=0;i<implic.size();i++){
            for (int j=1;j<implic.size()-1;j++){
                if(implic.get(i).element[4]+1==implic.get(j).element[4]){
                    if (implic.get(i).element[0]==implic.get(j).element[0]&&implic.get(i).element[1]==implic.get(j).element[1]){
                        tempElement.element[0]=implic.get(i).element[0];
                        tempElement.element[1]=implic.get(i).element[1];
                        tempElement.element[2]=2;
                        tempElement.element[3]=1;
                        implic.get(i).element[5]++;
                        implic.get(j).element[5]++;
                        tempElement.element[4]=implic.get(i).element[0]+implic.get(i).element[1];
                        countOfImplic++;
                        newImplic.add(tempElement);
                    }
                    else {
                        if (implic.get(i).element[1]==implic.get(j).element[1]&&implic.get(i).element[2]==implic.get(j).element[2]){
                            tempElement.element[0]=2;
                            tempElement.element[1]=implic.get(i).element[1];
                            tempElement.element[2]=implic.get(i).element[2];
                            tempElement.element[3]=1;
                            implic.get(i).element[5]++;
                            implic.get(j).element[5]++;
                            tempElement.element[4]=implic.get(i).element[1]+implic.get(i).element[2];
                            countOfImplic++;
                            newImplic.add(tempElement);
                        }
                        else{
                            if (implic.get(i).element[0]==implic.get(j).element[0]&&implic.get(i).element[2]==implic.get(j).element[2]){
                                tempElement.element[1]=2;
                                tempElement.element[0]=implic.get(i).element[0];
                                tempElement.element[2]=implic.get(i).element[2];
                                tempElement.element[3]=1;
                                implic.get(i).element[5]++;
                                implic.get(j).element[5]++;
                                tempElement.element[4]=implic.get(i).element[0]+implic.get(i).element[2];
                                countOfImplic++;
                                newImplic.add(tempElement);
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<implic.size();i++){
            if(implic.get(i).element[5]<1){
                tempElement.element=implic.get(i).element;
                newImplic.add(tempElement);
               // countOfImplic++;
            }
        }
        int row = User.getInstance().tableElements.get(User.getInstance().tableElements.size()-1).row+1;
        if(countOfImplic>0){
            getTableElementsThreeParams(newImplic,row);
            return newImplic;
        }
        else
            return implic;
    }
    public ArrayList<IstElement> makeIterFourParams(ArrayList<IstElement> implic){
        ArrayList<IstElement> newImplic = new ArrayList<>();
        IstElement tempElement = new IstElement();
        for(int i=0;i<implic.size();i++){
            for (int j=1;j<implic.size()-1;j++){
                if(implic.get(i).element[5]+1==implic.get(j).element[5]){
                    if (implic.get(i).element[0]==implic.get(j).element[0]&&implic.get(i).element[1]==implic.get(j).element[1]&&implic.get(i).element[2]==implic.get(j).element[2]){
                        tempElement.element[0]=implic.get(i).element[0];
                        tempElement.element[1]=implic.get(i).element[1];
                        tempElement.element[2]=implic.get(i).element[2];
                        tempElement.element[3]=2;
                        tempElement.element[4]=1;
                        implic.get(i).element[6]++;
                        implic.get(j).element[6]++;
                        tempElement.element[5]=implic.get(i).element[0]+implic.get(i).element[1]+implic.get(i).element[2];
                        countOfImplic++;
                        newImplic.add(tempElement);
                    }
                    else {
                        if (implic.get(i).element[1]==implic.get(j).element[1]&&implic.get(i).element[2]==implic.get(j).element[2]&&implic.get(i).element[3]==implic.get(j).element[3]){
                            tempElement.element[0]=2;
                            tempElement.element[1]=implic.get(i).element[1];
                            tempElement.element[2]=implic.get(i).element[2];
                            tempElement.element[3]=implic.get(i).element[3];
                            tempElement.element[4]=1;
                            implic.get(i).element[6]++;
                            implic.get(j).element[6]++;
                            tempElement.element[5]=implic.get(i).element[1]+implic.get(i).element[2]+implic.get(i).element[3];
                            countOfImplic++;
                            newImplic.add(tempElement);
                        }
                        else{
                            if (implic.get(i).element[0]==implic.get(j).element[0]&&implic.get(i).element[2]==implic.get(j).element[2]&&implic.get(i).element[3]==implic.get(j).element[3]){
                                tempElement.element[1]=2;
                                tempElement.element[0]=implic.get(i).element[0];
                                tempElement.element[2]=implic.get(i).element[2];
                                tempElement.element[3]=implic.get(i).element[3];
                                tempElement.element[4]=1;
                                implic.get(i).element[6]++;
                                implic.get(j).element[6]++;
                                tempElement.element[5]=implic.get(i).element[0]+implic.get(i).element[2]+implic.get(i).element[3];
                                countOfImplic++;
                                newImplic.add(tempElement);
                            }
                            else {
                                if (implic.get(i).element[0]==implic.get(j).element[0]&&implic.get(i).element[1]==implic.get(j).element[1]&&implic.get(i).element[3]==implic.get(j).element[3]){
                                    tempElement.element[2]=2;
                                    tempElement.element[0]=implic.get(i).element[0];
                                    tempElement.element[1]=implic.get(i).element[1];
                                    tempElement.element[3]=implic.get(i).element[3];
                                    tempElement.element[4]=1;
                                    implic.get(i).element[6]++;
                                    implic.get(j).element[6]++;
                                    tempElement.element[5]=implic.get(i).element[0]+implic.get(i).element[1]+implic.get(i).element[3];
                                    countOfImplic++;
                                    newImplic.add(tempElement);
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<implic.size();i++){
            if(implic.get(i).element[6]<1){
                tempElement.element=implic.get(i).element;
                newImplic.add(tempElement);
                //countOfImplic++;
            }
        }
        int row = User.getInstance().tableElements.get(User.getInstance().tableElements.size()-1).row+1;
        if(countOfImplic>0){
            getTableElementsFourParams(newImplic,row);
            return newImplic;
        }
        else
            return implic;
    }
    public ArrayList<IstElement> sortMassTwoParams(ArrayList<IstElement> imnplic){
        for (int i=0;i<imnplic.size();i++){
            for( int j=1;j<imnplic.size()-1;j++){
                if(imnplic.get(i).element[3]>imnplic.get(j).element[3]){
                    int[] temp = {imnplic.get(i).element[0],imnplic.get(i).element[1],imnplic.get(i).element[2],imnplic.get(i).element[3],imnplic.get(i).element[4]};
                    imnplic.get(i).element[0] = imnplic.get(j).element[0];
                    imnplic.get(i).element[1] = imnplic.get(j).element[1];
                    imnplic.get(i).element[2] = imnplic.get(j).element[2];
                    imnplic.get(i).element[3] = imnplic.get(j).element[3];
                    imnplic.get(i).element[4] = imnplic.get(j).element[4];
                    imnplic.get(j).element[0] = temp[0];
                    imnplic.get(j).element[1] = temp[1];
                    imnplic.get(j).element[2] = temp[2];
                    imnplic.get(j).element[3] = temp[3];
                    imnplic.get(j).element[4] = temp[4];
                }
            }
        }
        return imnplic;
    }
    public ArrayList<IstElement> sortMassThreeParams(ArrayList<IstElement> implic){
        for (int i=0;i<implic.size();i++){
            for( int j=1;j<implic.size()-1;j++){
                if(implic.get(i).element[4]>implic.get(j).element[4]){
                    int[] temp = {implic.get(i).element[0],implic.get(i).element[1],implic.get(i).element[2],implic.get(i).element[3],implic.get(i).element[4],implic.get(i).element[5]};
                    implic.get(i).element[0] = implic.get(j).element[0];
                    implic.get(i).element[1] = implic.get(j).element[1];
                    implic.get(i).element[2] = implic.get(j).element[2];
                    implic.get(i).element[3] = implic.get(j).element[3];
                    implic.get(i).element[4] = implic.get(j).element[4];
                    implic.get(i).element[5] = implic.get(j).element[5];
                    implic.get(j).element[0] = temp[0];
                    implic.get(j).element[1] = temp[1];
                    implic.get(j).element[2] = temp[2];
                    implic.get(j).element[3] = temp[3];
                    implic.get(j).element[4] = temp[4];
                    implic.get(j).element[5] = temp[5];
                }
            }
        }
        return implic;
    }
    public ArrayList<IstElement> sortMassFourParams (ArrayList<IstElement> imnplic){
        for (int i=0;i<imnplic.size();i++){
            for( int j=1;j<imnplic.size()-1;j++){
                if(imnplic.get(i).element[5]>imnplic.get(j).element[5]){
                    int[] temp = {imnplic.get(i).element[0],imnplic.get(i).element[1],imnplic.get(i).element[2],imnplic.get(i).element[3],imnplic.get(i).element[4],imnplic.get(i).element[5],imnplic.get(i).element[6]};
                    imnplic.get(i).element[0] = imnplic.get(j).element[0];
                    imnplic.get(i).element[1] = imnplic.get(j).element[1];
                    imnplic.get(i).element[2] = imnplic.get(j).element[2];
                    imnplic.get(i).element[3] = imnplic.get(j).element[3];
                    imnplic.get(i).element[4] = imnplic.get(j).element[4];
                    imnplic.get(i).element[5] = imnplic.get(j).element[5];
                    imnplic.get(i).element[6] = imnplic.get(j).element[6];
                    imnplic.get(j).element[0] = temp[0];
                    imnplic.get(j).element[1] = temp[1];
                    imnplic.get(j).element[2] = temp[2];
                    imnplic.get(j).element[3] = temp[3];
                    imnplic.get(j).element[4] = temp[4];
                    imnplic.get(j).element[5] = temp[5];
                    imnplic.get(j).element[6] = temp[6];
                }
            }
        }
        return imnplic;
    }
    public void methodForTwoParams(){
        for(int i=0;i<4;i++){
            if(User.getInstance().istTablTwo[i][2]==1){
                IstElement tempElement = new IstElement();
                tempElement.element[2]=User.getInstance().istTablTwo[i][2];
                for(int j=0;j<2;j++){
                    if(User.getInstance().istTablTwo[i][j]==1){
                        counter++;
                    }
                }
                tempElement.element[0]=User.getInstance().istTablTwo[i][0];
                tempElement.element[1]=User.getInstance().istTablTwo[i][1];
                tempElement.element[3]=counter;
                tempElement.element[4]=0;
                istTablTwoParams.add(tempElement);

            }
            counter=0;
        }
        getTableElementsTwoParams(istTablTwoParams,0);
        istTablTwoParams = sortMassTwoParams(istTablTwoParams);
        istTablTwoParams = makeIterTwoParams(istTablTwoParams);
        if(countOfImplic>1) {
            countOfImplic = 0;
            istTablTwoParams = sortMassTwoParams(istTablTwoParams);
            istTablTwoParams = makeIterTwoParams(istTablTwoParams);
            if(countOfImplic>1){
                countOfImplic=0;
                istTablTwoParams = sortMassTwoParams(istTablTwoParams);
                istTablTwoParams = makeIterTwoParams(istTablTwoParams);
                getResultTwoParams(istTablTwoParams);
            }
            else {
                getResultTwoParams(istTablTwoParams);
            }
        }
        else {

            getResultTwoParams(istTablTwoParams);
        }
    }
    public void methodForThreeParams(){
        for(int i=0;i<8;i++){
            if(User.getInstance().istTablThree[i][3]==1){
                IstElement tempElement = new IstElement();
                tempElement.element[3]=User.getInstance().istTablThree[i][3];
                for(int j=0;j<3;j++){
                    if(User.getInstance().istTablThree[i][j]==1){
                        counter++;
                    }
                }
                tempElement.element[0]=User.getInstance().istTablThree[i][0];
                tempElement.element[1]=User.getInstance().istTablThree[i][1];
                tempElement.element[2]=User.getInstance().istTablThree[i][2];
                tempElement.element[4]=counter;
                tempElement.element[5]=0;
                istTablThreeParams.add(tempElement);
            }
            counter=0;
        }
        getTableElementsThreeParams(istTablThreeParams,0);
        istTablThreeParams = sortMassThreeParams(istTablThreeParams);
        istTablThreeParams = makeIterThreeParams(istTablThreeParams);
        if(countOfImplic>1) {
            countOfImplic = 0;
            istTablThreeParams = sortMassThreeParams(istTablThreeParams);
            istTablThreeParams = makeIterThreeParams(istTablThreeParams);
            if(countOfImplic>1){
                countOfImplic=0;
                istTablThreeParams = sortMassThreeParams(istTablThreeParams);
                istTablThreeParams = makeIterThreeParams(istTablThreeParams);
                if (countOfImplic>1){
                    countOfImplic=0;
                    istTablThreeParams = sortMassThreeParams(istTablThreeParams);
                    istTablThreeParams = makeIterThreeParams(istTablThreeParams);
                    getResultThreeParams(istTablThreeParams);
                }
                else {
                    getResultThreeParams(istTablThreeParams);
                }
            }
            else {
                getResultThreeParams(istTablThreeParams);
            }
        }
        else {

            getResultThreeParams(istTablThreeParams);
        }
    }
    public void methodForFourParams(){
        for(int i=0;i<16;i++){
            if(User.getInstance().istTablFour[i][4]==1){
                IstElement tempElement = new IstElement();
                tempElement.element[4]=User.getInstance().istTablFour[i][4];
                for(int j=0;j<4;j++){
                    if(User.getInstance().istTablFour[i][j]==1){
                        counter++;
                    }
                }
                tempElement.element[0]=User.getInstance().istTablFour[i][0];
                tempElement.element[1]=User.getInstance().istTablFour[i][1];
                tempElement.element[2]=User.getInstance().istTablFour[i][2];
                tempElement.element[3]=User.getInstance().istTablFour[i][3];
                tempElement.element[5]=counter;
                tempElement.element[6]=0;
                istTablFourParams.add(tempElement);

            }
            counter=0;
        }

        getTableElementsFourParams(istTablFourParams,0);
        istTablFourParams = sortMassFourParams(istTablFourParams);
        istTablFourParams = makeIterFourParams(istTablFourParams);
        if(countOfImplic>1) {
            countOfImplic = 0;
            istTablFourParams = sortMassFourParams(istTablFourParams);
            istTablFourParams = makeIterFourParams(istTablFourParams);
            if(countOfImplic>1){
                countOfImplic=0;
                istTablFourParams = sortMassFourParams(istTablFourParams);
                istTablFourParams = makeIterFourParams(istTablFourParams);
                if (countOfImplic>1){
                    countOfImplic=0;
                    istTablFourParams = sortMassFourParams(istTablFourParams);
                    istTablFourParams = makeIterFourParams(istTablFourParams);
                    if(countOfImplic>1){
                        countOfImplic=0;
                        istTablFourParams = sortMassFourParams(istTablFourParams);
                        istTablFourParams = makeIterFourParams(istTablFourParams);
                        if(countOfImplic>1){
                            countOfImplic=0;
                            istTablFourParams = sortMassFourParams(istTablFourParams);
                            istTablFourParams = makeIterFourParams(istTablFourParams);
                            if(countOfImplic>1){
                                countOfImplic=0;
                                istTablFourParams = sortMassFourParams(istTablFourParams);
                                istTablFourParams = makeIterFourParams(istTablFourParams);
                                getResultFourParams(istTablThreeParams);
                            }
                            else {
                                getResultFourParams(istTablThreeParams);
                            }
                        }
                        else {
                            getResultFourParams(istTablThreeParams);
                        }
                    }
                    else {
                        getResultFourParams(istTablThreeParams);
                    }
                }
                else {
                    getResultFourParams(istTablThreeParams);
                }
            }
            else {
                getResultFourParams(istTablThreeParams);
            }
        }
        else {

            getResultFourParams(istTablThreeParams);
        }
    }
    public void getResultTwoParams(ArrayList<IstElement> implic){
       // int[][] tempImplic = new int[4][];
        String[] resultStrings = new String[implic.size()];
        int tempCounter = 0;
        String tempString="";
        for(int i = 0; i< implic.size(); i++){
            if(implic.get(i).element[0]==0){
                tempString+="!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if(implic.get(i).element[0]==1){
                tempString+="X1";
               // resultView.setText(resultView.getText().toString()+"X1");
            }
            if(implic.get(i).element[1]==0){
                tempString+="!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[1]==1){
                tempString+="X2";
               // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[0]==2){
                for(int j=0;j<User.getInstance().istTablTwo.length;j++){
                    if(implic.get(i).element[1]==User.getInstance().istTablTwo[j][1]){
                        for(int k=0;k<User.getInstance().istTablTwo.length;k++){
                            if(User.getInstance().carnoElementsTwo.get(k).realNumber==j){
                                User.getInstance().carnoElementsTwo.get(k).color = User.getInstance().colors[i];
                            }
                        }
                        ((GridTwoParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                    }
                }
                tempCounter++;
            }
            if(implic.get(i).element[1]==2){
                for(int j=0;j<User.getInstance().istTablTwo.length;j++){
                    if(implic.get(i).element[0]==User.getInstance().istTablTwo[j][0]){
                        for(int k=0;k<User.getInstance().istTablTwo.length;k++){
                            if(User.getInstance().carnoElementsTwo.get(k).realNumber==j){
                                User.getInstance().carnoElementsTwo.get(k).color = User.getInstance().colors[i];
                            }
                        }
                        ((GridTwoParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                    }
                }
                tempCounter++;
            }
            resultStrings[i] = tempString;
            tempString="";
        }
        resultView.setText(resultStrings[0]);
        for(int i=1;i<implic.size();i++){
            resultView.setText(resultView.getText().toString()+" v "+resultStrings[i]);
        }
        istTablThreeParams = new ArrayList<>();
        istTablTwoParams = new ArrayList<>();
        istTablFourParams = new ArrayList<>();
    }
    public void getResultThreeParams(ArrayList<IstElement> implic){
        String[] resultStrings = new String[implic.size()];
        String tempString="";
        for(int i = 0; i< implic.size(); i++){
            if(implic.get(i).element[0]==0){
                tempString+="!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if(implic.get(i).element[0]==1){
                tempString+="X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if(implic.get(i).element[1]==0){
                tempString+="!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[1]==1){
                tempString+="X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[2]==0){
                tempString+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[2]==1){
                tempString+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[0]==2){
                if(implic.get(i).element[1]==2){
                    for(int j=0;j<User.getInstance().istTablThree.length;j++){
                        if(implic.get(i).element[2]==User.getInstance().istTablThree[j][2]){
                            for(int k=0;k<User.getInstance().istTablThree.length;k++){
                                if(User.getInstance().carnoElementsThree.get(k).realNumber==j){
                                    User.getInstance().carnoElementsThree.get(k).color = User.getInstance().colors[i];
                                }
                            }
                            ((GridThreeParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                        }
                    }
                }
                else {
                    if(implic.get(i).element[2]==2){
                        for(int j=0;j<User.getInstance().istTablThree.length;j++){
                            if(implic.get(i).element[1]==User.getInstance().istTablThree[j][1]){
                                for(int k=0;k<User.getInstance().istTablThree.length;k++){
                                    if(User.getInstance().carnoElementsThree.get(k).realNumber==j){
                                        User.getInstance().carnoElementsThree.get(k).color = User.getInstance().colors[i];
                                    }
                                }
                                ((GridThreeParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                            }
                        }
                    }
                    else {
                        for(int j=0;j<User.getInstance().istTablThree.length;j++){
                            if(implic.get(i).element[1]==User.getInstance().istTablThree[j][1]&&implic.get(i).element[2]==User.getInstance().istTablThree[i][2]){
                                for(int k=0;k<User.getInstance().istTablThree.length;k++){
                                    if(User.getInstance().carnoElementsThree.get(k).realNumber==j){
                                        User.getInstance().carnoElementsThree.get(k).color = User.getInstance().colors[i];
                                    }
                                }
                                ((GridThreeParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                            }
                        }
                    }
                }

            }
            if(implic.get(i).element[1]==2){
                if(implic.get(i).element[2]==2){
                    for(int j=0;j<User.getInstance().istTablThree.length;j++){
                        if(implic.get(i).element[0]==User.getInstance().istTablThree[j][0]){
                            for(int k=0;k<User.getInstance().istTablThree.length;k++){
                                if(User.getInstance().carnoElementsThree.get(k).realNumber==j){
                                    User.getInstance().carnoElementsThree.get(k).color = User.getInstance().colors[i];
                                }
                            }
                            ((GridThreeParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                        }
                    }
                }
                else {
                    if(implic.get(i).element[0]!=2) {
                        for (int j = 0; j < User.getInstance().istTablThree.length; j++) {
                            if (implic.get(i).element[0] == User.getInstance().istTablThree[j][0] && implic.get(i).element[2] == User.getInstance().istTablThree[i][2]) {
                                for(int k=0;k<User.getInstance().istTablThree.length;k++){
                                    if(User.getInstance().carnoElementsThree.get(k).realNumber==j){
                                        User.getInstance().carnoElementsThree.get(k).color = User.getInstance().colors[i];
                                    }
                                }
                                ((GridThreeParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                            }
                        }
                    }
                }

            }
            if(implic.get(i).element[2]==2&&implic.get(i).element[1]!=2&&implic.get(i).element[0]!=2){
                    for(int j=0;j<User.getInstance().istTablThree.length;j++){
                        if(implic.get(i).element[0]==User.getInstance().istTablThree[j][0]&&implic.get(i).element[1]==User.getInstance().istTablThree[i][1]){
                            for(int k=0;k<User.getInstance().istTablThree.length;k++){
                                if(User.getInstance().carnoElementsThree.get(k).realNumber==j){
                                    User.getInstance().carnoElementsThree.get(k).color = User.getInstance().colors[i];
                                }
                            }
                            ((GridThreeParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                        }
                    }
            }
            resultStrings[i] = tempString;
            tempString = "";
        }
        resultView.setText(resultStrings[0]);
        for(int i=1;i<implic.size();i++){
            resultView.setText(resultView.getText().toString()+" v "+resultStrings[i]);
        }
        istTablThreeParams = new ArrayList<>();
        istTablTwoParams = new ArrayList<>();
        istTablFourParams = new ArrayList<>();
    }
    public void getResultFourParams(ArrayList<IstElement> implic){
        String[] resultStrings = new String[implic.size()];
        String tempString=null;
        for(int i = 0; i< implic.size(); i++){
            if(implic.get(i).element[0]==0){
                tempString+="!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if(implic.get(i).element[0]==1){
                tempString+="X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if(implic.get(i).element[1]==0){
                tempString+="!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[1]==1){
                tempString+="X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[2]==0){
                tempString+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[2]==1){
                tempString+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[3]==0){
                tempString+="!X4";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[3]==1){
                tempString+="X4";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[0]==2){
                if(implic.get(i).element[1]==2){
                    if(implic.get(i).element[2]==2) {
                        for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                            if (implic.get(i).element[3] == User.getInstance().istTablFour[j][3]) {
                                for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                    User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                }
                            }
                                ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                            }
                        }
                    }
                    else {
                        if(implic.get(i).element[3]==2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic.get(i).element[2] == User.getInstance().istTablFour[j][2]) {
                                    for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                        if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                            User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                        }
                                    }
                                    ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        }
                        else {
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic.get(i).element[2] == User.getInstance().istTablFour[j][2]&&implic.get(i).element[3] == User.getInstance().istTablFour[j][3]) {
                                    for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                        if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                            User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                        }
                                    }
                                    ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        }
                    }
                }
                else {
                    if(implic.get(i).element[2]==2){
                        if(implic.get(i).element[3]==2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic.get(i).element[1] == User.getInstance().istTablFour[j][1]) {
                                    for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                        if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                            User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                        }
                                    }
                                    ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        }
                        else {
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic.get(i).element[1] == User.getInstance().istTablFour[j][1]&&implic.get(i).element[3] == User.getInstance().istTablFour[j][3]) {

                                    for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                        if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                            User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                        }
                                    }
                                    ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        }
                    }
                    else {
                        if(implic.get(i).element[3]==2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic.get(i).element[1] == User.getInstance().istTablFour[j][1]&&implic.get(i).element[2] == User.getInstance().istTablFour[j][2]) {

                                    for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                        if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                            User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                        }
                                    }
                                    ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        }
                        else {
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic.get(i).element[1] == User.getInstance().istTablFour[j][1]&&implic.get(i).element[2] == User.getInstance().istTablFour[j][2]&&implic.get(i).element[3] == User.getInstance().istTablFour[j][3]) {

                                    for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                        if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                            User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                        }
                                    }
                                    ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        }
                    }
                }

            }
            if(implic.get(i).element[1]==2){
                if(implic.get(i).element[2]==2){
                    if(implic.get(i).element[3]==2){
                        for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                            if (implic.get(i).element[0] == User.getInstance().istTablFour[j][0]) {

                                for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                    if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                        User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                    }
                                }
                                ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                            }
                        }
                    }
                    else {
                        for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                            if (implic.get(i).element[0] == User.getInstance().istTablFour[j][0]&&implic.get(i).element[3] == User.getInstance().istTablFour[j][3]) {

                                for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                    if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                        User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                    }
                                }
                                ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                            }
                        }
                    }
                }
                else {
                    if(implic.get(i).element[0]!=2&&implic.get(i).element[3]==2) {
                        for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                            if (implic.get(i).element[0] == User.getInstance().istTablFour[j][0] && implic.get(i).element[2] == User.getInstance().istTablFour[i][2]) {

                                for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                    if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                        User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                    }
                                }
                                ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                            }
                        }
                    }
                    else {
                        if(implic.get(i).element[0]!=2&&implic.get(i).element[3]!=2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic.get(i).element[0] == User.getInstance().istTablFour[j][0] && implic.get(i).element[2] == User.getInstance().istTablFour[i][2]&& implic.get(i).element[3] == User.getInstance().istTablFour[i][3]) {

                                    for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                        if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                            User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                        }
                                    }
                                    ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        }
                    }
                }

            }
            if(implic.get(i).element[2]==2&&implic.get(i).element[1]!=2&&implic.get(i).element[0]!=2){
                if(implic.get(i).element[3]==2){
                    for(int j=0;j<User.getInstance().istTablFour.length;j++){
                        if(implic.get(i).element[0]==User.getInstance().istTablFour[j][0]&&implic.get(i).element[1]==User.getInstance().istTablFour[i][1]){

                            for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                    User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                }
                            }
                            ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                        }
                    }
                }
                else {
                    for(int j=0;j<User.getInstance().istTablFour.length;j++){
                        if(implic.get(i).element[0]==User.getInstance().istTablFour[j][0]&&implic.get(i).element[1]==User.getInstance().istTablFour[i][1]&&implic.get(i).element[3]==User.getInstance().istTablFour[i][3]){

                            for(int k=0;k<User.getInstance().istTablFour.length;k++){
                                if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                    User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                                }
                            }
                            ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                        }
                    }
                }

            }
            if(implic.get(i).element[2]!=2&&implic.get(i).element[1]!=2&&implic.get(i).element[0]!=2&&implic.get(i).element[3]==2){
                for(int j=0;j<User.getInstance().istTablFour.length;j++){
                    if(implic.get(i).element[0]==User.getInstance().istTablFour[j][0]&&implic.get(i).element[1]==User.getInstance().istTablFour[i][1]&&implic.get(i).element[2]==User.getInstance().istTablFour[i][2]){

                        for(int k=0;k<User.getInstance().istTablFour.length;k++){
                            if(User.getInstance().carnoElementsFour.get(k).realNumber==j){
                                User.getInstance().carnoElementsFour.get(k).color = User.getInstance().colors[i];
                            }
                        }
                        ((GridFourParamAdapter)karnoKard.getAdapter()).notifyDataSetChanged();
                    }
                }
            }
            resultStrings[i] = tempString;
        }
        resultView.setText(resultStrings[0]);
        for(int i=1;i<implic.size();i++){
            resultView.setText(resultView.getText().toString()+" v "+resultStrings[i]);
        }
        istTablThreeParams = new ArrayList<>();
        istTablTwoParams = new ArrayList<>();
        istTablFourParams = new ArrayList<>();
    }
    public void getTableElementsTwoParams(ArrayList<IstElement> istElements, int row){

        String tempStringTable = "";
        TableElement tableElement = new TableElement();
        for(int i=0;i<istElements.size();i++){
            tableElement.parents="";
            if (istElements.get(i).element[0] == 0) {
                tempStringTable += "!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if (istElements.get(i).element[0] == 1) {
                tempStringTable += "X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if (istElements.get(i).element[1] == 0) {
                tempStringTable += "!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if (istElements.get(i).element[1] == 1) {
                tempStringTable += "X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(row>0){
                tableElement.parents+="Склеены: ";
                for(int j=0;j<User.getInstance().tableElements.size();j++){
                        if(User.getInstance().tableElements.get(j).row==row-1){
                            if(istElements.get(i).element[0]==User.getInstance().tableElements.get(j).formula[0]||istElements.get(i).element[1]==User.getInstance().tableElements.get(j).formula[1]){
                                tableElement.parents +=String.valueOf(User.getInstance().tableElements.get(j).id)+" ";
                            }
                        }
                }
            }
            tableElement.name = tempStringTable;
            tableElement.id = User.getInstance().tableElements.size();
            tableElement.row = row;
            tableElement.formula[0] = istElements.get(i).element[0];
            tableElement.formula[1] = istElements.get(i).element[1];
            tempStringTable = "";
            User.getInstance().tableElements.add(tableElement);
        }
    }
    public void getTableElementsThreeParams(ArrayList<IstElement> implic, int row){

        String tempStringTable = null;
        TableElement tableElement = new TableElement();
        for(int i=0;i<implic.size();i++){
            tableElement.parents="";
            if (implic.get(i).element[0] == 0) {
                tempStringTable += "!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if (implic.get(i).element[0] == 1) {
                tempStringTable += "X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if (implic.get(i).element[1] == 0) {
                tempStringTable += "!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if (implic.get(i).element[1] == 1) {
                tempStringTable += "X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[2]==0){
                tempStringTable+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[2]==1){
                tempStringTable+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(row>0){
                tableElement.parents+="Склеены: ";
                for(int j=0;j<User.getInstance().tableElements.size();j++){
                    if(User.getInstance().tableElements.get(j).row==row-1){
                        if((implic.get(i).element[0]==User.getInstance().tableElements.get(j).formula[0]&&implic.get(i).element[1]==User.getInstance().tableElements.get(j).formula[1])||(implic.get(i).element[0]==User.getInstance().tableElements.get(j).formula[0]&&implic.get(i).element[2]==User.getInstance().tableElements.get(j).formula[2])||(implic.get(i).element[1]==User.getInstance().tableElements.get(j).formula[1]&&implic.get(i).element[2]==User.getInstance().tableElements.get(j).formula[2])){
                            tableElement.parents +=String.valueOf(User.getInstance().tableElements.get(j).id)+" ";
                        }
                    }
                }
            }
            tableElement.name = tempStringTable;
            tableElement.id = User.getInstance().tableElements.size();
            tableElement.row = row;
            tableElement.formula[0] = implic.get(i).element[0];
            tableElement.formula[1] = implic.get(i).element[1];
            tableElement.formula[2] = implic.get(i).element[2];
            tempStringTable = "";
            User.getInstance().tableElements.add(tableElement);
        }
    }
    public void getTableElementsFourParams(ArrayList<IstElement> implic, int row){

        String tempStringTable = null;
        TableElement tableElement = new TableElement();
        for(int i=0;i<implic.size();i++){
            tableElement.parents="";
            if (implic.get(i).element[0] == 0) {
                tempStringTable += "!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if (implic.get(i).element[0] == 1) {
                tempStringTable += "X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if (implic.get(i).element[1] == 0) {
                tempStringTable += "!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if (implic.get(i).element[1] == 1) {
                tempStringTable += "X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[2]==0){
                tempStringTable+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[2]==1){
                tempStringTable+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic.get(i).element[3]==0){
                tempStringTable+="!X4";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic.get(i).element[3]==1){
                tempStringTable+="X4";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(row>0){
                tableElement.parents+="Склеены: ";
                for(int j=0;j<User.getInstance().tableElements.size();j++){
                    if(User.getInstance().tableElements.get(j).row==row-1){
                        if((implic.get(i).element[0]==User.getInstance().tableElements.get(j).formula[0]&&implic.get(i).element[1]==User.getInstance().tableElements.get(j).formula[1]&&implic.get(i).element[2]==User.getInstance().tableElements.get(j).formula[2])
                                ||(implic.get(i).element[0]==User.getInstance().tableElements.get(j).formula[0]&&implic.get(i).element[3]==User.getInstance().tableElements.get(j).formula[3]&&implic.get(i).element[2]==User.getInstance().tableElements.get(j).formula[2])
                                ||(implic.get(i).element[0]==User.getInstance().tableElements.get(j).formula[0]&&implic.get(i).element[1]==User.getInstance().tableElements.get(j).formula[1]&&implic.get(i).element[3]==User.getInstance().tableElements.get(j).formula[3])
                                ||(implic.get(i).element[1]==User.getInstance().tableElements.get(j).formula[1]&&implic.get(i).element[2]==User.getInstance().tableElements.get(j).formula[2]&&implic.get(i).element[3]==User.getInstance().tableElements.get(j).formula[3])){
                            tableElement.parents +=String.valueOf(User.getInstance().tableElements.get(j).id)+" ";
                        }
                    }
                }
            }
            tableElement.name = tempStringTable;
            tableElement.id = User.getInstance().tableElements.size();
            tableElement.row = row;
            tableElement.formula[0] = implic.get(i).element[0];
            tableElement.formula[1] = implic.get(i).element[1];
            tableElement.formula[2] = implic.get(i).element[2];
            tableElement.formula[3] = implic.get(i).element[3];
            tempStringTable = "";
            User.getInstance().tableElements.add(tableElement);
        }
    }
}
