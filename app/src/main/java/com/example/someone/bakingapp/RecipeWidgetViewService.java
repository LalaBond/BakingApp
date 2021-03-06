package com.example.someone.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.someone.bakingapp.models.IngredientModel;
import com.example.someone.bakingapp.models.RecipeModel;
import com.example.someone.bakingapp.network.DataService;
import com.example.someone.bakingapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by someone on 8/5/18.
 * Handles he tasks of filling up the widget with data
 */


public class RecipeWidgetViewService extends RemoteViewsService {

    private int pos;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        if(intent != null) {

            pos = intent.getIntExtra("pos", 0);

        }
        return new GridRemoteViewsFactory(this.getApplicationContext(), pos);
    }

}

class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private List<RecipeModel> body;
    private Context context;
    private int position;

    public GridRemoteViewsFactory(Context applicationContext, int position) {

        context = applicationContext;
        this.position = position;


        RetrofitCall();
    }

    @Override
    public void onCreate() {

        Log.e("TAG", "ENTERING ONCREATE WIDGET");

    }

    private void RetrofitCall() {
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<List<RecipeModel>> call = service.getRecipes();
        ExecuteClient(call);
    }

    private void ExecuteClient(Call<List<RecipeModel>> call) {

        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {

                body = response.body();
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {

                Log.e("$lala error -> ", t.toString());
            }
        });
    }

    @Override
    public void onDataSetChanged() {
        RetrofitCall();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {

        if(body != null) {
            int x = body.get(0).getIngredients().size();
            return x;
        }else{
         return 0;
        }

    }

    @Override
    public RemoteViews getViewAt(int i) {

        //setting up ingredients of the first list in item
        //rv.setTextViewText(R.id.ingredient,body.get(0).getIngredients().get(i).ingredient);

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_list_item);
        rv.setTextViewText(R.id.ingredient,body.get(position).getIngredients().get(i).ingredient);
        rv.setTextViewText(R.id.quantity, String.valueOf(body.get(position).getIngredients().get(i).quantity));
        rv.setTextViewText(R.id.measure, body.get(position).getIngredients().get(i).measure);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {

            return 1;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
