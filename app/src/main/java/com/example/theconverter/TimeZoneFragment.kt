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

    private fun setAllTime(timeUTC: IntArray, view: View) {
        val scrollView: ScrollView = view.timeZoneFragment.getChildAt(1) as ScrollView
        val linearLayout: LinearLayout = scrollView.getChildAt(0) as LinearLayout

        val childCount = linearLayout.childCount

        val factorArray = resources.getStringArray(R.array.time_zones_factors)

        var count = 0

        for (i in 0 until childCount) {
            if (linearLayout.getChildAt(i).tag == "timeField") {
                setOneTime(linearLayout.getChildAt(i) as EditText, fromUTC(timeUTC, factorArray[count]))
                count++
            }
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
        view.findViewById<EditText>(R.id.editText_utc_min12_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 0
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min11_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 1
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min10_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 2
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min9_30_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 3
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min9_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 4
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min8_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 5
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min7_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 6
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min6_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 7
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min5_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 8
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min4_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 9
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min3_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 10
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min2_30_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 11
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min2_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 12
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_min1_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 13
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl0_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 14
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl1_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 15
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl2_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 16
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl3_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 17
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl4_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 18
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl4_30_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 19
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl5_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 20
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl5_30_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 21
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl5_45_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 22
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl6_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 23
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl6_30_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 24
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl7_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 25
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl8_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 26
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl8_45_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 27
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl9_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 28
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl9_30_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 29
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl10_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 30
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl10_30_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 31
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl11_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 32
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl12_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 33
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl12_45_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 34
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl13_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 35
            }
        }

        view.findViewById<EditText>(R.id.editText_utc_pl14_time_zones).setOnFocusChangeListener{ _, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 36
            }
        }

        view.findViewById<Button>(R.id.button_done).setOnClickListener {
            val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
            timePicker.setIs24HourView(true)

            when (selectedFun) {
                0 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min12)), view)

                1 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min11)), view)

                2 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min10)), view)

                3 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min9_30)), view)

                4 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min9)), view)

                5 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min8)), view)

                6 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min7)), view)

                7 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min6)), view)

                8 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min5)), view)

                9 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min4)), view)

                10 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min3)), view)

                11 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min2_30)), view)

                12 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min2)), view)

                13 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_min1)), view)

                14 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl0)), view)

                15 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl1)), view)

                16 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl2)), view)

                17 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl3)), view)

                18 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl4)), view)

                19 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl4_30)), view)

                20 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl5)), view)

                21 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl5_30)), view)

                22 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl5_45)), view)

                23 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl6)), view)

                24 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl6_30)), view)

                25 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl7)), view)

                26 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl8)), view)

                27 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl8_45)), view)

                28 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl9)), view)

                29 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl9_30)), view)

                30 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl10)), view)

                31 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl10_30)), view)

                32 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl11)), view)

                33 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl12)), view)

                34 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl12_45)), view)

                35 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl13)), view)

                36 -> setAllTime(toUTC(timePicker.hour, timePicker.minute, getString(R.string.time_zones_utc_pl14)), view)

                else -> throw NotImplementedError()
            }

            toggleTimeSelVisibility(view)
        }
    }
}
