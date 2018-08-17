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
import android.widget.TextView;

import com.example.someone.bakingapp.R;
import com.example.someone.bakingapp.adapters.IngredientsAdapter;
import com.example.someone.bakingapp.models.RecipeModel;

@SuppressLint("ValidFragment")
public class IngredientsFragment extends Fragment {

    private Context context;
    private RecipeModel recipeModel;


    public IngredientsFragment(Context context, RecipeModel recipeModel) {
        this.context = context;
        this.recipeModel = recipeModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        TextView servingQtyTv = view.findViewById(R.id.servingQty);
        servingQtyTv.setText(String.valueOf(recipeModel.getServings()));

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(context, recipeModel);
        RecyclerView ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        ingredientsRecyclerView.setLayoutManager(layoutManager);

        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        return view;
    }

}
