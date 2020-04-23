package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListFavorisNeighboursAdded {

    private static int ITEMS_COUNT = 0;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void listFavorisNeighboursAdded() {
        //Clique sur le voisin à la position 0 de la liste
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),isDisplayed(),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        //Le nom du voisin selectionné est bien affiché
        ViewInteraction textView = onView(
                allOf(withId(R.id.detail_title), withText("Caroline"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar2),
                                        0),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("Caroline")));
        //Clique sur le bouton de favoris
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.favorite_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());
        //Clique sur le bouton de retour à l'activité précédente
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.button_back),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar2),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        //Clique sur l'onglet favoris
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());
        //Au clique sur l'onglet, je switch de liste
        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        viewPager.perform(swipeLeft());
        //Je vérifie que le nom du voisin est le même que celui cliqué dans le détail
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_list_name), withText("Caroline"),
                        childAtPosition(
                                allOf(withId(R.id.fragment_details),
                                        childAtPosition(
                                                withId(R.id.list_neighbours),
                                                0)),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Caroline")));
        //Je clique sur le favoris de la liste de favoris
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.list_neighbours),isDisplayed(),
                        withParent(withId(R.id.container))));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));
        //Clique sur le bouton favoris
        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.favorite_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());
        //Clique sur le bouton back
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.button_back),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar2),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        onView(AllOf.allOf(ViewMatchers.withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT-0));
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
