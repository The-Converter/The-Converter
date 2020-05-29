package com.example.theconverter

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Utility class for Fragments
 * Get and filter childs on conditions, soon more
 *
 * @param [fragment] ConstraintLayout (frogment with constraints) to work with
 * @throws [ClassNotFoundException] if no Scroll-View exists in the given ConstraintLayout but is required by a called method
 *
 * @author Eli
 */
class FragmentUtility (private val fragment: ConstraintLayout) {

    // no public properties because getter doesn't take arguments

    private var _editTextList: MutableList<EditText> = mutableListOf()

    private var _editTextFromScrollViewList: MutableList<EditText> = mutableListOf()

    private var _viewsByTagList: MutableList<View> = mutableListOf()
    private var _editTextByTagList: MutableList<EditText> = mutableListOf()

    private var _viewsByTagFromScrollViewList: MutableList<View> = mutableListOf()
    private var _editTextByTagFromScrollViewList: MutableList<EditText> = mutableListOf()

    /**
     * Get all EditTexts that are child of the fragment
     * MutableList gets cached but a update can be forced
     *
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_editTextList] MutableList of EditTexts
     */
    fun getAllEditText(forceUpdate: Boolean = false): MutableList<EditText> {

        if (_editTextList.isEmpty() || forceUpdate) {
            _editTextList = mutableListOf()
            val childCount = fragment.childCount

            for (i in 0 until childCount) {
                if (fragment.getChildAt(i) is EditText) {
                    _editTextList.add(fragment.getChildAt(i) as EditText)
                }
            }
        }

        return _editTextList
    }

    /**
     * Get all EditTexts that are child of the ScrollView in the fragment
     * MutableList gets cached but a update can be forced
     *
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_editTextFromScrollViewList] MutableList of EditTexts contained by the ScrollView
     */
    fun getAllEditTextFromScrollView(forceUpdate: Boolean = false): MutableList<EditText> {

        if (_editTextFromScrollViewList.isEmpty() || forceUpdate) {
            _editTextFromScrollViewList = mutableListOf()

            val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
            val childCount = linearLayout.childCount

            for (i in 0 until childCount) {
                if (linearLayout.getChildAt(i) is EditText) {
                    _editTextFromScrollViewList.add(linearLayout.getChildAt(i) as EditText)
                }
            }
        }

        return _editTextFromScrollViewList
    }

    private fun getScrollView(): ScrollView {
        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i) is ScrollView) {
                return fragment.getChildAt(i) as ScrollView
            }
        }

        throw ClassNotFoundException("Couldn't find a ScrollView in $fragment")
    }

    /**
     * Get all views that are child of the fragment, filtered by a tag
     * Only returns the cached list use overload with string to force a update
     *
     * @return [_viewsByTagList] Cached MutableList of Views in the fragment, filtered by Tag
     */
    fun getViewsByTag(): MutableList<View> {
        return _viewsByTagList
    }

    /**
     * Get all views that are child of the fragment, filtered by a tag
     * Always creates a new list
     *
     * @param [tag] tag to filter by
     * @return [_viewsByTagList] New MutableList of Views in the fragment, filtered by Tag
     */
    fun getViewsByTag(tag: String): MutableList<View> {
        _viewsByTagList = mutableListOf()
        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i).tag == tag) {
                _viewsByTagList.add(fragment.getChildAt(i))
            }
        }

        return _viewsByTagList
    }

    /**
     * Get all views that are child of the ScrollView in the fragment, filtered by a tag
     * Only returns the cached list use overload with string to force a update
     *
     * @return [_viewsByTagFromScrollViewList] Cached MutableList of Views contained by the ScrollView, filtered by Tag
     */
    fun getViewsByTagFromScrollView(): MutableList<View> {
        return _viewsByTagFromScrollViewList
    }

    /**
     * Get all views that are child of the ScrollView in the fragment, filtered by a tag
     * Always creates a new list
     *
     * @param [tag] tag to filter by
     * @return [_viewsByTagFromScrollViewList] New MutableList of Views contained by the ScrollView, filtered by Tag
     */
    fun getViewsByTagFromScrollView(tag: String): MutableList<View> {
        _viewsByTagFromScrollViewList = mutableListOf()

        val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
        val childCount = linearLayout.childCount

        for (i in 0 until childCount) {
            if (linearLayout.getChildAt(i).tag == tag) {
                _viewsByTagFromScrollViewList.add(linearLayout.getChildAt(i))
            }
        }
        return _viewsByTagFromScrollViewList
    }

    /**
     * Get all EditTexts that are child of the fragment, filtered by a tag
     * Only returns the cached list use overload with string to force a update
     *
     * @return [_editTextByTagList] Cached MutableList of EditTexts in the fragment, filtered by Tag
     */
    fun getEditTextByTag(): MutableList<EditText> {
        return _editTextByTagList
    }

    /**
     * Get all EditTexts that are child of the fragment, filtered by a tag
     * Always creates a new list
     *
     * @param [tag] tag to filter by
     * @return [_editTextByTagList] New MutableList of EditTexts in the fragment, filtered by Tag
     */
    fun getEditTextByTag(tag: String): MutableList<EditText> {
        _editTextByTagList = mutableListOf()

        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i).tag == tag && fragment.getChildAt(i) is EditText) {
                _editTextByTagList.add(fragment.getChildAt(i) as EditText)
            }
        }
        return _editTextByTagList
    }

    /**
     * Get all EditTexts that are child of the ScrollView in the fragment, filtered by a tag
     * Only returns the cached list use overload with string to force a update
     *
     * @return [_editTextByTagFromScrollViewList] Cached MutableList of EditTexts contained by the Scrollview, filtered by Tag
     */
    fun getEditTextsByTagFromScrollView(): MutableList<EditText> {
        return _editTextByTagFromScrollViewList
    }

    /**
     * Get all EditTexts that are child of the ScrollView in the fragment, filtered by a tag
     * Always creates a new list
     *
     * @param [tag] tag to filter by
     * @return [_editTextFromScrollViewList] New MutableList of EditTexts contained by the Scrollview, filtered by Tag
     */
    fun getEditTextsByTagFromScrollView(tag: String): MutableList<EditText> {
        _editTextByTagFromScrollViewList = mutableListOf()

        val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
        val childCount = linearLayout.childCount

        for (i in 0 until childCount) {
            if (linearLayout.getChildAt(i).tag == tag && linearLayout.getChildAt(i) is EditText) {
                _editTextByTagFromScrollViewList.add(linearLayout.getChildAt(i) as EditText)
            }
        }

        return _editTextByTagFromScrollViewList
    }
}