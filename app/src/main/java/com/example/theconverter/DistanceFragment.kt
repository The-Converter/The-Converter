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


/**
 * A simple [Fragment] subclass.
 * Use the [DistanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DistanceFragment : Fragment() {

    private var changedByApp = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_distance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton_choose).setOnClickListener {
            val action = DistanceFragmentDirections.actionDistanceFragmentToFirstFragment()
            findNavController().navigate(action)
        }

        view.findViewById<EditText>(R.id.editText_km_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputKm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_m_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputM(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_dm_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputDm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_cm_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputCm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_mm_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputMm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_miles_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputMiles(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_yard_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputYard(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_foot_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputFoot(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_seaMile_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputSeaMile(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_inch_distance).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputInch(view, content.toString().toDouble())
            }
        }
    }

    private fun clearEditText(view: View) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_m_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_dm_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_cm_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_mm_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_miles_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_yard_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_foot_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_seaMile_distance).getText().clear()

        view.findViewById<EditText>(R.id.editText_inch_distance).getText().clear()

        changedByApp = false
    }

    private fun inputKm(view: View, km: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_m_distance).setText((km * getString(R.string.dis_km_m).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((km * getString(R.string.dis_km_dm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((km * getString(R.string.dis_km_cm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((km * getString(R.string.dis_km_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((km * getString(R.string.dis_km_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((km * getString(R.string.dis_km_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((km * getString(R.string.dis_km_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((km * getString(R.string.dis_km_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((km * getString(R.string.dis_km_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputM(view: View, m: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((m / getString(R.string.dis_km_m).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((m * getString(R.string.dis_m_dm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((m * getString(R.string.dis_m_cm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((m * getString(R.string.dis_m_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((m * getString(R.string.dis_m_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((m * getString(R.string.dis_m_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((m * getString(R.string.dis_m_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((m * getString(R.string.dis_m_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((m * getString(R.string.dis_m_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputDm(view: View, dm: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((dm / getString(R.string.dis_km_dm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((dm / getString(R.string.dis_m_dm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((dm * getString(R.string.dis_dm_cm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((dm * getString(R.string.dis_dm_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((dm * getString(R.string.dis_dm_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((dm * getString(R.string.dis_dm_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((dm * getString(R.string.dis_dm_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((dm * getString(R.string.dis_dm_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((dm * getString(R.string.dis_dm_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputCm(view: View, cm: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((cm / getString(R.string.dis_km_cm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((cm / getString(R.string.dis_m_cm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((cm / getString(R.string.dis_dm_cm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((cm * getString(R.string.dis_cm_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((cm * getString(R.string.dis_cm_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((cm * getString(R.string.dis_cm_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((cm * getString(R.string.dis_cm_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((cm * getString(R.string.dis_cm_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((cm * getString(R.string.dis_cm_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputMm(view: View, mm: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((mm / getString(R.string.dis_km_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((mm / getString(R.string.dis_m_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((mm / getString(R.string.dis_dm_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((mm / getString(R.string.dis_cm_mm).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((mm * getString(R.string.dis_mm_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((mm * getString(R.string.dis_mm_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((mm * getString(R.string.dis_mm_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((mm * getString(R.string.dis_mm_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((mm * getString(R.string.dis_mm_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputMiles(view: View, miles: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((miles / getString(R.string.dis_km_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((miles / getString(R.string.dis_m_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((miles / getString(R.string.dis_dm_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((miles / getString(R.string.dis_cm_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((miles / getString(R.string.dis_mm_miles).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((miles * getString(R.string.dis_miles_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((miles * getString(R.string.dis_miles_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((miles * getString(R.string.dis_miles_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((miles * getString(R.string.dis_miles_inch).toDouble()).toString())

        changedByApp = false

    }

    private fun inputYard(view: View, yard: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((yard / getString(R.string.dis_km_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((yard / getString(R.string.dis_m_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((yard / getString(R.string.dis_dm_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((yard / getString(R.string.dis_cm_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((yard / getString(R.string.dis_mm_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((yard / getString(R.string.dis_miles_yard).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((yard * getString(R.string.dis_yard_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((yard * getString(R.string.dis_yard_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((yard * getString(R.string.dis_yard_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputFoot(view: View, foot: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((foot / getString(R.string.dis_km_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((foot / getString(R.string.dis_m_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((foot / getString(R.string.dis_dm_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((foot / getString(R.string.dis_cm_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((foot / getString(R.string.dis_mm_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((foot / getString(R.string.dis_miles_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((foot / getString(R.string.dis_yard_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((foot * getString(R.string.dis_foot_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((foot / getString(R.string.dis_foot_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputSeaMile(view: View, seaMile: Double)
    {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_distance).setText((seaMile / getString(R.string.dis_km_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((seaMile / getString(R.string.dis_m_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((seaMile / getString(R.string.dis_dm_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((seaMile / getString(R.string.dis_cm_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((seaMile / getString(R.string.dis_mm_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((seaMile / getString(R.string.dis_miles_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((seaMile / getString(R.string.dis_yard_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((seaMile / getString(R.string.dis_foot_seaMile).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_inch_distance).setText((seaMile * getString(R.string.dis_seaMile_inch).toDouble()).toString())

        changedByApp = false
    }

    private fun inputInch(view: View, inch: Double)
    {
        changedByApp = true


        view.findViewById<EditText>(R.id.editText_km_distance).setText((inch / getString(R.string.dis_km_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_distance).setText((inch / getString(R.string.dis_m_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_distance).setText((inch / getString(R.string.dis_dm_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_distance).setText((inch / getString(R.string.dis_cm_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_distance).setText((inch / getString(R.string.dis_mm_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_miles_distance).setText((inch / getString(R.string.dis_miles_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_yard_distance).setText((inch / getString(R.string.dis_yard_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_distance).setText((inch / getString(R.string.dis_foot_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_seaMile_distance).setText((inch / getString(R.string.dis_seaMile_inch).toDouble()).toString())

        changedByApp = false
    }
}
