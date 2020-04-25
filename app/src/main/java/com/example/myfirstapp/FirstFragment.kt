package com.example.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import org.w3c.dom.Text
import java.io.File

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

        view.findViewById<Button>(R.id.random_button).setOnClickListener {
            val countTextView = view.findViewById<TextView>(R.id.textview_first)
            val currentCount = countTextView.text.toString().toInt()

            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount)
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
            val myToast = Toast.makeText(context, getString(R.string.toast_text), Toast.LENGTH_SHORT)
            myToast.show()
        }

        view.findViewById<Button>(R.id.count_button).setOnClickListener {
            countMe(view)
        }

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val textview = view.findViewById<TextView>(R.id.textview_first)

            if (!textview.text.equals(getString(R.string.pressed_text))) {
                textview.text = getString(R.string.pressed_text)
            }
            else {
                textview.text = getString(R.string.hello_first_fragment)
            }
        }
    }

    fun countMe(view: View) {
        val countTextView = view.findViewById<TextView>(R.id.textview_first) // find text view
        val count: Int = countTextView.text.toString().toInt() + 1 // increase count

        countTextView.text = count.toString() // set new text
    }
}
