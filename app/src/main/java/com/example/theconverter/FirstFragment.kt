package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.view.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentUtility = FragmentUtility(view.firstFragment, context)

        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToSpeedFragment(), R.id.button_speed)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToTemperatureFragment(), R.id.button_temperature)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToAreaFragment(), R.id.button_area)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToDistanceFragment(), R.id.button_distance)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToTimeFragment(), R.id.button_time)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToEnergyFragment(), R.id.button_energy)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToTimeZoneFragment(), R.id.button_time_zones)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToVolumeFragment(), R.id.button_volume)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToPressureFragment(), R.id.button_pressure)
        fragmentUtility.setReturnButton(FirstFragmentDirections.actionFirstFragmentToCreditFragment(), R.id.button_credits)
    }
}