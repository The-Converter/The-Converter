package com.example.theconverter

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

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

    private var _cacheViewList: MutableList<View> = mutableListOf()
    private var _cacheEditTextList: MutableList<EditText> = mutableListOf()

    private var _cachedListId: String = ""

    /**
     * Get all EditTexts that are child of the fragment
     * MutableList gets cached but a update can be forced
     *
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_editTextList] MutableList of EditTexts
     */
    fun getAllEditText(forceUpdate: Boolean = false): MutableList<EditText> {

        val listId: String = "AllEditText"

        if (_cachedListId != listId || forceUpdate) {
            _cachedListId = listId
            _cacheEditTextList = mutableListOf()

            val childCount = fragment.childCount

            for (i in 0 until childCount) {
                if (fragment.getChildAt(i) is EditText) {
                    _cacheEditTextList.add(fragment.getChildAt(i) as EditText)
                }
            }
        }

        return _cacheEditTextList
    }

    /**
     * Get all EditTexts that are child of the ScrollView in the fragment
     * MutableList gets cached but a update can be forced
     *
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_editTextFromScrollViewList] MutableList of EditTexts contained by the ScrollView
     */
    fun getAllEditTextFromScrollView(forceUpdate: Boolean = false): MutableList<EditText> {

        val listId: String = "AllEditTextFromScrollView"

        if (_cachedListId != listId || forceUpdate) {
            _cachedListId = listId

            _cacheEditTextList = mutableListOf()

            val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
            val childCount = linearLayout.childCount

            for (i in 0 until childCount) {
                if (linearLayout.getChildAt(i) is EditText) {
                    _cacheEditTextList.add(linearLayout.getChildAt(i) as EditText)
                }
            }
        }

        return _cacheEditTextList
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
     * MutableList gets cached but a update can be forced
     *
     * @param [tag] tag to filter by, can be left empty if the last request was to this function
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_cacheViewList] New MutableList of Views in the fragment, filtered by Tag
     */
    fun getViewsByTag(tag: String = "", forceUpdate: Boolean = false): MutableList<View> {

        val listId: String = "ViewsByTag"

        if ((_cachedListId != listId || forceUpdate) && tag != "") {
            _cachedListId = listId

            _cacheViewList = mutableListOf()
            val childCount = fragment.childCount

            for (i in 0 until childCount) {
                if (fragment.getChildAt(i).tag == tag) {
                    _cacheViewList.add(fragment.getChildAt(i))
                }
            }
        }

        if (_cachedListId == listId) {
            return _cacheViewList
        }
        else {
            return mutableListOf()
        }
    }

    /**
     * Get all views that are child of the ScrollView in the fragment, filtered by a tag
     * MutableList gets cached but a update can be forced
     *
     * @param [tag] tag to filter by, can be left empty if the last request was to this function
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_viewsByTagFromScrollViewList] New MutableList of Views contained by the ScrollView, filtered by Tag
     */
    fun getViewsByTagFromScrollView(tag: String = "", forceUpdate: Boolean = false): MutableList<View> {

        val listId: String = "ViewsByTagFromScrollView"

        if ((_cachedListId != listId || forceUpdate) && tag != "") {
            _cachedListId = listId

            _cacheViewList = mutableListOf()

            val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
            val childCount = linearLayout.childCount

            for (i in 0 until childCount) {
                if (linearLayout.getChildAt(i).tag == tag) {
                    _cacheViewList.add(linearLayout.getChildAt(i))
                }
            }
        }

        if (_cachedListId == listId) {
            return _cacheViewList
        }
        else {
            return mutableListOf()
        }
    }

    /**
     * Get all EditTexts that are child of the fragment, filtered by a tag
     * MutableList gets cached but a update can be forced
     *
     * @param [tag] tag to filter by, can be left empty if the last request was to this function
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_editTextByTagList] New MutableList of EditTexts in the fragment, filtered by Tag
     */
    fun getEditTextByTag(tag: String = "", forceUpdate: Boolean = false): MutableList<EditText> {

        val listId: String = "EditTextsByTag"

        if ((_cachedListId != listId || forceUpdate) && tag != "") {
            _cachedListId = listId

            _cacheEditTextList = mutableListOf()
            val childCount = fragment.childCount

            for (i in 0 until childCount) {
                if (fragment.getChildAt(i).tag == tag && fragment.getChildAt(i) is EditText) {
                    _cacheEditTextList.add(fragment.getChildAt(i) as EditText)
                }
            }
        }

        if (_cachedListId == listId) {
            return _cacheEditTextList
        }
        else {
            return mutableListOf()
        }
    }

    /**
     * Get all EditTexts that are child of the ScrollView in the fragment, filtered by a tag
     * MutableList gets cached but a update can be forced
     *
     * @param [tag] tag to filter by, can be left empty if the last request was to this function
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_editTextFromScrollViewList] New MutableList of EditTexts contained by the Scrollview, filtered by Tag
     */
    fun getEditTextsByTagFromScrollView(tag: String = "", forceUpdate: Boolean = false): MutableList<EditText> {

        val listId: String = "EditTextsByTagFromScrollView"

        if ((_cachedListId != listId || forceUpdate) && tag != "") {
            _cachedListId = listId

            _cacheEditTextList = mutableListOf()

            val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
            val childCount = linearLayout.childCount

            for (i in 0 until childCount) {
                if (linearLayout.getChildAt(i).tag == tag && linearLayout.getChildAt(i) is EditText) {
                    _cacheEditTextList.add(linearLayout.getChildAt(i) as EditText)
                }
            }
        }

        if (_cachedListId == "EditTextsByTagFromScrollView") {
            return _cacheEditTextList
        }
        else {
            return mutableListOf()
        }
    }
}