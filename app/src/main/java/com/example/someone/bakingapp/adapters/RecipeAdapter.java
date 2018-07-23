package com.example.someone.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.someone.bakingapp.R;
import com.example.someone.bakingapp.RecipeDetailsActivity;
import com.example.someone.bakingapp.models.RecipeModel;

import java.util.List;

/**
 * Created by someone on 7/18/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<RecipeModel> recipes;
    private Context context;

    public RecipeAdapter(Context context, List<RecipeModel> body) {

        recipes = body;
        this.context = context;
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipes_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {

        holder.nameTv.setText(recipes.get(position).name);

    }


    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameTv;
        LinearLayout itemLinearLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.name);
            itemLinearLayout = itemView.findViewById(R.id.itemlinearLayout);

            itemLinearLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent recipeDetails = null;
                    try {
                        recipeDetails = new Intent(context, RecipeDetailsActivity.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("$lala error -> ", e.toString());
                    }

                    //moviePreview.putExtra("movies", movieList.get(getAdapterPosition()));
                    context.startActivity(recipeDetails);

                }
            });
        }
    }
}
