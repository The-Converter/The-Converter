package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_speed.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [SpeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpeedFragment : Fragment() {

    private var changedByApp: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentUtility = FragmentUtility(view.speedFragment, context)
        fragmentUtility.setReturnButton(findNavController(), SpeedFragmentDirections.actionSpeedFragmentToFirstFragment())

        val convertingUtility = ConvertingUtility(fragmentUtility, resources.getStringArray(R.array.speed_factors))
        convertingUtility.setEditTextListener()
        convertingUtility.setHistoryListener()
    }
}
