package com.example.someone.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.someone.bakingapp.adapters.IngredientsAdapter;
import com.example.someone.bakingapp.models.RecipeModel;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {


    private List<RecipeModel> recipeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);


        //GET DATA OF BUNDLE


        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this, recipeModel);
        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ingredientsRecyclerView.setLayoutManager(layoutManager);

        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

    }
}
