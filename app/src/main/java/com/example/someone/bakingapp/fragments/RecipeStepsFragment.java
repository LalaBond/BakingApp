package com.example.someone.bakingapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.someone.bakingapp.R;
import com.example.someone.bakingapp.adapters.StepsAdapter;
import com.example.someone.bakingapp.models.RecipeModel;

@SuppressLint("ValidFragment")
public class RecipeStepsFragment extends Fragment {


    private Context context;
    private RecipeModel recipeModel;

    @SuppressLint("ValidFragment")
    public RecipeStepsFragment(Context context, RecipeModel recipeModel) {

        this.context = context;
        this.recipeModel = recipeModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        StepsAdapter stepsAdapter = new StepsAdapter(context, recipeModel);
        RecyclerView stepsRecyclerView = view.findViewById(R.id.stepsRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        stepsRecyclerView.setLayoutManager(layoutManager);

        stepsRecyclerView.setAdapter(stepsAdapter);

         return view;
    }

}
