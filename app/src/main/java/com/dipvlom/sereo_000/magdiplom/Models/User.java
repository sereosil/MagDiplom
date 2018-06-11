package com.dipvlom.sereo_000.magdiplom.Models;

import android.graphics.Color;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class User {
    static private User instance = null;
    public int id;
    public int[] colors = {
            Color.WHITE,
            Color.BLUE,
            Color.GRAY,
            Color.RED,
            Color.YELLOW,
            Color.GREEN,
            Color.MAGENTA,
            Color.CYAN,
            Color.parseColor("#800080"),
            Color.parseColor("#f1cbff"),
            Color.parseColor("#bbaae5"),
            Color.parseColor("#fbffc7"),
            Color.parseColor("#79559f"),
            Color.parseColor("#4f4237"),
            Color.parseColor("#e5ba52"),
            Color.parseColor("#eac774")

    };
    public int[][] istTablFour = {
            {0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,1,1,0,0,0},
            {0,1,0,0,0,0,0},
            {0,1,0,1,0,0,0},
            {0,1,1,0,0,0,0},
            {0,1,1,1,0,0,0},
            {1,0,0,0,0,0,0},
            {1,0,0,1,0,0,0},
            {1,0,1,0,0,0,0},
            {1,0,1,1,0,0,0},
            {1,1,0,0,0,0,0},
            {1,1,0,1,0,0,0},
            {1,1,1,0,0,0,0},
            {1,1,1,1,0,0,0}
    };
    public int[][] istTablThree = {
            {0,0,0,0,0,0},
            {0,0,1,0,0,0},
            {0,1,0,0,0,0},
            {0,1,1,0,0,0},
            {1,0,0,0,0,0},
            {1,0,1,0,0,0},
            {1,1,0,0,0,0},
            {1,1,1,0,0,0}
    };
    public int[][] istTablTwo = {
            {0,0,0,0,0},
            {0,1,0,0,0},
            {1,0,0,0,0},
            {1,1,0,0,0}
    };
    public List<CarnoElement> carnoElementsTwo = new ArrayList<>();
    public List<CarnoElement> carnoElementsThree = new ArrayList<>();
    public List<CarnoElement> carnoElementsFour = new ArrayList<>();
    public List<TableElement> tableElements = new ArrayList<>();
    public boolean method;
    public int paramsCount;
    static public User getInstance() {
        return instance;
    }
    static public void createUser(){
        instance = new User();
    }


}
