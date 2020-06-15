package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_pressure.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [PressureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PressureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pressure, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentUtility = FragmentUtility(view.pressureFragment, context)
        fragmentUtility.setReturnButton(PressureFragmentDirections.actionPressureFragmentToFirstFragment())

        val convertingUtility = ConvertingUtility(fragmentUtility, resources.getStringArray(R.array.pressure_factors))
        convertingUtility.setEditTextListener()
        convertingUtility.setHistoryListener()


    }
}
