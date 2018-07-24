package com.example.someone.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.someone.bakingapp.R;
import com.example.someone.bakingapp.RecipeDetailsActivity;
import com.example.someone.bakingapp.models.RecipeModel;

import java.util.List;

/**
 * Created by someone on 7/19/18.
 */

/*Adapter made  to display ingredients in the view pager*/
public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private RecipeModel recipeModel;

    public IngredientsAdapter(Context context, RecipeModel recipeModel) {

        this.recipeModel = recipeModel;

    }


    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ingredients_item_layout, parent, false);

        return new IngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsAdapter.ViewHolder holder, int position) {

        holder.ingredientsTv.setText(recipeModel.getIngredients().get(position).ingredient + " ");
        holder.measureTv.setText(recipeModel.getIngredients().get(position).measure + " ");
        String quanitySt = String.valueOf(recipeModel.getIngredients().get(position).quantity);
        holder.qtyTv.setText(quanitySt);
    }



    @Override
    public int getItemCount() {
        return recipeModel.getIngredients().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientsTv, measureTv, qtyTv;
        public ViewHolder(View itemView) {
            super(itemView);
            ingredientsTv = itemView.findViewById(R.id.ingredient);
            measureTv = itemView.findViewById(R.id.measure);
            qtyTv = itemView.findViewById(R.id.quantity);



        }
    }
}
