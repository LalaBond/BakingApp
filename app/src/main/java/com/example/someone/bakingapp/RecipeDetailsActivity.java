package com.example.someone.bakingapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

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


            if (getSupportActionBar() != null){

                getSupportActionBar().setTitle(recipeModel.getName());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

        //twoPane = intent.getBooleanExtra("twoPane", false);

        /*View pager adapter*/
        RecipePagerAdapter pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager(), this, recipeModel);

        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}

