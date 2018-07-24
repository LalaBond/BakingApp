package com.example.someone.bakingapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by someone on 7/18/18.
 */

public class IngredientModel implements Serializable{

    @SerializedName("quantity")
    public float quantity;

    @SerializedName("measure")
    public String measure;

    @SerializedName("ingredient")
    public String ingredient;
}
