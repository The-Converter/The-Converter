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

        view.findViewById<EditText>(R.id.editText_days).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputDays(view, content.toString().toDouble())
                }
            }
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

        view.findViewById<EditText>(R.id.editText_seconds).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputSeconds(view, content.toString().toDouble())
                }
            }
        }

        view.findViewById<EditText>(R.id.editText_milliseconds).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty()) {
                    clearEditText(view)
                }
                else{
                    inputMilliseconds(view, content.toString().toDouble())
                }
            }
        }

    }

    private fun inputDays(view: View, days: Double){
        changedByApp = true

        val daysToHoursFactor = getString(R.string.time_days_hours).toDouble()
        val daysToMinutesFactor = getString(R.string.time_days_minutes).toDouble()
        val daysToSecondsFactor = getString(R.string.time_days_seconds).toDouble()
        val daysToMillisecondsFactor = getString(R.string.time_days_milliseconds).toDouble()

        view.findViewById<EditText>(R.id.editText_hours).setText((days * daysToHoursFactor).toString())
        view.findViewById<EditText>(R.id.editText_minutes).setText((days * daysToMinutesFactor).toString())
        view.findViewById<EditText>(R.id.editText_seconds).setText((days * daysToSecondsFactor).toString())
        view.findViewById<EditText>(R.id.editText_milliseconds).setText((days * daysToMillisecondsFactor).toString())

        changedByApp = false
    }

    private fun inputHour(view: View, hours: Double){
        changedByApp = true

        val hoursToDaysFactor = getString(R.string.time_hours_days).toDouble()
        val hoursToMinutesFactor = getString(R.string.time_hours_minutes).toDouble()
        val hoursToSecondsFactor = getString(R.string.time_hours_seconds).toDouble()
        val hoursToMillisecondsFactor = getString(R.string.time_hours_milliseconds).toDouble()

        view.findViewById<EditText>(R.id.editText_days).setText((hours * hoursToDaysFactor).toString())
        view.findViewById<EditText>(R.id.editText_minutes).setText((hours * hoursToMinutesFactor).toString())
        view.findViewById<EditText>(R.id.editText_seconds).setText((hours * hoursToSecondsFactor).toString())
        view.findViewById<EditText>(R.id.editText_milliseconds).setText((hours * hoursToMillisecondsFactor).toString())

        changedByApp = false
    }

    private fun inputMinutes(view: View, minutes: Double){
        changedByApp = true

        val minutesToDaysFactor = getString(R.string.time_minutes_days).toDouble()
        val minutesToHoursFactor = getString(R.string.time_minutes_hours).toDouble()
        val minutesToSecondsFactor = getString(R.string.time_minutes_seconds).toDouble()
        val minutesToMillisecondsFactor = getString(R.string.time_minutes_milliseconds).toDouble()

        view.findViewById<EditText>(R.id.editText_days).setText((minutes * minutesToDaysFactor).toString())
        view.findViewById<EditText>(R.id.editText_hours).setText((minutes * minutesToHoursFactor).toString())
        view.findViewById<EditText>(R.id.editText_seconds).setText((minutes * minutesToSecondsFactor).toString())
        view.findViewById<EditText>(R.id.editText_milliseconds).setText((minutes * minutesToMillisecondsFactor).toString())

        changedByApp = false
    }

    private fun inputSeconds(view: View, seconds: Double){
        changedByApp = true

        val secondsToDaysFactor = getString(R.string.time_seconds_days).toDouble()
        val secondsToHoursFactor = getString(R.string.time_seconds_hours).toDouble()
        val secondsToMinutesFactor = getString(R.string.time_seconds_minutes).toDouble()
        val secondsToMillisecondsFactor = getString(R.string.time_seconds_milliseconds).toDouble()

        view.findViewById<EditText>(R.id.editText_days).setText((seconds * secondsToDaysFactor).toString())
        view.findViewById<EditText>(R.id.editText_hours).setText((seconds * secondsToHoursFactor).toString())
        view.findViewById<EditText>(R.id.editText_minutes).setText((seconds * secondsToMinutesFactor).toString())
        view.findViewById<EditText>(R.id.editText_milliseconds).setText((seconds * secondsToMillisecondsFactor).toString())

        changedByApp = false
    }

    private fun inputMilliseconds(view: View, milliseconds: Double){
        changedByApp = true

        val millisecondsToSecondsFactor = getString(R.string.time_milliseconds_second).toDouble()
        val millisecondsToMinutesFactor = getString(R.string.time_milliseconds_minute).toDouble()
        val millisecondsToHoursFactor = getString(R.string.time_milliseconds_hours).toDouble()
        val millisecondsToDaysFactor = getString(R.string.time_milliseconds_days).toDouble()

        view.findViewById<EditText>(R.id.editText_days).setText((milliseconds * millisecondsToDaysFactor).toString())
        view.findViewById<EditText>(R.id.editText_hours).setText((milliseconds * millisecondsToHoursFactor).toString())
        view.findViewById<EditText>(R.id.editText_minutes).setText((milliseconds * millisecondsToMinutesFactor).toString())
        view.findViewById<EditText>(R.id.editText_seconds).setText((milliseconds * millisecondsToSecondsFactor).toString())

        changedByApp = false
    }

    private fun clearEditText(view: View) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_days).getText().clear()
        view.findViewById<EditText>(R.id.editText_hours).getText().clear()
        view.findViewById<EditText>(R.id.editText_minutes).getText().clear()
        view.findViewById<EditText>(R.id.editText_seconds).getText().clear()
        view.findViewById<EditText>(R.id.editText_milliseconds).getText().clear()

        changedByApp = false
    }
}