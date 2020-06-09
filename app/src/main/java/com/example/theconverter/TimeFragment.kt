package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_time.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [TimeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentUtility = FragmentUtility(view.timeFragment, context)
        fragmentUtility.setReturnButton(findNavController(), TimeFragmentDirections.actionTimeFragmentToFirstFragment())

        val convertingUtility = ConvertingUtility(fragmentUtility, resources.getStringArray(R.array.time_factors))
        convertingUtility.setEditTextListener()
        convertingUtility.setHistoryListener()
    }
}