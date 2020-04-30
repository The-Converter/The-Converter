package com.example.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged


/**
 * A simple [Fragment] subclass.
 * Use the [Temperature.newInstance] factory method to
 * create an instance of this fragment.
 */
class Temperature : Fragment() {

    private var changedByUser : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temperature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<EditText>(R.id.editText_Celcius).doAfterTextChanged { content ->
            if(changedByUser) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputCelcius(view, content.toString().toDouble())
                }
            }
        }

        view.findViewById<EditText>(R.id.editText_Fahrenheit).doAfterTextChanged { content ->
            if(changedByUser) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputFahrenheit(view, content.toString().toDouble())
                }
            }
        }

        view.findViewById<EditText>(R.id.editText_Kelvin).doAfterTextChanged { content ->
            if(changedByUser) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputKelvin(view, content.toString().toDouble())
                }
            }
        }
    }

    private fun clearEditText(view: View){

        changedByUser = false

        view.findViewById<EditText>(R.id.editText_Celcius).getText().clear()

        view.findViewById<EditText>(R.id.editText_Fahrenheit).getText().clear()

        view.findViewById<EditText>(R.id.editText_Kelvin).getText().clear()

        changedByUser = true
    }

    private fun inputCelcius(view: View, Celcius: Double){

        changedByUser = false

        view.findViewById<EditText>(R.id.editText_Fahrenheit).setText((Celcius * 9 / 5 +32).toDouble().toString()+"°F")

        view.findViewById<EditText>(R.id.editText_Kelvin).setText((Celcius +273.15).toDouble().toString()+"K")

        changedByUser = true
    }

    private fun inputFahrenheit(view: View, Fahrenheit: Double){

        changedByUser = false

        view.findViewById<EditText>(R.id.editText_Celcius).setText(((Fahrenheit-32)*5/9).toDouble().toString()+"°C")

        view.findViewById<EditText>(R.id.editText_Kelvin).setText(((Fahrenheit -32)*5 /9 +273.15).toDouble().toString()+"K")

        changedByUser = true
    }

    private fun inputKelvin(view: View, Kelvin: Double){

        changedByUser = false

        view.findViewById<EditText>(R.id.editText_Celcius).setText((Kelvin-273.15).toDouble().toString()+"°C")

        view.findViewById<EditText>(R.id.editText_Fahrenheit).setText(((Kelvin -273.15)*9 /5 +32).toDouble().toString()+"F")

        changedByUser = true
    }


}
