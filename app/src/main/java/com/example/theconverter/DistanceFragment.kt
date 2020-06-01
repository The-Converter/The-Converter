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
import kotlinx.android.synthetic.main.fragment_distance.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [DistanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DistanceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_distance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentUtility = FragmentUtility(view.distanceFragment)
        fragmentUtility.setReturnButton(view, findNavController(), DistanceFragmentDirections.actionDistanceFragmentToFirstFragment())

        val convertingUtility = ConvertingUtility(fragmentUtility.getEditTexts(), resources.getStringArray(R.array.distance_factors))
        convertingUtility.setListeners()
    }
}
