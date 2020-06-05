package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_area.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [AreaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AreaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentUtility: FragmentUtility = FragmentUtility(view.areaFragment)
        fragmentUtility.setReturnButton(view, findNavController(), AreaFragmentDirections.actionAreaFragmentToFirstFragment())

        val convertingUtility: ConvertingUtility = ConvertingUtility(fragmentUtility, context, resources.getStringArray(R.array.area_factors))
        convertingUtility.setListeners()
    }
}