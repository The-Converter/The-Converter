package com.example.theconverter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

/**
 * Utility class for Fragments
 * Get and filter children on conditions, soon more
 *
 * @param [_fragment] ConstraintLayout (frogment with constraints) to work with
 * @param [_context] Context
 *
 * @author Eli
 */
class FragmentUtility (private val _fragment: ConstraintLayout, private val _context: Context?) {

    // no public properties because getter doesn't take arguments

    private var _cacheEditTextList: MutableList<EditText> = mutableListOf()
    private var _cacheHistoryTextViewList: MutableList<TextView> = mutableListOf()

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

        checkViewGroupChildren(_fragment, tag)

        val scrollViewNullable: ScrollView? = getScrollView()

        scrollViewNullable?.let { scrollView ->
            val linearLayout: LinearLayout = scrollView.getChildAt(0) as LinearLayout
            checkViewGroupChildren(linearLayout, tag)
        }
    }

    private fun checkViewGroupChildren(viewGroup: ViewGroup, tag: String) {
        val childCount = viewGroup.childCount

        for (i in 0 until childCount) {
            val actChild = viewGroup.getChildAt(i)

            if (actChild is EditText && (tag == "" || actChild.tag == tag)) {
                _cacheEditTextList.add(actChild)
            }
        }
    }

    private fun getScrollView(): ScrollView? {
        val childCount = _fragment.childCount

        for (i in 0 until childCount) {
            if (_fragment.getChildAt(i) is ScrollView) {
                return _fragment.getChildAt(i) as ScrollView
            }
        }

        return null
    }

    /**
     * Sets up a onclick listener to perform a navigation
     *
     * @param [action] where to navigate to
     * @param [id] id of the image button, standard is imageButton_choose
     */
    fun setReturnButton(action: NavDirections, id: Int = R.id.imageButton_choose) {

        _fragment.findViewById<View>(id).setOnClickListener {
            _fragment.findNavController().navigate(action)
        }
    }

    /**
     * Hides the soft-keyboard from the screen
     */
    fun hideKeyBoard() {
        _context?.let {context ->
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(_fragment.windowToken, 0)
        }
    }

    /**
     * Get the TextViews used for the converting-history
     * List will be cached, update can be forced
     *
     * @param [forceUpdate] set to true if you want to force an update
     * @return [_cacheHistoryTextViewList] Mutable list of history-textViews
     */
    fun getHistoryTextViews(forceUpdate: Boolean = false): MutableList<TextView> {

        if (forceUpdate || _cacheHistoryTextViewList.isEmpty()) {

            _cacheHistoryTextViewList.add(_fragment.findViewById(R.id.textView_history1))
            _cacheHistoryTextViewList.add(_fragment.findViewById(R.id.textView_history2))
            _cacheHistoryTextViewList.add(_fragment.findViewById(R.id.textView_history3))
        }

        return _cacheHistoryTextViewList
    }
}