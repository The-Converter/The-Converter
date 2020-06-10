package com.example.theconverter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_credits.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CreditsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreditsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FragmentUtility(view.creditFragment, context).setReturnButton(CreditsFragmentDirections.actionCreditsToFirstFragment())
    }
}
