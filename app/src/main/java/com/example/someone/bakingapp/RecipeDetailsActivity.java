package com.example.someone.bakingapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.someone.bakingapp.adapters.IngredientsAdapter;
import com.example.someone.bakingapp.fragments.IngredientsFragment;
import com.example.someone.bakingapp.fragments.RecipeStepsFragment;
import com.example.someone.bakingapp.models.RecipeModel;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //GET DATA OF BUNDLE

        RecipePagerAdapter pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager(), this);
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

//        ActionBar actionBar = getActionBar();
//
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//        // Create a tab listener that is called when the user changes tabs.
//        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//                // show the given tab
//            }
//
//            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//                // hide the given tab
//            }
//
//            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//                // probably ignore this event
//            }
//        };
//
//        // Add 3 tabs, specifying the tab's text and TabListener
//        for (int i = 0; i < 2; i++) {
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText("Tab " + (i + 1))
//                            .setTabListener(tabListener));
//        }

    }
}

class RecipePagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public RecipePagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
               return new IngredientsFragment(context);
           case 1:
               return new RecipeStepsFragment();
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:

                return context.getString(R.string.ingredientsString);
            case 1:
                return context. getString(R.string.stepsString);
            default:
                return null;
        }
    }

//    public static class DemoObjectFragment extends Fragment {
//        public static final String ARG_OBJECT = "object";
//
//        @Override
//        public View onCreateView(LayoutInflater inflater,
//                                 ViewGroup container, Bundle savedInstanceState) {
//            // The last two arguments ensure LayoutParams are inflated
//            // properly.
//            View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
//            Bundle args = getArguments();
//            ((TextView) rootView.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
//            return rootView;
//        }
    //}
}
