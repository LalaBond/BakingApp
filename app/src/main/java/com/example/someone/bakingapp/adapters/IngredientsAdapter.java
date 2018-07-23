package com.example.someone.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.someone.bakingapp.RecipeDetailsActivity;
import com.example.someone.bakingapp.models.RecipeModel;

import java.util.List;

/**
 * Created by someone on 7/19/18.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    public IngredientsAdapter(Context context, List<RecipeModel> recipeModel) {
    }


    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(IngredientsAdapter.ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
