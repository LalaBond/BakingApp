package com.example.someone.bakingapp.adapters;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.someone.bakingapp.R;
import com.example.someone.bakingapp.RecipeDetailsActivity;
import com.example.someone.bakingapp.RecipeWidgetProvider;
import com.example.someone.bakingapp.RecipeWidgetViewService;
import com.example.someone.bakingapp.models.RecipeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by someone on 7/18/18.
 */

/*Adapter to display recipes in the Main Activity*/
public class ConfigurationActivityAdapter extends RecyclerView.Adapter<ConfigurationActivityAdapter.ViewHolder> {

    private List<RecipeModel> recipes = new ArrayList<>();
    private Context context;
    private int mAppWidgetId;

    public ConfigurationActivityAdapter(Context context, List<RecipeModel> body, int mAppWidgetId) {

        recipes = body;
        this.context = context;
        this.mAppWidgetId = mAppWidgetId;
    }

    @Override
    public ConfigurationActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipes_item_layout, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConfigurationActivityAdapter.ViewHolder holder, int position) {

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

                    try {

                    AppWidgetManager widgetmanager = AppWidgetManager.getInstance(context);

                    //obtain recipe name and ingredients then finish configuration activity
                    RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
                    widgetView.setTextViewText(R.id.widget_recipe_text, recipes.get(getAdapterPosition()).getName());

                        Intent intent = new Intent(context, RecipeWidgetViewService.class);
                        //intent.putExtra("ingredients", recipes.get(getAdapterPosition()));

                        //intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
                        widgetView.setRemoteAdapter(R.id.widget_ingredient_list, intent);
                        widgetmanager.updateAppWidget(mAppWidgetId, widgetView);
                        ((Activity)context).finish();

                    } catch (Exception e) {
                        Log.e("$lala error -> ", e.toString());
                    }

                }
           });
        }
    }
}
