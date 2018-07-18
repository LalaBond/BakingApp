package com.example.someone.bakingapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by someone on 7/18/18.
 */

public class IngredientModel {

    @SerializedName("quantity")
    public Integer quantity;

    @SerializedName("measure")
    public String measure;

    @SerializedName("ingredient")
    public String ingredient;
}
