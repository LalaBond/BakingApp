package com.example.someone.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.someone.bakingapp.R;
import com.example.someone.bakingapp.adapters.RecipeAdapter;
import com.example.someone.bakingapp.models.RecipeModel;
import com.example.someone.bakingapp.network.DataService;
import com.example.someone.bakingapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/*fragment used to populate tablet two pane view*/
public class ListFragment extends Fragment {


    private Context context;
    private View view;

    public ListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        context = getActivity().getApplicationContext();

        RetrofitCall();

        return view;
    }

    private void RetrofitCall() {
        DataService service = RetrofitClient.getRetrofitInstance().create(DataService.class);
        Call<List<RecipeModel>> call = service.getRecipes();
        ExecuteClient(call);
    }

    /*setting up recipe adapter*/
    private void createAdapter(List<RecipeModel> body) {


        ViewPager pager = getActivity().findViewById(R.id.pager);
        RecipeAdapter adapter = new RecipeAdapter(context, body, true, view, getActivity().getSupportFragmentManager(), pager);

        RecyclerView recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView);

        /*Divider for recycler view*/
        DividerItemDecoration listDivider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        listDivider.setDrawable((ContextCompat.getDrawable(context, R.drawable.list_divider)));

        recipeRecyclerView.addItemDecoration(listDivider);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recipeRecyclerView.setLayoutManager(layoutManager);

        recipeRecyclerView.setAdapter(adapter);


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

}
