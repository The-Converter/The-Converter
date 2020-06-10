package com.example.theconverter

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_time_zone.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [TimeZoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimeZoneFragment : Fragment() {

    private var _selectedFun: Int = -1
    private var _factorArray: Array<String> = emptyArray()
    private lateinit var _fragmentUtility: FragmentUtility

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_zone, container, false)
    }

    // https://time.is/time_zones

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton_choose).setOnClickListener {
            val action = TimeZoneFragmentDirections.actionTimeZoneFragmentToFirstFragment()
            findNavController().navigate(action)
        }

        _fragmentUtility = FragmentUtility(view.timeZoneFragment, context)

        // cache list with tags
        _fragmentUtility.getEditTexts(true, "timeField")
        _factorArray = resources.getStringArray(R.array.time_zones_factors)

        setOnClickListener(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun toggleTimeSelVisibility(view: View) {
        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        val button = view.findViewById<Button>(R.id.button_done)

        if (!timePicker.isVisible) {
            timePicker.visibility = TimePicker.VISIBLE
            timePicker.setIs24HourView(true)
            timePicker.bringToFront()

            button.visibility = Button.VISIBLE
            button.bringToFront()
        }
        else {
            timePicker.visibility = TimePicker.GONE
            button.visibility = TimePicker.GONE
            _selectedFun = -1
        }
    }

    private fun toUTC(hourTime: Int, minTime: Int, timeZoneVal: String): IntArray {
        val hourToUTC: Int
        var minToUTC: Int

        if (timeZoneVal.contains(':')) {
            val timeStrings = timeZoneVal.split(':')

            hourToUTC = timeStrings[0].toInt() * -1
            minToUTC = timeStrings[1].toInt()

            if (!timeZoneVal.contains('-'))
                minToUTC *= -1
        }
        else {
            hourToUTC = timeZoneVal.toInt() * -1
            minToUTC = 0
        }

        return addTime(intArrayOf(0, hourTime, minTime), intArrayOf(0, hourToUTC, minToUTC))
    }

    private fun addTime(baseTime: IntArray, timeToAdd: IntArray): IntArray {
        var dayTimeUTC = baseTime[0] + timeToAdd[0]

        var hourTimeUTC = baseTime[1] + timeToAdd[1]
        var minTimeUTC = baseTime[2] + timeToAdd[2]

        var pair = unitCheck(minTimeUTC, hourTimeUTC, 60)
        minTimeUTC = pair.first
        hourTimeUTC = pair.second

        pair = unitCheck(hourTimeUTC, dayTimeUTC, 24)
        hourTimeUTC = pair.first
        dayTimeUTC = pair.second

        return intArrayOf(dayTimeUTC, hourTimeUTC, minTimeUTC)
    }

    private fun unitCheck(smallerUnit: Int, biggerUnit: Int, difference: Int): Pair<Int, Int> {

        return if (smallerUnit >= difference) {
            Pair(smallerUnit - difference, biggerUnit + 1)
        } else if (smallerUnit < 0) {
            Pair(smallerUnit + difference, biggerUnit - 1)
        } else {
            Pair(smallerUnit, biggerUnit)
        }
    }

    private fun setAllTime(timeUTC: IntArray) {

        for ((i, editText) in _fragmentUtility.getEditTexts().withIndex()) {
            setOneTime(editText, fromUTC(timeUTC, _factorArray[i]))
        }
    }

    private fun setOneTime(editText: EditText, time: IntArray) {
        if (time[0] == 0) {
            editText.setText(
                getString(R.string.clock_24_hours_without_days_format, time[1].toString().padStart(2, '0'), time[2].toString().padStart(2, '0'))
            )
        }
        else {
            editText.setText(
                getString(
                    R.string.clock_24_hours_with_days_format,

                    if (time[0] == 1) {
                        "next day"
                    }
                    else {
                        "day before"
                    },
                    time[1].toString().padStart(2, '0'),
                    time[2].toString().padStart(2, '0')
                )
            )
        }
    }

    private fun fromUTC(timeUTC: IntArray, timeZoneVal: String): IntArray {
        val hourToAdd: Int
        var minToAdd: Int

        if (timeZoneVal.contains(':')) {
            val timeStrings = timeZoneVal.split(':')

            hourToAdd = timeStrings[0].toInt()
            minToAdd = timeStrings[1].toInt()

            if (timeZoneVal.contains("-"))
                minToAdd *= -1
        }
        else {
            hourToAdd = timeZoneVal.toInt()
            minToAdd = 0
        }

        return addTime(timeUTC, intArrayOf(0, hourToAdd, minToAdd))
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun setOnClickListener(view: View) {
        for ((i, editText) in _fragmentUtility.getEditTexts().withIndex()) {
            editText.setOnFocusChangeListener{ _, hasFocus ->
                if (hasFocus) {
                    toggleTimeSelVisibility(view)
                    _selectedFun = i
                }
            }
        }

        view.findViewById<Button>(R.id.button_done).setOnClickListener {
            val timePicker = view.findViewById<TimePicker>(R.id.timePicker)

            setAllTime(toUTC(timePicker.hour, timePicker.minute, _factorArray[_selectedFun]))
            toggleTimeSelVisibility(view)
        }
    }
}
