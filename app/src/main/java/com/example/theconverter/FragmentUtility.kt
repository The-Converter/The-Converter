package com.example.theconverter

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Utility class for Fragments
 * Get and filter children on conditions, soon more
 *
 * @param [fragment] ConstraintLayout (frogment with constraints) to work with
 * @throws [ClassNotFoundException] if no Scroll-View exists in the given ConstraintLayout but is required by a called method
 *
 * @author Eli
 */
class FragmentUtility (private val fragment: ConstraintLayout) {

    // no public properties because getter doesn't take arguments

    private var _cacheEditTextList: MutableList<EditText> = mutableListOf()

    /**
     * Get all EditTexts that are child of the fragment also if they're child of a ScrollView in the fragment
     * List can be filtered by a tag
     * List will be cached, update can be forced
     *
     * @param [forceUpdate] set to true if you want to force an update
     * @param [tag] set a tag to filter by, default is no filter
     *
     * @return [_cacheEditTextList] MutableList of EditTexts
     */
    fun getEditTexts(forceUpdate: Boolean = false, tag: String = ""): MutableList<EditText> {

        if (forceUpdate || _cacheEditTextList.isEmpty()) {
            setEditText(tag)
        }

        return _cacheEditTextList
    }

    private fun setEditText(tag: String) {
        _cacheEditTextList = mutableListOf()
        val childCountFragment = fragment.childCount

        for (i in 0 until childCountFragment) {
            val actChild = fragment.getChildAt(i)

            if (actChild is EditText && (tag == "" || actChild.tag == tag)) {
                _cacheEditTextList.add(actChild)
            }
        }

        val scrollViewNullable: ScrollView? = getScrollView()

        scrollViewNullable?.let { scrollView ->
            val linearLayout: LinearLayout = scrollView.getChildAt(0) as LinearLayout
            val childCountScrollView = linearLayout.childCount

            for (i in 0 until childCountScrollView) {
                val actChild = linearLayout.getChildAt(i)

                if (actChild is EditText && (tag == "" || actChild.tag == tag)) {
                    _cacheEditTextList.add(actChild)
                }
            }
        }
    }

    private fun getScrollView(): ScrollView? {
        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i) is ScrollView) {
                return fragment.getChildAt(i) as ScrollView
            }
        }

        return null
    }

    /**
     * Sets up a onclick listener to perform a navigation
     *
     * @param [view] View, needed to get ImageButton
     * @param [navController] NavController, needed for navigation
     * @param [action] where to navigate to
     * @param [id] id of the image button, standard is imageButton_choose
     */
    fun setReturnButton(view: View, navController: NavController, action: NavDirections, id: Int = R.id.imageButton_choose) {

        view.findViewById<ImageButton>(id).setOnClickListener {
            navController.navigate(action)
        }
    }
}