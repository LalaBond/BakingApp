package com.example.someone.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.someone.bakingapp.fragments.IngredientsFragment;
import com.example.someone.bakingapp.fragments.RecipeStepsFragment;
import com.example.someone.bakingapp.models.RecipeModel;

public class RecipeDetailsActivity extends AppCompatActivity {

private ViewPager pager;
    private RecipeModel recipeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intent = getIntent();

        recipeModel = (RecipeModel) intent.getSerializableExtra("recipe");

        /*View pager adapter*/
        RecipePagerAdapter pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager(), this, recipeModel);

        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

    }
}

class RecipePagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private RecipeModel recipeModel;

    public RecipePagerAdapter(FragmentManager fm, Context context, RecipeModel recipeModel) {
        super(fm);

        this.context = context;
        this.recipeModel = recipeModel;
    }

    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
               return new IngredientsFragment(context, recipeModel);
           case 1:
               return new RecipeStepsFragment(context, recipeModel);
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.ingredientsString);
            case 1:
                return context. getString(R.string.stepsString);
            default:
                return null;
        }
    }
}
