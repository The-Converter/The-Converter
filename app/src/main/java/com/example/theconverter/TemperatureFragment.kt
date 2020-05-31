package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [TemperatureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TemperatureFragment : Fragment() {

    private var changedByApp: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temperature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton_choose).setOnClickListener {
            val action = TemperatureFragmentDirections.actionTemperatureFragmentToFirstFragment()
            findNavController().navigate(action)
        }

        view.findViewById<EditText>(R.id.editText_celsius).setOnEditorActionListener { _, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.findViewById<EditText>(R.id.editText_celsius).clearFocus()
                true
            }
            else {
                false
            }
        }

        view.findViewById<EditText>(R.id.editText_celsius).setOnFocusChangeListener { _, hasfocus ->
            if(!hasfocus) {
                inputCelsius(view, view.findViewById<EditText>(R.id.editText_celsius).text.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_fahrenheit).setOnEditorActionListener { _, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.findViewById<EditText>(R.id.editText_fahrenheit).clearFocus()
                true
            }
            else {
                false
            }
        }

        view.findViewById<EditText>(R.id.editText_fahrenheit).setOnFocusChangeListener { _, hasfocus ->
            if(!hasfocus) {
                inputFahrenheit(view, view.findViewById<EditText>(R.id.editText_fahrenheit).text.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_kelvin).setOnEditorActionListener { _, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.findViewById<EditText>(R.id.editText_kelvin).clearFocus()
                true
            }
            else {
                false
            }
        }

        view.findViewById<EditText>(R.id.editText_kelvin).setOnFocusChangeListener { _, hasfocus ->
            if(!hasfocus) {
                inputCelsius(view, view.findViewById<EditText>(R.id.editText_kelvin).text.toString().toDouble())
            }
        }
    }


    private fun inputCelsius(view: View, celsius: Double) {

        changedByApp = true

        val celsiusFahrenheitFactor = getString(R.string.celsius_fahrenheit_factor).toDouble()
        val celsiusFahrenheitAdd = getString(R.string.celsius_fahrenheit_add).toDouble()

        view.findViewById<EditText>(R.id.editText_fahrenheit).setText((celsius * celsiusFahrenheitFactor + celsiusFahrenheitAdd).toString())

        view.findViewById<EditText>(R.id.editText_kelvin).setText((celsius + getString(R.string.celsius_kelvin_add).toDouble()).toString())

        changedByApp = false
    }

    private fun inputFahrenheit(view: View, fahrenheit: Double) {

        changedByApp = true

        val celsiusFahrenheitFactor = getString(R.string.celsius_fahrenheit_factor).toDouble()
        val celsiusFahrenheitAdd = getString(R.string.celsius_fahrenheit_add).toDouble()

        val celsius = (fahrenheit - celsiusFahrenheitAdd) / celsiusFahrenheitFactor

        view.findViewById<EditText>(R.id.editText_celsius).setText(celsius.toString())

        view.findViewById<EditText>(R.id.editText_kelvin).setText((celsius + 273.15).toString())

        changedByApp = false
    }

    private fun inputKelvin(view: View, kelvin: Double) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_celsius).setText((kelvin - getString(R.string.celsius_kelvin_add).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_fahrenheit).setText((kelvin * getString(R.string.celsius_fahrenheit_factor).toDouble() - getString(R.string.fahrenheit_kelvin_subtract).toDouble()).toString())

        changedByApp = false
    }

    private fun clearEditText(view: View) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_celsius).getText().clear()

        view.findViewById<EditText>(R.id.editText_fahrenheit).getText().clear()

        view.findViewById<EditText>(R.id.editText_kelvin).getText().clear()

        changedByApp = false
    }
}
