package com.example.someone.bakingapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.someone.bakingapp.adapters.IngredientsAdapter;
import com.example.someone.bakingapp.fragments.IngredientsFragment;
import com.example.someone.bakingapp.fragments.RecipeStepsFragment;
import com.example.someone.bakingapp.models.RecipeModel;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

private ViewPager pager;
    private List<RecipeModel> recipeModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);


        Intent intent = getIntent();

        recipeModel = (List<RecipeModel>) intent.getSerializableExtra("recipe");

        /*View pager adapter*/
        RecipePagerAdapter pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager(), this);
        pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

    }
}

class RecipePagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public RecipePagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
               return new IngredientsFragment(context);
           case 1:
               return new RecipeStepsFragment();
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
