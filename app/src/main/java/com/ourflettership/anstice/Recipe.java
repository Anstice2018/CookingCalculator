package com.ourflettership.anstice;

import java.io.Serializable;

/**
 * Created by Anstice on 2018/8/29.
 */

public class Recipe implements Serializable {

    public static final int 項目長度 = 7;

    private String name;
    private String describe;
    private String copy;
    private int drawableId;
    private String[] ingredients = new String[項目長度];
    private String[] wUnits = new String[項目長度];
    private String[] tUnits = new String[項目長度];
    private String[] weights = new String[項目長度];
    private String[] totals = new String[項目長度];



    // Construtor
    public Recipe(String name, String describe, int drawableId, String copy,
                  String[] ingredients, String[] wUnits, String[] tUnits, String[] weights, String[] totals) {
        this.name = name;
        this.describe = describe;
        this.drawableId = drawableId;
        this.copy = copy;
        this.ingredients = ingredients;
        this.wUnits = wUnits;
        this.tUnits = tUnits;
        this.weights = weights;
        this.totals = totals;
    }




    // getter
    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public String getCopy() {
        return copy;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public String[] getIngredients(){
        return ingredients;
    }

    public String[] getwUnits() {
        return wUnits;
    }

    public String[] gettUnits() {
        return tUnits;
    }

    public String[] getWeights() {
        return weights;
    }

    public String[] getTotals() {
        return totals;
    }
}
