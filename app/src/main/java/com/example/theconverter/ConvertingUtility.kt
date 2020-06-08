package com.example.theconverter

import android.view.inputmethod.EditorInfo
import android.widget.EditText

import org.mariuszgromada.math.mxparser.*

/**
 * Utility class to convert and set units
 * If setListeners is called the class will use the factors to calculate and set the units
 *
 * @param [_fragmentUtility] Utility class to manage a fragment
 * @param [_factorArray] Array with factors for converting from the base value
 * @param [_outputString] String to display unit-value with, % will be replaced with value, if not provided only the value will be printed
 * @param [_toBaseFactorArray] Array with factors for converting to the base value, if not provided the operations of the [_factorArray] will be reversed
 *
 * @author Eli
 */
class ConvertingUtility (private val _fragmentUtility: FragmentUtility, private val _factorArray: Array<String>, private val _outputString: String = "", private val _toBaseFactorArray: Array<String> = arrayOf()) {

    // index = number of textView
    // 1st string = content of editText
    // 2nd string = unit (EditText hint)
    // 3rd string = actListener
    private var _historyArray: Array<Array<String>> = Array(3) {Array(3) {""} }
    private var _historyActivated: Boolean = false

    /**
     * Sets the listeners
     */
    fun setEditTextListener() {

        for ((i, editText) in _fragmentUtility.getEditTexts().withIndex()) {

            editText.setOnEditorActionListener { _, actionId, _ ->
                onEditorAction(editText, actionId)
            }

            editText.setOnFocusChangeListener { _, hasFocus ->
                onFocusChangeListener(i, editText, hasFocus)
            }
        }
    }

    private fun onEditorAction(editText: EditText, actionId: Int): Boolean {

        return if (actionId == EditorInfo.IME_ACTION_DONE) {
            editText.clearFocus()
            _fragmentUtility.hideKeyBoard()

            true
        }
        else {
            false
        }
    }

    private fun onFocusChangeListener(actListener: Int, editText: EditText, hasFocus: Boolean) {

        if (!hasFocus) {

            if (editText.text.isNotEmpty()) {
                setAllValues(getBaseValue(actListener, editText))

                if (_historyActivated) {
                    addToHistory(actListener, editText)
                }
            }
            else {
                clearAllEditText()
            }
        }
        else {
            editText.text.clear()
        }
    }

    private fun getBaseValue(actListener: Int, valueNumber: String): Double {
        val actFactorArray: Array<String>
        val actUnit: Int

        if (actListener > 0) {
            actUnit = actListener - 1
        }
        else {
            return valueNumber.toDouble()
        }

        if (_toBaseFactorArray.isNotEmpty()) {
            actFactorArray = _toBaseFactorArray.copyOf()
        }
        else {
            actFactorArray = _factorArray.copyOf()

            actFactorArray[actUnit] = when (actFactorArray[actUnit][0]) {
                '+' -> actFactorArray[actUnit].replaceFirst('+', '-')
                '-' -> actFactorArray[actUnit].replaceFirst('-', '+')

                '*' -> actFactorArray[actUnit].replaceFirst('*', '/')
                '/' -> actFactorArray[actUnit].replaceFirst('/', '*')

                else -> throw IllegalArgumentException("The factor (${actFactorArray[actUnit]}) doesn't contain a legal operator at the first position")
            }
        }

        return calculate(actFactorArray[actUnit], valueNumber)
    }

    private fun getBaseValue(actListener: Int, editText: EditText): Double {
        return getBaseValue(actListener, editText.text.toString())
    }

    // i used a external library in this function, see its license here: https://github.com/mariuszgromada/MathParser.org-mXparser/blob/master/LICENSE.txt
    private fun calculate(expressionString: String, enteredNumber: String): Double {
        val expression: Expression = Expression(enteredNumber + expressionString)
        return expression.calculate()
    }

    private fun setAllValues(baseValue: Double) {

        for ((i, editText) in _fragmentUtility.getEditTexts().withIndex()) {

            val result = if (i > 0) {
                calculate(_factorArray[i - 1], baseValue.toString()).toString()
            } else {
                baseValue.toString()
            }

            if (_outputString == "") {
                editText.setText(result)
            }
            else {
                editText.setText(_outputString.replace("%", result))
            }
        }
    }

    private fun clearAllEditText() {

        for (editText in _fragmentUtility.getEditTexts()) {
            editText.getText().clear()
        }
    }

    private fun addToHistory(actUnit: Int, valueNumber: String, valueUnit: String) {
        freeFirstSpaceInHistory()

        _historyArray[0][0] = valueNumber
        _historyArray[0][1] = valueUnit
        _historyArray[0][2] = actUnit.toString()

        setHistoryTextViews()
    }

    private fun addToHistory(actUnit: Int, editText: EditText) {
        addToHistory(actUnit, editText.text.toString(), editText.hint.toString())
    }

    private fun freeFirstSpaceInHistory() {

        if (_historyArray.isNullOrEmpty()) {
            return
        }

        var actStringArray: Array<String>
        var lastStringArray: Array<String> = _historyArray[0].copyOf()
        _historyArray[0] = Array(3) {""}

        for (i in _historyArray.indices) {

            if (i < 2) {
                actStringArray = _historyArray[i + 1]
                _historyArray[i + 1] = lastStringArray

                lastStringArray = actStringArray.copyOf()
            }
        }
    }

    private fun setHistoryTextViews() {
        val historyTextViews = _fragmentUtility.getHistoryTextViews()

        for ((i, textView) in historyTextViews.withIndex()) {
            textView.setText("${_historyArray[i][0]} ${_historyArray[i][1]}")
        }
    }

    /**
     * Sets up a history for the given fragment
     * Some text-views have to be set (if standard_layout is included, they're)
     */
    fun setHistoryListener() {
        val historyTextViews = _fragmentUtility.getHistoryTextViews()

        for ((i, textView) in historyTextViews.withIndex()) {

            textView.setOnClickListener {
                val actListener = _historyArray[i][2].toInt()

                setAllValues(getBaseValue(actListener, _historyArray[i][0]))

                if (_historyActivated) {
                    addToHistory(actListener, _historyArray[i][0], _historyArray[i][1])
                }
            }
        }

        _historyActivated = true
    }
}