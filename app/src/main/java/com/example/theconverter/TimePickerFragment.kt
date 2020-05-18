package com.example.theconverter

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [TimePickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimePickerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_picker, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        timePicker.setIs24HourView(true)

        view.findViewById<Button>(R.id.button_done).setOnClickListener {
            val action = TimePickerFragmentDirections.actionTimePickerFragmentToTimeZoneFragment(timePicker.hour, timePicker.minute)
            view.findNavController().navigate(action)
        }
    }
}
