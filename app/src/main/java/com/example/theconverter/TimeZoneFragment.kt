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
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_time_zone.*
import kotlinx.android.synthetic.main.fragment_time_zone.view.*
import org.w3c.dom.Text

/**
 * A simple [Fragment] subclass.
 * Use the [TimeZoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimeZoneFragment : Fragment() {

    private var selectedFun: Int = -1
    private var timeFieldList :MutableList<EditText> = mutableListOf()

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

        val scrollView: ScrollView = view.timeZoneFragment.getChildAt(3) as ScrollView
        val linearLayout: LinearLayout = scrollView.getChildAt(0) as LinearLayout

        val childCount = linearLayout.childCount

        for (i in 0 until childCount) {
            if (linearLayout.getChildAt(i).tag == "timeField") {
                timeFieldList.add(linearLayout.getChildAt(i) as EditText)
            }
        }

        setOnClickListener(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun toggleTimeSelVisibility(view: View) {
        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        val button = view.findViewById<Button>(R.id.button_done)

        if (!timePicker.isVisible) {
            timePicker.visibility = TimePicker.VISIBLE
            timePicker.bringToFront()

            button.visibility = Button.VISIBLE
            button.bringToFront()
        }
        else {
            timePicker.visibility = TimePicker.GONE
            button.visibility = TimePicker.GONE
            selectedFun = -1
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

        if (minTimeUTC >= 60) {
            hourTimeUTC += 1
            minTimeUTC -= 60
        } else if (minTimeUTC < 0) {
            hourTimeUTC -= 1
            minTimeUTC += 60
        }

        if (hourTimeUTC >= 24) {
            dayTimeUTC += 1
            hourTimeUTC -= 24
        } else if (hourTimeUTC < 0) {
            dayTimeUTC -= 1
            hourTimeUTC += 24
        }

        return intArrayOf(dayTimeUTC, hourTimeUTC, minTimeUTC)
    }

    private fun setAllTime(timeUTC: IntArray) {
        val factorArray = resources.getStringArray(R.array.time_zones_factors)

        for ((i, editText) in timeFieldList.withIndex()) {
            setOneTime(editText, fromUTC(timeUTC, factorArray[i]))
        }
    }

    private fun setOneTime(editText: EditText, time: IntArray) {
        if (time[0] == 0)
            editText.setText(getString(R.string.clock_24_hours_without_days_format, time[1].toString().padStart(2, '0'), time[2].toString().padStart(2, '0')))
        else
            editText.setText(getString(R.string.clock_24_hours_with_days_format, time[0].toString(), time[1].toString().padStart(2, '0'), time[2].toString().padStart(2, '0')))
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
        for ((i, editText) in timeFieldList.withIndex()) {
            editText.setOnFocusChangeListener{ _, hasfocus ->
                if (hasfocus) {
                    toggleTimeSelVisibility(view)
                    selectedFun = i
                }
            }
        }

        view.findViewById<Button>(R.id.button_done).setOnClickListener {
            val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
            timePicker.setIs24HourView(true)

            when (selectedFun) {
                0 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min12)))

                1 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min11)))

                2 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min10)))

                3 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min9_30)))

                4 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min9)))

                5 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min8)))

                6 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min7)))

                7 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min6)))

                8 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min5)))

                9 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min4)))

                10 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min3)))

                11 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min2_30)))

                12 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min2)))

                13 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min1)))

                14 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl0)))

                15 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl1)))

                16 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl2)))

                17 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl3)))

                18 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl4)))

                19 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl4_30)))

                20 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl5)))

                21 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl5_30)))

                22 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl5_45)))

                23 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl6)))

                24 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl6_30)))

                25 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl7)))

                26 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl8)))

                27 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl8_45)))

                28 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl9)))

                29 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl9_30)))

                30 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl10)))

                31 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl10_30)))

                32 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl11)))

                33 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl12)))

                34 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl12_45)))

                35 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl13)))

                36 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl14)))

                else -> throw NotImplementedError()
            }

            toggleTimeSelVisibility(view)
        }
    }
}
