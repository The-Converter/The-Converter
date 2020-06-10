package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_volume.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [VolumeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VolumeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentUtility = FragmentUtility(view.volumeFragment, context)
        fragmentUtility.setReturnButton(VolumeFragmentDirections.actionVolumeFragmentToFirstFragment())

        val convertingUtility = ConvertingUtility(fragmentUtility, resources.getStringArray(R.array.volume_factors))
        convertingUtility.setEditTextListener()
        convertingUtility.setHistoryListener()
    }
}
