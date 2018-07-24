package org.secfirst.umbrella

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.forkingcode.espresso.contrib.DescendantViewActions.checkViewAction
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.MainActivity
import org.secfirst.umbrella.feature.form.view.controller.FormController


@RunWith(AndroidJUnit4::class)
class FormControllerTest {

    private lateinit var router: Router
    private val form: Form = Form()

    @Rule
    @JvmField
    var testRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)


    @Before
    fun setUp() {
        testRule.runOnUiThread {
            router = testRule.activity.getRouter()
            router.setRoot(RouterTransaction.with(FormController()))
        }
    }

    @Test
    fun should_return_valid_title_for_all_forms() {
        onView(withId(R.id.titleAllForm)).check(matches(
                withText(R.string.message_title_all_list_form)))
    }

    @Test
    fun should_return_valid_title_for_active_forms() {
        onView(withId(R.id.titleActiveForm)).check(matches(
                withText(R.string.message_title_active_list_form)))
    }

    @Test
    fun one_item_is_necessary_in_list() {
        onView(withId(R.id.allFormRecycleView))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1,
                        checkViewAction(matches(isCompletelyDisplayed()))))
    }

    @Test
    fun click_on_item_and_open_form_detail() {
        onView(withId(R.id.allFormRecycleView)).perform(

                // First position the recycler view. Necessary to allow the layout
                // manager perform the scroll operation
                scrollToPosition<RecyclerView.ViewHolder>(1),

                // Click the item to trigger navigation to detail view
                actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )

        // Check detail view
        onView(withId(R.id.navigation)).check(matches(not(isDisplayed())))

        // return to main activity
        pressBack()
    }

    @Test
    fun menu_should_be_visible_in_form_controller() {
        onView(withId(R.id.navigation)).check(matches(isDisplayed()))

    }
}