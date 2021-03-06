package com.example.someone.bakingapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.someone.bakingapp.adapters.RecipeAdapter;
import com.example.someone.bakingapp.models.RecipeModel;
import com.example.someone.bakingapp.network.DataService;
import com.example.someone.bakingapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private boolean twoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.tablet_linear_layout) != null) {

            //if its a two pane layout fragment activity will be called (ListFragment)
            twoPane = true;

        } else {
            //populate phone if its not a two pane layout.  Continue with calls
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });


            //creating call using client to get recipe data
            try {
                RetrofitCall();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("$lala error -> ", e.toString());
            }
        }
    }

    private void RetrofitCall() {
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<List<RecipeModel>> call = service.getRecipes();
        ExecuteClient(call);
    }

    private void ExecuteClient(Call<List<RecipeModel>> call) {

        try {
            call.enqueue(new Callback<List<RecipeModel>>() {

                @Override
                public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                    Log.e("$lala SUCCESS -> ", "SUCCESS");
                    createAdapter(response.body());
                }

                @Override
                public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                    Log.e("$lala ERROR -> ", t.toString());
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAdapter(List<RecipeModel> body) {

        RecipeAdapter adapter = new RecipeAdapter(this, body, twoPane);

        RecyclerView recipeRecyclerView = findViewById(R.id.recipeRecyclerView);

        /*Divider for recycler view*/
        DividerItemDecoration listDivider = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        listDivider.setDrawable((ContextCompat.getDrawable(this, R.drawable.list_divider)));

        recipeRecyclerView.addItemDecoration(listDivider);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recipeRecyclerView.setLayoutManager(layoutManager);

        recipeRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
