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

/**
 * A simple [Fragment] subclass.
 * Use the [TimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TimeFragment : Fragment() {
    private var changedByApp: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton_choose).setOnClickListener {
            val action = TimeFragmentDirections.actionTimeFragmentToFirstFragment()
            findNavController().navigate(action)
        }

        view.findViewById<EditText>(R.id.editText_hours).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputHour(view, content.toString().toDouble())
                }
            }
        }

        view.findViewById<EditText>(R.id.editText_minutes).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputMinutes(view, content.toString().toDouble())
                }
            }
        }

    }

    private fun inputHour(view: View, hours: Double){
        changedByApp = true

        val hoursToMinutesFactor = getString(R.string.time_hours_minutes).toDouble()

        view.findViewById<EditText>(R.id.editText_minutes).setText((hours * hoursToMinutesFactor).toString())

        changedByApp = false
    }

    private fun inputMinutes(view: View, minutes: Double){
        changedByApp = true

        val minutesToHoursFactor = getString(R.string.time_minutes_hours).toDouble()

        view.findViewById<EditText>(R.id.editText_hours).setText((minutes * minutesToHoursFactor).toString())

        changedByApp = false
    }


    private fun clearEditText(view: View) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_hours).getText().clear()

        view.findViewById<EditText>(R.id.editText_minutes).getText().clear()

        changedByApp = false
    }
}