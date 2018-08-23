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
import com.example.someone.bakingapp.StepDetailsActivity;
import com.example.someone.bakingapp.models.RecipeModel;

/**
 * Created by someone on 7/24/18.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>  {

    private RecipeModel recipeModel;
    private Context context;

    public StepsAdapter(Context context, RecipeModel recipeModel) {

        this.recipeModel = recipeModel;
        this.context = context;
    }


    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.steps_item_layout, parent, false);

        return new StepsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.stepNumberTv.setText(String.valueOf(position));
        holder.shortDescriptionTv.setText(recipeModel.getSteps().get(position).shortDescription);

    }

    @Override
    public int getItemCount() {
        return recipeModel.getSteps().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView stepNumberTv, shortDescriptionTv;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            stepNumberTv = itemView.findViewById(R.id.stepNumberTv);
            shortDescriptionTv = itemView.findViewById(R.id.shortDescriptionTv);
            linearLayout = itemView.findViewById(R.id.linearLayout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                           try {
                               Intent intent = new Intent(context, StepDetailsActivity.class);
                               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                               intent.putExtra("recipe", recipeModel.getSteps().get((getAdapterPosition())));
                               context.startActivity(intent);
                           } catch (Exception e) {
                               e.printStackTrace();
                               Log.e("$lala error -> ", e.toString());
                           }

                       }
            }
            );
        }
    }
}
