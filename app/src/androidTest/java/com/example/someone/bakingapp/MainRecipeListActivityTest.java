package com.example.someone.bakingapp;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.someone.bakingapp.adapters.RecipeAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.CursorMatchers.withRowString;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;


/**
 * Created by someone on 8/16/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainRecipeListActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> test = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkRecipeListData() {


            onView(withId(R.id.recipeRecyclerView))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


            onView(withId(R.id.recipeNameTv)).check(matches(withText("Nutella Pie")));



    }





}
