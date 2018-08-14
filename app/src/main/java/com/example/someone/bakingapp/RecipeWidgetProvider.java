package com.example.someone.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.someone.bakingapp.models.RecipeModel;
import com.example.someone.bakingapp.network.DataService;
import com.example.someone.bakingapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    private static RemoteViews views;

    @Override
    /*called within intervals defined in updatePeriodMillis.  Also called when user adds widget*/
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            try {

                // Create an Intent to launch ExampleActivity
                Intent intent = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
                Intent serviceIntent = new Intent(context, RecipeWidgetViewService.class);

                views.setRemoteAdapter(R.id.widget_ingredient_list, serviceIntent);
                views.setOnClickPendingIntent(R.id.button, pendingIntent);

                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views);

            }catch(Exception e){

                Log.e("$lala error -> ", e.toString());
            }

//            Intent intent = new Intent(context, RecipeWidgetViewService.class);
//            views.setRemoteAdapter(R.id.widget_ingredient_list, intent);

        }

    }

//    public static void sendRefreshBroadcast(Context context) {
//        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//        intent.setComponent(new ComponentName(context, RecipeWidgetProvider.class));
//        context.sendBroadcast(intent);
//    }



    @Override
    /*This is called when an instance the App Widget is created for the first time.*/
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        super.onEnabled(context);
    }

    @Override
    /*This is called when the last instance of your App Widget is deleted from the App Widget host. */
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);
    }

//    @Override
//    /*This is called for every broadcast and before each of the above callback methods.*/
//    public void onReceive(final Context context, Intent intent) {
//        final String action = intent.getAction();
//        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
//            // refresh all your widgets
//            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
//            ComponentName cn = new ComponentName(context, RecipeWidgetProvider.class);
//            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widget_ingredient_list);
//        }
//        super.onReceive(context, intent);
//    }

}

