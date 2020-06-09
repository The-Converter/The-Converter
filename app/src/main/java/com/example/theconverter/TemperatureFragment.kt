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
import kotlinx.android.synthetic.main.fragment_temperature.view.*


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

        val fragmentUtility = FragmentUtility(view.temperatureFragment, context)
        fragmentUtility.setReturnButton(findNavController(), TemperatureFragmentDirections.actionTemperatureFragmentToFirstFragment())

        val convertingUtility = ConvertingUtility(fragmentUtility, resources.getStringArray(R.array.temperature_factors), resources.getStringArray(R.array.temperature_factors_to_base))
        convertingUtility.setEditTextListener()
        convertingUtility.setHistoryListener()
    }
}
