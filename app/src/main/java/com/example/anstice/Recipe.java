package com.example.anstice;

import java.io.Serializable;

/**
 * Created by Anstice on 2018/8/29.
 */

public class Recipe implements Serializable {
    private String name;
    private int drawableId;

    // Construtor
    public Recipe(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    // getter
    public String getName() {
        return name;
    }

    public int getDrawableId() {
        return drawableId;
    }
}
