package com.example.theconverter

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class FragmentUtility (private val fragment: ConstraintLayout) {

    // no public properties because getter doesn't take arguments

    private var _editTextList: MutableList<EditText> = mutableListOf()

    private var _editTextFromScrollViewList: MutableList<EditText> = mutableListOf()

    private var _viewsByTagList: MutableList<View> = mutableListOf()
    private var _editTextByTagList: MutableList<EditText> = mutableListOf()

    private var _viewsByTagFromScrollViewList: MutableList<View> = mutableListOf()
    private var _editTextByTagFromScrollViewList: MutableList<EditText> = mutableListOf()

    fun getAllEditText(forceUpdate: Boolean = false): MutableList<EditText> {

        if (_editTextList.isEmpty() || forceUpdate) {
            _editTextList = setAllEditText()
        }

        return _editTextList
    }

    private fun setAllEditText(): MutableList<EditText> {
        val editTextList: MutableList<EditText> = mutableListOf()
        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i) is EditText) {
                editTextList.add(fragment.getChildAt(i) as EditText)
            }
        }

        return editTextList
    }

    fun getAllEditTextFromScrollView(forceUpdate: Boolean = false): MutableList<EditText> {

        if (_editTextFromScrollViewList.isEmpty() || forceUpdate) {
            _editTextFromScrollViewList = setAllEditTextFromScrollView()
        }

        return _editTextFromScrollViewList
    }

    private fun setAllEditTextFromScrollView(): MutableList<EditText> {
        val editTextList: MutableList<EditText> = mutableListOf()

        val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
        val childCount = linearLayout.childCount

        for (i in 0 until childCount) {
            if (linearLayout.getChildAt(i) is EditText) {
                editTextList.add(linearLayout.getChildAt(i) as EditText)
            }
        }

        return editTextList
    }

    private fun getScrollView(): ScrollView {
        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i) is ScrollView) {
                return fragment.getChildAt(i) as ScrollView
            }
        }

        throw NoSuchFieldException("Couldn't find a ScrollView in $fragment")
    }

    fun getViewsByTag(): MutableList<View> {
        return _viewsByTagList
    }

    fun getViewsByTag(tag: String): MutableList<View> {
        _viewsByTagList = setViewsByTag(tag)
        return _viewsByTagList
    }

    private fun setViewsByTag(tag: String): MutableList<View> {
        val viewList: MutableList<View> = mutableListOf()
        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i).tag == tag) {
                viewList.add(fragment.getChildAt(i))
            }
        }

        return viewList
    }

    fun getViewsByTagFromScrollView(): MutableList<View> {
        return _viewsByTagFromScrollViewList
    }

    fun getViewsByTagFromScrollView(tag: String): MutableList<View> {
        _viewsByTagFromScrollViewList = setViewsByTagFromScrollView(tag)
        return _viewsByTagFromScrollViewList
    }

    private fun setViewsByTagFromScrollView(tag: String): MutableList<View> {
        val viewList: MutableList<View> = mutableListOf()

        val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
        val childCount = linearLayout.childCount

        for (i in 0 until childCount) {
            if (linearLayout.getChildAt(i).tag == tag) {
                viewList.add(linearLayout.getChildAt(i))
            }
        }

        return viewList
    }

    fun getEditTextByTag(): MutableList<EditText> {
        return _editTextByTagList
    }

    fun getEditTextByTag(tag: String): MutableList<EditText> {
        _editTextByTagList = setEditTextsByTag(tag)
        return _editTextByTagList
    }

    private fun setEditTextsByTag(tag: String): MutableList<EditText> {
        val editTextList: MutableList<EditText> = mutableListOf()
        val childCount = fragment.childCount

        for (i in 0 until childCount) {
            if (fragment.getChildAt(i).tag == tag && fragment.getChildAt(i) is EditText) {
                editTextList.add(fragment.getChildAt(i) as EditText)
            }
        }

        return editTextList
    }

    fun getEditTextsByTagFromScrollView(): MutableList<EditText> {
        return _editTextByTagFromScrollViewList
    }

    fun getEditTextsByTagFromScrollView(tag: String): MutableList<EditText> {
        _editTextByTagFromScrollViewList = setEditTextsByTagFromScrollView(tag)
        return _editTextByTagFromScrollViewList
    }

    private fun setEditTextsByTagFromScrollView(tag: String): MutableList<EditText> {
        val editTextList: MutableList<EditText> = mutableListOf()

        val linearLayout: LinearLayout = getScrollView().getChildAt(0) as LinearLayout
        val childCount = linearLayout.childCount

        for (i in 0 until childCount) {
            if (linearLayout.getChildAt(i).tag == tag && linearLayout.getChildAt(i) is EditText) {
                editTextList.add(linearLayout.getChildAt(i) as EditText)
            }
        }

        return editTextList
    }
}