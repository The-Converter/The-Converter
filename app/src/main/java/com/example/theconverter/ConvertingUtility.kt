package com.example.theconverter

import android.view.inputmethod.EditorInfo
import android.widget.EditText

import org.mariuszgromada.math.mxparser.*

/**
 * Utility class to convert and set units
 * If setListeners is called the class will use the factors to calculate and set the units
 *
 * @param [editTexts] MutableList with all EditTexts that are used to enter/display unit values
 * @param [factorArray] Array with factors for converting from the base value
 * @param [outputString] String to display unit-value with, $ will be replaced with value, if not provided only the value will be printed
 * @param [toBaseFactorArray] Array with factors for converting to the base value, if not provided the operations of the [factorArray] will be reversed
 *
 * @author Eli
 */
class ConvertingUtility (private val editTexts: MutableList<EditText>, private val factorArray: Array<String>, private val outputString: String = "", private val toBaseFactorArray: Array<String> = arrayOf()) {

    fun setListeners() {

        for ((i, editText) in editTexts.withIndex()) {

            editText.setOnEditorActionListener { _, actionId, _ ->

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    editText.clearFocus()
                    true
                }
                else {
                    false
                }
            }

            editText.setOnFocusChangeListener { _, hasFocus ->

                if (!hasFocus) {
                    setAllValues(getBaseValue(editText, i))
                }
                else {
                    editText.text.clear()
                }
            }
        }
    }

    private fun getBaseValue(editText: EditText, actListener: Int): Double {
        val actFactorArray: Array<String>
        val actUnit: Int

        if (actListener > 0) {
            actUnit = actListener - 1
        }
        else {
            return editText.text.toString().toDouble()
        }

        if (toBaseFactorArray.isNotEmpty()) {
            actFactorArray = toBaseFactorArray.copyOf()
        }
        else {
            actFactorArray = factorArray.copyOf()

            actFactorArray[actUnit] = when (actFactorArray[actUnit][0]) {
                '+' -> actFactorArray[actUnit].replaceFirst('+', '-')
                '-' -> actFactorArray[actUnit].replaceFirst('-', '+')

                '*' -> actFactorArray[actUnit].replaceFirst('*', '/')
                '/' -> actFactorArray[actUnit].replaceFirst('/', '*')

                else -> throw IllegalArgumentException("The factor (${actFactorArray[actUnit]}) doesn't contain a legal operator at the first position")
            }
        }

        return calculate(actFactorArray[actUnit], editText.text.toString())
    }

    // i used a external library in this function, see its license here: https://github.com/mariuszgromada/MathParser.org-mXparser/blob/master/LICENSE.txt
    private fun calculate(expressionString: String, enteredNumber: String): Double {
        val expression: Expression = Expression(enteredNumber + expressionString)
        return expression.calculate()
    }

    private fun setAllValues(baseValue: Double) {

        for ((i, editText) in editTexts.withIndex()) {

            val result = if (i > 0) {
                calculate(factorArray[i - 1], baseValue.toString()).toString()
            } else {
                baseValue.toString()
            }

            if (outputString == "") {
                editText.setText(result)
            }
            else {
                editText.setText(outputString.replace("$", result))
            }
        }
    }
}