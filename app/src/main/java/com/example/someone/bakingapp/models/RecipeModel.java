package com.example.someone.bakingapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by someone on 7/17/18.
 */

public class RecipeModel {


    @SerializedName("id")
    public Integer id;

    @SerializedName("name")
    public String name;

    @SerializedName("ingredients")
    public List<IngredientModel> ingredients = null;

    @SerializedName("steps")
    public List<StepModel> steps = null;

    @SerializedName("servings")
    public Integer servings;

    @SerializedName("image")
    public String image;

}
