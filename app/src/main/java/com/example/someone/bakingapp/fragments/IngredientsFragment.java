package com.example.someone.bakingapp.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.someone.bakingapp.R;
import com.example.someone.bakingapp.adapters.IngredientsAdapter;
import com.example.someone.bakingapp.models.RecipeModel;

import java.util.List;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link IngredientsFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
@SuppressLint("ValidFragment")
public class IngredientsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<RecipeModel> recipeModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;


    @SuppressLint("ValidFragment")
    public IngredientsFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment IngredientsFragment.
//     */
    // TODO: Rename and change types and number of parameters
//    public static IngredientsFragment newInstance(String param1, String param2) {
//        IngredientsFragment fragment = new IngredientsFragment(context);
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(context, recipeModel);
        RecyclerView ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        ingredientsRecyclerView.setLayoutManager(layoutManager);

        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        return view;
    }

}
