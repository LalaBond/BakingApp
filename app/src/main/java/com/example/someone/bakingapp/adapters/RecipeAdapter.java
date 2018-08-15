package com.example.someone.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by someone on 7/18/18.
 */

/*Adapter to display recipes in the Main Activity*/
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<RecipeModel> recipes = new ArrayList<>();
    private Context context;
    private boolean twoPane;

    public RecipeAdapter(Context context, List<RecipeModel> body, boolean twoPane) {

        recipes = body;
        this.context = context;
        this.twoPane = twoPane;
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        LinearLayout itemLinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.name);
            itemLinearLayout = itemView.findViewById(R.id.itemlinearLayout);

            itemLinearLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    if (twoPane) {
                        try {
                            RecipePagerAdapter pagerAdapter = new RecipePagerAdapter(((AppCompatActivity) context).getSupportFragmentManager(), context, recipes.get(getAdapterPosition()));

                            System.out.println("lala get aparent! " + ((AppCompatActivity) context).getParent());
                            ViewPager pager = view.findViewById(R.id.pager);
                            pager.setAdapter(pagerAdapter);
                        } catch (Exception e) {
                           Log.e("$lala error -> ", e.toString());
                        }

                    } else {
                        Intent recipeDetails = null;
                        try {

                            recipeDetails = new Intent(context, RecipeDetailsActivity.class);
                            recipeDetails.putExtra("recipe", recipes.get(getAdapterPosition()));
                            recipeDetails.putExtra("twoPane", twoPane);

                            context.startActivity(recipeDetails);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("$lala error -> ", e.toString());
                        }
                    }

                }
            });
        }
    }
}
