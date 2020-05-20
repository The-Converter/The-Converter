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
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text
import java.sql.Time

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

        view.findViewById<Button>(R.id.button_done).setOnClickListener {
            val timePicker = view.findViewById<TimePicker>(R.id.timePicker)

            when (selectedFun) {
                1 -> inputUtcMin12(view, timePicker.hour, timePicker.minute)
                else -> throw NotImplementedError()
            }

            toggleTimeSelVisibility(view)
        }

        view.findViewById<EditText>(R.id.editText_utc_min12_time_zones).setOnFocusChangeListener{ asdf, hasfocus ->
            if (hasfocus) {
                toggleTimeSelVisibility(view)
                selectedFun = 1
        }
        }
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

    private fun inputUtcMin12(view: View, hour: Int, min: Int) {
        view.findViewById<TextView>(R.id.editText_utc_min12_time_zones).text = getString(R.string.clock_24_hours_format, hour.toString().padStart(2, '0'), min.toString().padStart(2, '0'))
    }
}
