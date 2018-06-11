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
    public int[][] istTablTwoParams = new int[4][];
    public int[][] istTablThreeParams = new int[8][];
    public int[][] istTablFourParams = new int[16][];
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
    public int[][] makeIterTwoParams(int[][] implic){
        int[][] newImplic = new int[4][];
        for(int i=0;i<implic.length;i++){
            for (int j=1;j<implic.length-1;j++){
                if(implic[i][3]+1==implic[j][3]){
                    if (implic[i][0]==implic[j][0]){
                        newImplic[countOfImplic][0]=implic[i][0];
                        newImplic[countOfImplic][1]=2;
                        newImplic[countOfImplic][2]=1;
                        newImplic[countOfImplic][3]=implic[i][0];
                        implic[i][4]++;
                        implic[j][4]++;
                        countOfImplic++;
                    }
                    else {
                        if (implic[i][1]==implic[j][1]){
                            newImplic[countOfImplic][0]=2;
                            newImplic[countOfImplic][1]=implic[i][1];
                            newImplic[countOfImplic][2]=1;
                            newImplic[countOfImplic][3]=implic[i][1];
                            implic[i][4]++;
                            implic[j][4]++;
                            countOfImplic++;
                        }
                    }
                }
            }
        }
        for(int i=0;i<implic.length;i++){
            if(implic[i][4]<1){
                newImplic[countOfImplic]=implic[i];
                countOfImplic++;
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
    public int [][] makeIterThreeParams(int [][] implic){
        int[][] newImplic = new int[8][];
        for(int i=0;i<implic.length;i++){
            for (int j=1;j<implic.length-1;j++){
                if(implic[i][4]+1==implic[j][4]){
                    if (implic[i][0]==implic[j][0]&&implic[i][1]==implic[j][1]){
                        newImplic[countOfImplic][0]=implic[i][0];
                        newImplic[countOfImplic][1]=implic[i][1];
                        newImplic[countOfImplic][2]=2;
                        newImplic[countOfImplic][3]=1;
                        implic[i][5]++;
                        implic[j][5]++;
                        newImplic[countOfImplic][4]=implic[i][0]+implic[i][1];
                        countOfImplic++;
                    }
                    else {
                        if (implic[i][1]==implic[j][1]&&implic[i][2]==implic[j][2]){
                            newImplic[countOfImplic][0]=2;
                            newImplic[countOfImplic][1]=implic[i][1];
                            newImplic[countOfImplic][2]=implic[i][2];
                            newImplic[countOfImplic][3]=1;
                            implic[i][5]++;
                            implic[j][5]++;
                            newImplic[countOfImplic][4]=implic[i][1]+implic[i][2];
                            countOfImplic++;
                        }
                        else{
                            if (implic[i][0]==implic[j][0]&&implic[i][2]==implic[j][2]){
                                newImplic[countOfImplic][1]=2;
                                newImplic[countOfImplic][0]=implic[i][0];
                                newImplic[countOfImplic][2]=implic[i][2];
                                newImplic[countOfImplic][3]=1;
                                implic[i][5]++;
                                implic[j][5]++;
                                newImplic[countOfImplic][4]=implic[i][0]+implic[i][2];
                                countOfImplic++;
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<implic.length;i++){
            if(implic[i][5]<1){
                newImplic[countOfImplic]=implic[i];
                countOfImplic++;
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
    public int [][] makeIterFourParams(int [][] implic){
        int[][] newImplic = new int[16][];
        for(int i=0;i<implic.length;i++){
            for (int j=1;j<implic.length-1;j++){
                if(implic[i][5]+1==implic[j][5]){
                    if (implic[i][0]==implic[j][0]&&implic[i][1]==implic[j][1]&&implic[i][2]==implic[j][2]){
                        newImplic[countOfImplic][0]=implic[i][0];
                        newImplic[countOfImplic][1]=implic[i][1];
                        newImplic[countOfImplic][2]=implic[i][2];
                        newImplic[countOfImplic][3]=2;
                        newImplic[countOfImplic][4]=1;
                        implic[i][6]++;
                        implic[j][6]++;
                        newImplic[countOfImplic][5]=implic[i][0]+implic[i][1]+implic[i][2];
                        countOfImplic++;
                    }
                    else {
                        if (implic[i][1]==implic[j][1]&&implic[i][2]==implic[j][2]&&implic[i][3]==implic[j][3]){
                            newImplic[countOfImplic][0]=2;
                            newImplic[countOfImplic][1]=implic[i][1];
                            newImplic[countOfImplic][2]=implic[i][2];
                            newImplic[countOfImplic][3]=implic[i][3];
                            newImplic[countOfImplic][4]=1;
                            implic[i][6]++;
                            implic[j][6]++;
                            newImplic[countOfImplic][5]=implic[i][1]+implic[i][2]+implic[i][3];
                            countOfImplic++;
                        }
                        else{
                            if (implic[i][0]==implic[j][0]&&implic[i][2]==implic[j][2]&&implic[i][3]==implic[j][3]){
                                newImplic[countOfImplic][1]=2;
                                newImplic[countOfImplic][0]=implic[i][0];
                                newImplic[countOfImplic][2]=implic[i][2];
                                newImplic[countOfImplic][3]=implic[i][3];
                                newImplic[countOfImplic][4]=1;
                                implic[i][6]++;
                                implic[j][6]++;
                                newImplic[countOfImplic][5]=implic[i][0]+implic[i][2]+implic[i][3];
                                countOfImplic++;
                            }
                            else {
                                if (implic[i][0]==implic[j][0]&&implic[i][1]==implic[j][1]&&implic[i][3]==implic[j][3]){
                                    newImplic[countOfImplic][2]=2;
                                    newImplic[countOfImplic][0]=implic[i][0];
                                    newImplic[countOfImplic][1]=implic[i][1];
                                    newImplic[countOfImplic][3]=implic[i][3];
                                    newImplic[countOfImplic][4]=1;
                                    implic[i][6]++;
                                    implic[j][6]++;
                                    newImplic[countOfImplic][5]=implic[i][0]+implic[i][1]+implic[i][3];
                                    countOfImplic++;
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<implic.length;i++){
            if(implic[i][6]<1){
                newImplic[countOfImplic]=implic[i];
                countOfImplic++;
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
    public int[][] sortMassTwoParams(int [][] imnplic){
        for (int i=0;i<imnplic.length;i++){
            for( int j=1;j<imnplic.length-1;j++){
                if(imnplic[i][3]>imnplic[j][3]){
                    int[] temp = {imnplic[i][0],imnplic[i][1],imnplic[i][2],imnplic[i][3],imnplic[i][4]};
                    imnplic[i][0] = imnplic[j][0];
                    imnplic[i][1] = imnplic[j][1];
                    imnplic[i][2] = imnplic[j][2];
                    imnplic[i][3] = imnplic[j][3];
                    imnplic[i][4] = imnplic[j][4];
                    imnplic[j][0] = temp[0];
                    imnplic[j][1] = temp[1];
                    imnplic[j][2] = temp[2];
                    imnplic[j][3] = temp[3];
                    imnplic[j][4] = temp[4];
                }
            }
        }
        return imnplic;
    }
    public int[][] sortMassThreeParams(int[][] implic){
        for (int i=0;i<implic.length;i++){
            for( int j=1;j<implic.length-1;j++){
                if(implic[i][4]>implic[j][4]){
                    int[] temp = {implic[i][0],implic[i][1],implic[i][2],implic[i][3],implic[i][4],implic[i][5]};
                    implic[i][0] = implic[j][0];
                    implic[i][1] = implic[j][1];
                    implic[i][2] = implic[j][2];
                    implic[i][3] = implic[j][3];
                    implic[i][4] = implic[j][4];
                    implic[i][5] = implic[j][5];
                    implic[j][0] = temp[0];
                    implic[j][1] = temp[1];
                    implic[j][2] = temp[2];
                    implic[j][3] = temp[3];
                    implic[j][4] = temp[4];
                    implic[j][5] = temp[5];
                }
            }
        }
        return implic;
    }
    public int [][] sortMassFourParams (int [][] imnplic){
        for (int i=0;i<imnplic.length;i++){
            for( int j=1;j<imnplic.length-1;j++){
                if(imnplic[i][5]>imnplic[j][5]){
                    int[] temp = {imnplic[i][0],imnplic[i][1],imnplic[i][2],imnplic[i][3],imnplic[i][4],imnplic[i][5],imnplic[i][6]};
                    imnplic[i][0] = imnplic[j][0];
                    imnplic[i][1] = imnplic[j][1];
                    imnplic[i][2] = imnplic[j][2];
                    imnplic[i][3] = imnplic[j][3];
                    imnplic[i][4] = imnplic[j][4];
                    imnplic[i][5] = imnplic[j][5];
                    imnplic[i][6] = imnplic[j][6];
                    imnplic[j][0] = temp[0];
                    imnplic[j][1] = temp[1];
                    imnplic[j][2] = temp[2];
                    imnplic[j][3] = temp[3];
                    imnplic[j][4] = temp[4];
                    imnplic[j][5] = temp[5];
                    imnplic[j][6] = temp[6];
                }
            }
        }
        return imnplic;
    }
    public void methodForTwoParams(){
        for(int i=0;i<4;i++){
            if(User.getInstance().istTablTwo[i][2]==1){
                istTablTwoParams[i][2]=User.getInstance().istTablTwo[i][2];
                for(int j=0;j<2;j++){
                    if(User.getInstance().istTablTwo[i][j]==1){
                        counter++;
                    }
                }
                istTablTwoParams[i][0]=User.getInstance().istTablTwo[i][0];
                istTablTwoParams[i][1]=User.getInstance().istTablTwo[i][1];
                istTablTwoParams[i][3]=counter;
                istTablTwoParams[i][4]=0;

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
                istTablThreeParams[i][3]=User.getInstance().istTablThree[i][3];
                for(int j=0;j<3;j++){
                    if(User.getInstance().istTablThree[i][j]==1){
                        counter++;
                    }
                }
                istTablThreeParams[i][0]=User.getInstance().istTablThree[i][0];
                istTablThreeParams[i][1]=User.getInstance().istTablThree[i][1];
                istTablThreeParams[i][2]=User.getInstance().istTablThree[i][2];
                istTablThreeParams[i][4]=counter;
                istTablThreeParams[i][5]=0;
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
                istTablFourParams[i][4]=User.getInstance().istTablFour[i][4];
                for(int j=0;j<4;j++){
                    if(User.getInstance().istTablFour[i][j]==1){
                        counter++;
                    }
                }
                istTablFourParams[i][0]=User.getInstance().istTablFour[i][0];
                istTablFourParams[i][1]=User.getInstance().istTablFour[i][1];
                istTablFourParams[i][2]=User.getInstance().istTablFour[i][2];
                istTablFourParams[i][3]=User.getInstance().istTablFour[i][3];
                istTablFourParams[i][5]=counter;
                istTablFourParams[i][6]=0;

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
    public void getResultTwoParams(int [][] implic){
        int[][] tempImplic = new int[4][];
        String[] resultStrings = new String[implic.length];
        int tempCounter = 0;
        String tempString=null;
        for(int i = 0; i< implic.length; i++){
            if(implic[i][0]==0){
                tempString+="!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if(implic[i][0]==1){
                tempString+="X1";
               // resultView.setText(resultView.getText().toString()+"X1");
            }
            if(implic[i][1]==0){
                tempString+="!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][1]==1){
                tempString+="X2";
               // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][0]==2){
                for(int j=0;j<User.getInstance().istTablTwo.length;j++){
                    if(implic[i][1]==User.getInstance().istTablTwo[j][1]){
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
            if(implic[i][1]==2){
                for(int j=0;j<User.getInstance().istTablTwo.length;j++){
                    if(implic[i][0]==User.getInstance().istTablTwo[j][0]){
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
        }
        resultView.setText(resultStrings[0]);
        for(int i=1;i<implic.length;i++){
            resultView.setText(resultView.getText().toString()+" v "+resultStrings[i]);
        }
        istTablThreeParams = new int[8][];
        istTablTwoParams = new int[4][];
        istTablFourParams = new int[16][];
    }
    public void getResultThreeParams(int [][] implic){
        String[] resultStrings = new String[implic.length];
        String tempString=null;
        for(int i = 0; i< implic.length; i++){
            if(implic[i][0]==0){
                tempString+="!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if(implic[i][0]==1){
                tempString+="X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if(implic[i][1]==0){
                tempString+="!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][1]==1){
                tempString+="X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][2]==0){
                tempString+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][2]==1){
                tempString+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][0]==2){
                if(implic[i][1]==2){
                    for(int j=0;j<User.getInstance().istTablThree.length;j++){
                        if(implic[i][2]==User.getInstance().istTablThree[j][2]){
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
                    if(implic[i][2]==2){
                        for(int j=0;j<User.getInstance().istTablThree.length;j++){
                            if(implic[i][1]==User.getInstance().istTablThree[j][1]){
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
                            if(implic[i][1]==User.getInstance().istTablThree[j][1]&&implic[i][2]==User.getInstance().istTablThree[i][2]){
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
            if(implic[i][1]==2){
                if(implic[i][2]==2){
                    for(int j=0;j<User.getInstance().istTablThree.length;j++){
                        if(implic[i][0]==User.getInstance().istTablThree[j][0]){
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
                    if(implic[i][0]!=2) {
                        for (int j = 0; j < User.getInstance().istTablThree.length; j++) {
                            if (implic[i][0] == User.getInstance().istTablThree[j][0] && implic[i][2] == User.getInstance().istTablThree[i][2]) {
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
            if(implic[i][2]==2&&implic[i][1]!=2&&implic[i][0]!=2){
                    for(int j=0;j<User.getInstance().istTablThree.length;j++){
                        if(implic[i][0]==User.getInstance().istTablThree[j][0]&&implic[i][1]==User.getInstance().istTablThree[i][1]){
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
        }
        resultView.setText(resultStrings[0]);
        for(int i=1;i<implic.length;i++){
            resultView.setText(resultView.getText().toString()+" v "+resultStrings[i]);
        }
        istTablThreeParams = new int[8][];
        istTablTwoParams = new int[4][];
        istTablFourParams = new int[16][];
    }
    public void getResultFourParams(int [][] implic){
        String[] resultStrings = new String[implic.length];
        String tempString=null;
        for(int i = 0; i< implic.length; i++){
            if(implic[i][0]==0){
                tempString+="!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if(implic[i][0]==1){
                tempString+="X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if(implic[i][1]==0){
                tempString+="!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][1]==1){
                tempString+="X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][2]==0){
                tempString+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][2]==1){
                tempString+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][3]==0){
                tempString+="!X4";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][3]==1){
                tempString+="X4";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][0]==2){
                if(implic[i][1]==2){
                    if(implic[i][2]==2) {
                        for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                            if (implic[i][3] == User.getInstance().istTablFour[j][3]) {
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
                        if(implic[i][3]==2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic[i][2] == User.getInstance().istTablFour[j][2]) {
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
                                if (implic[i][2] == User.getInstance().istTablFour[j][2]&&implic[i][3] == User.getInstance().istTablFour[j][3]) {
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
                    if(implic[i][2]==2){
                        if(implic[i][3]==2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic[i][1] == User.getInstance().istTablFour[j][1]) {
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
                                if (implic[i][1] == User.getInstance().istTablFour[j][1]&&implic[i][3] == User.getInstance().istTablFour[j][3]) {

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
                        if(implic[i][3]==2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic[i][1] == User.getInstance().istTablFour[j][1]&&implic[i][2] == User.getInstance().istTablFour[j][2]) {

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
                                if (implic[i][1] == User.getInstance().istTablFour[j][1]&&implic[i][2] == User.getInstance().istTablFour[j][2]&&implic[i][3] == User.getInstance().istTablFour[j][3]) {

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
            if(implic[i][1]==2){
                if(implic[i][2]==2){
                    if(implic[i][3]==2){
                        for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                            if (implic[i][0] == User.getInstance().istTablFour[j][0]) {

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
                            if (implic[i][0] == User.getInstance().istTablFour[j][0]&&implic[i][3] == User.getInstance().istTablFour[j][3]) {

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
                    if(implic[i][0]!=2&&implic[i][3]==2) {
                        for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                            if (implic[i][0] == User.getInstance().istTablFour[j][0] && implic[i][2] == User.getInstance().istTablFour[i][2]) {

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
                        if(implic[i][0]!=2&&implic[i][3]!=2){
                            for (int j = 0; j < User.getInstance().istTablFour.length; j++) {
                                if (implic[i][0] == User.getInstance().istTablFour[j][0] && implic[i][2] == User.getInstance().istTablFour[i][2]&& implic[i][3] == User.getInstance().istTablFour[i][3]) {

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
            if(implic[i][2]==2&&implic[i][1]!=2&&implic[i][0]!=2){
                if(implic[i][3]==2){
                    for(int j=0;j<User.getInstance().istTablFour.length;j++){
                        if(implic[i][0]==User.getInstance().istTablFour[j][0]&&implic[i][1]==User.getInstance().istTablFour[i][1]){

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
                        if(implic[i][0]==User.getInstance().istTablFour[j][0]&&implic[i][1]==User.getInstance().istTablFour[i][1]&&implic[i][3]==User.getInstance().istTablFour[i][3]){

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
            if(implic[i][2]!=2&&implic[i][1]!=2&&implic[i][0]!=2&&implic[i][3]==2){
                for(int j=0;j<User.getInstance().istTablFour.length;j++){
                    if(implic[i][0]==User.getInstance().istTablFour[j][0]&&implic[i][1]==User.getInstance().istTablFour[i][1]&&implic[i][2]==User.getInstance().istTablFour[i][2]){

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
        for(int i=1;i<implic.length;i++){
            resultView.setText(resultView.getText().toString()+" v "+resultStrings[i]);
        }
        istTablThreeParams = new int[8][];
        istTablTwoParams = new int[4][];
        istTablFourParams = new int[16][];
    }
    public void getTableElementsTwoParams(int [][] implic, int row){

        String tempStringTable = null;
        TableElement tableElement = new TableElement();
        for(int i=0;i<implic.length;i++){
            tableElement.parents="";
            if (implic[i][0] == 0) {
                tempStringTable += "!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if (implic[i][0] == 1) {
                tempStringTable += "X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if (implic[i][1] == 0) {
                tempStringTable += "!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if (implic[i][1] == 1) {
                tempStringTable += "X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(row>0){
                tableElement.parents+="Склеены: ";
                for(int j=0;j<User.getInstance().tableElements.size();j++){
                        if(User.getInstance().tableElements.get(j).row==row-1){
                            if(implic[i][0]==User.getInstance().tableElements.get(j).formula[0]||implic[i][1]==User.getInstance().tableElements.get(j).formula[1]){
                                tableElement.parents +=String.valueOf(User.getInstance().tableElements.get(j).id)+" ";
                            }
                        }
                }
            }
            tableElement.name = tempStringTable;
            tableElement.id = User.getInstance().tableElements.size();
            tableElement.row = row;
            tableElement.formula[0] = implic[i][0];
            tableElement.formula[1] = implic[i][1];
            tempStringTable = "";
            User.getInstance().tableElements.add(tableElement);
        }
    }
    public void getTableElementsThreeParams(int [][] implic, int row){

        String tempStringTable = null;
        TableElement tableElement = new TableElement();
        for(int i=0;i<implic.length;i++){
            tableElement.parents="";
            if (implic[i][0] == 0) {
                tempStringTable += "!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if (implic[i][0] == 1) {
                tempStringTable += "X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if (implic[i][1] == 0) {
                tempStringTable += "!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if (implic[i][1] == 1) {
                tempStringTable += "X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][2]==0){
                tempStringTable+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][2]==1){
                tempStringTable+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(row>0){
                tableElement.parents+="Склеены: ";
                for(int j=0;j<User.getInstance().tableElements.size();j++){
                    if(User.getInstance().tableElements.get(j).row==row-1){
                        if((implic[i][0]==User.getInstance().tableElements.get(j).formula[0]&&implic[i][1]==User.getInstance().tableElements.get(j).formula[1])||(implic[i][0]==User.getInstance().tableElements.get(j).formula[0]&&implic[i][2]==User.getInstance().tableElements.get(j).formula[2])||(implic[i][1]==User.getInstance().tableElements.get(j).formula[1]&&implic[i][2]==User.getInstance().tableElements.get(j).formula[2])){
                            tableElement.parents +=String.valueOf(User.getInstance().tableElements.get(j).id)+" ";
                        }
                    }
                }
            }
            tableElement.name = tempStringTable;
            tableElement.id = User.getInstance().tableElements.size();
            tableElement.row = row;
            tableElement.formula[0] = implic[i][0];
            tableElement.formula[1] = implic[i][1];
            tableElement.formula[2] = implic[i][2];
            tempStringTable = "";
            User.getInstance().tableElements.add(tableElement);
        }
    }
    public void getTableElementsFourParams(int [][] implic, int row){

        String tempStringTable = null;
        TableElement tableElement = new TableElement();
        for(int i=0;i<implic.length;i++){
            tableElement.parents="";
            if (implic[i][0] == 0) {
                tempStringTable += "!X1";
                //resultView.setText(resultView.getText().toString()+"!X1");
            }
            if (implic[i][0] == 1) {
                tempStringTable += "X1";
                // resultView.setText(resultView.getText().toString()+"X1");
            }
            if (implic[i][1] == 0) {
                tempStringTable += "!X2";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if (implic[i][1] == 1) {
                tempStringTable += "X2";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][2]==0){
                tempStringTable+="!X3";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][2]==1){
                tempStringTable+="X3";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(implic[i][3]==0){
                tempStringTable+="!X4";
                //resultView.setText(resultView.getText().toString()+"!X2");
            }
            if(implic[i][3]==1){
                tempStringTable+="X4";
                // resultView.setText(resultView.getText().toString()+"X2");
            }
            if(row>0){
                tableElement.parents+="Склеены: ";
                for(int j=0;j<User.getInstance().tableElements.size();j++){
                    if(User.getInstance().tableElements.get(j).row==row-1){
                        if((implic[i][0]==User.getInstance().tableElements.get(j).formula[0]&&implic[i][1]==User.getInstance().tableElements.get(j).formula[1]&&implic[i][2]==User.getInstance().tableElements.get(j).formula[2])
                                ||(implic[i][0]==User.getInstance().tableElements.get(j).formula[0]&&implic[i][3]==User.getInstance().tableElements.get(j).formula[3]&&implic[i][2]==User.getInstance().tableElements.get(j).formula[2])
                                ||(implic[i][0]==User.getInstance().tableElements.get(j).formula[0]&&implic[i][1]==User.getInstance().tableElements.get(j).formula[1]&&implic[i][3]==User.getInstance().tableElements.get(j).formula[3])
                                ||(implic[i][1]==User.getInstance().tableElements.get(j).formula[1]&&implic[i][2]==User.getInstance().tableElements.get(j).formula[2]&&implic[i][3]==User.getInstance().tableElements.get(j).formula[3])){
                            tableElement.parents +=String.valueOf(User.getInstance().tableElements.get(j).id)+" ";
                        }
                    }
                }
            }
            tableElement.name = tempStringTable;
            tableElement.id = User.getInstance().tableElements.size();
            tableElement.row = row;
            tableElement.formula[0] = implic[i][0];
            tableElement.formula[1] = implic[i][1];
            tableElement.formula[2] = implic[i][2];
            tableElement.formula[3] = implic[i][3];
            tempStringTable = "";
            User.getInstance().tableElements.add(tableElement);
        }
    }
}
