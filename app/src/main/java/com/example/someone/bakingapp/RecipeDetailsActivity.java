package com.example.someone.bakingapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.someone.bakingapp.adapters.RecipePagerAdapter;
import com.example.someone.bakingapp.models.RecipeModel;

public class RecipeDetailsActivity extends AppCompatActivity {

private ViewPager pager;
private RecipeModel recipeModel;
private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_details);
        Intent intent = getIntent();

        recipeModel = (RecipeModel) intent.getSerializableExtra("recipe");
        //twoPane = intent.getBooleanExtra("twoPane", false);

        TextView recipeNameTv = findViewById(R.id.recipeNameTv);
        recipeNameTv.setText(recipeModel.getName());
        /*View pager adapter*/
        RecipePagerAdapter pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager(), this, recipeModel);

        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

    }
}

