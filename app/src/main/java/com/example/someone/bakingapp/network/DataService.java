package com.example.someone.bakingapp.network;

import com.example.someone.bakingapp.models.RecipeModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by someone on 7/17/18.
 */

public interface DataService {

    @GET()
    Call<RecipeModel> getRecipes();
}
