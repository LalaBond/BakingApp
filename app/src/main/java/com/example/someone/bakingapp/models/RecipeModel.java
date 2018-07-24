package com.example.someone.bakingapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by someone on 7/17/18.
 */

public class RecipeModel implements Serializable {


    @SerializedName("id")
    public Integer id;

    @SerializedName("name")
    public String name;

    @SerializedName("ingredients")
    public List<IngredientModel> ingredients = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(List<StepModel> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("steps")
    public List<StepModel> steps = null;

    @SerializedName("servings")
    public Integer servings;

    @SerializedName("image")
    public String image;

}
