package com.example.someone.bakingapp;

import android.content.res.Resources;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;


/**
 * Created by someone on 8/16/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainRecipeListActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> test = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkListClicks() {

        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //intended(hasComponent(RecipeDetailsActivity.class.getName()));
        //intended(hasComponent(new ComponentName(getTargetContext(), RecipeDetailsActivity.class)));
    }


    @Test
    //Check each recycler view item to match with correct data
    public void checkListData(){

        for (int position = 0; position < 4; position++) {
            for (int subposition = 0; subposition < 4; subposition++) {

                if (subposition == position) {

                    onView(atPositionOnView(subposition, -1, R.id.recipeRecyclerView)).
                            check(matches(hasDescendant(withText(getRecipeNameAtPosition(position)))));
                }
                else {
                    //data doesnt match
                    onView(atPositionOnView(subposition, -1, R.id.recipeRecyclerView)).
                            check(matches(not(hasDescendant(withText(getRecipeNameAtPosition(position))))));
                }
            }
        }

    }


    private String getRecipeNameAtPosition(int position) {

        String recipeName = "";
        switch(position){
            case 0:
                recipeName = "Nutella Pie";
                break;
            case 1:
                recipeName = "Brownies";
                break;
            case 2:
                recipeName = "Yellow Cake";
                break;
            case 3:
                recipeName = "Cheesecake";
                break;

            default:
                break;

        }
        return recipeName;
    }


    // Reference github https://github.com/dannyroa/espresso-samples
    private static Matcher<View> atPositionOnView(final int position, final int targetViewId, final int recyclerViewId) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {

            }

            Resources resources = null;
            View childView = null;

            @Override
            protected boolean matchesSafely(View item) {
                resources = item.getResources();

                if (childView == null) {
                    RecyclerView recyclerView = item.getRootView().findViewById(R.id.recipeRecyclerView);

                    if (recyclerView != null && recyclerView.getId() == R.id.recipeRecyclerView) {
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                        if (viewHolder != null) {
                            childView = viewHolder.itemView;
                        }
                    } else {
                        return false;
                    }
                }

                if (targetViewId == -1) {
                    return item == childView;
                } else {
                    View targetView = childView.findViewById(targetViewId);
                    return item == targetView;
                }
            }

        };
    }
}
