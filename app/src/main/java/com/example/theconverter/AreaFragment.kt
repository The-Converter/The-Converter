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
import kotlin.math.pow


/**
 * A simple [Fragment] subclass.
 * Use the [AreaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AreaFragment : Fragment() {

    private var changedByApp: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton_choose).setOnClickListener {
            val action = AreaFragmentDirections.actionAreaFragmentToFirstFragment()
            findNavController().navigate(action)
        }

        view.findViewById<EditText>(R.id.editText_km_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputKm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_hectares_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputHectares(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_ar_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputAr(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_m_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputM(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_dm_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputDm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_cm_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputCm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_mm_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputMm(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_mile_area).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputMi(view, content.toString().toDouble())
            }
        }
    }

    private fun clearEditText(view: View) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_area).getText().clear()

        view.findViewById<EditText>(R.id.editText_hectares_area).getText().clear()

        view.findViewById<EditText>(R.id.editText_ar_area).getText().clear()

        view.findViewById<EditText>(R.id.editText_m_area).getText().clear()

        view.findViewById<EditText>(R.id.editText_dm_area).getText().clear()

        view.findViewById<EditText>(R.id.editText_cm_area).getText().clear()

        view.findViewById<EditText>(R.id.editText_mm_area).getText().clear()

        changedByApp = false
    }

    private fun inputKm(view: View, km: Double) {

        changedByApp = true

        val factor:Double = resources.getInteger(R.integer.factor_area).toDouble()

        view.findViewById<EditText>(R.id.editText_hectares_area).setText((km * factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_ar_area).setText((km * factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_m_area).setText((km * factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        view.findViewById<EditText>(R.id.editText_dm_area).setText((km * factor.pow(resources.getInteger(R.integer.four_steps))).toString())

        view.findViewById<EditText>(R.id.editText_cm_area).setText((km * factor.pow(resources.getInteger(R.integer.five_steps))).toString())

        view.findViewById<EditText>(R.id.editText_mm_area).setText((km * factor.pow(resources.getInteger(R.integer.six_steps))).toString())

        changedByApp = false
    }

    private fun inputHectares(view: View, hectares: Double) {

        changedByApp = true

        val factor:Double = resources.getInteger(R.integer.factor_area).toDouble()

        view.findViewById<EditText>(R.id.editText_km_area).setText((hectares / factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_ar_area).setText((hectares * factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_m_area).setText((hectares * factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_dm_area).setText((hectares * factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        view.findViewById<EditText>(R.id.editText_cm_area).setText((hectares * factor.pow(resources.getInteger(R.integer.four_steps))).toString())

        view.findViewById<EditText>(R.id.editText_mm_area).setText((hectares * factor.pow(resources.getInteger(R.integer.five_steps))).toString())

        changedByApp = false

    }

    private fun inputAr(view: View, ar: Double) {

        changedByApp = true

        val factor:Double = resources.getInteger(R.integer.factor_area).toDouble()

        view.findViewById<EditText>(R.id.editText_km_area).setText((ar / factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_hectares_area).setText((ar / factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_m_area).setText((ar * factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_dm_area).setText((ar * factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_cm_area).setText((ar * factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        view.findViewById<EditText>(R.id.editText_mm_area).setText((ar * factor.pow(resources.getInteger(R.integer.four_steps))).toString())

        changedByApp = false

    }

    private fun inputM(view: View, m: Double) {

        changedByApp = true

        val factor:Double = resources.getInteger(R.integer.factor_area).toDouble()

        view.findViewById<EditText>(R.id.editText_km_area).setText((m / factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        view.findViewById<EditText>(R.id.editText_hectares_area).setText((m / factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_ar_area).setText((m / factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_dm_area).setText((m * factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_cm_area).setText((m * factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_mm_area).setText((m * factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        changedByApp = false

    }

    private fun inputDm(view: View, dm: Double) {

        changedByApp = true

        val factor:Double = resources.getInteger(R.integer.factor_area).toDouble()

        view.findViewById<EditText>(R.id.editText_km_area).setText((dm / factor.pow(resources.getInteger(R.integer.four_steps))).toString())

        view.findViewById<EditText>(R.id.editText_hectares_area).setText((dm / factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        view.findViewById<EditText>(R.id.editText_ar_area).setText((dm / factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_m_area).setText((dm / factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_cm_area).setText((dm * factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_mm_area).setText((dm * factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        changedByApp = false

    }

    private fun inputCm(view: View, cm: Double) {

        changedByApp = true

        val factor:Double = resources.getInteger(R.integer.factor_area).toDouble()

        view.findViewById<EditText>(R.id.editText_km_area).setText((cm / factor.pow(resources.getInteger(R.integer.five_steps))).toString())

        view.findViewById<EditText>(R.id.editText_hectares_area).setText((cm / factor.pow(resources.getInteger(R.integer.four_steps))).toString())

        view.findViewById<EditText>(R.id.editText_ar_area).setText((cm / factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        view.findViewById<EditText>(R.id.editText_m_area).setText((cm / factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_dm_area).setText((cm / factor.pow(resources.getInteger(R.integer.one_step))).toString())

        view.findViewById<EditText>(R.id.editText_mm_area).setText((cm * factor.pow(resources.getInteger(R.integer.one_step))).toString())

        changedByApp = false

    }

    private fun inputMm(view: View, mm: Double) {

        changedByApp = true

        val factor:Double = resources.getInteger(R.integer.factor_area).toDouble()

        view.findViewById<EditText>(R.id.editText_km_area).setText((mm / factor.pow(resources.getInteger(R.integer.six_steps))).toString())

        view.findViewById<EditText>(R.id.editText_hectares_area).setText((mm / factor.pow(resources.getInteger(R.integer.five_steps))).toString())

        view.findViewById<EditText>(R.id.editText_ar_area).setText((mm / factor.pow(resources.getInteger(R.integer.four_steps))).toString())

        view.findViewById<EditText>(R.id.editText_m_area).setText((mm / factor.pow(resources.getInteger(R.integer.three_steps))).toString())

        view.findViewById<EditText>(R.id.editText_dm_area).setText((mm / factor.pow(resources.getInteger(R.integer.two_steps))).toString())

        view.findViewById<EditText>(R.id.editText_cm_area).setText((mm / factor.pow(resources.getInteger(R.integer.one_step))).toString())

        changedByApp = false

    }

    private fun inputMi(view: View, mi: Double) {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_km_area).setText((mi / getString(R.string.sq_km_mi).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_hectares_area).setText((mi / getString(R.string.sq_ha_mi).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_ar_area).setText((mi / getString(R.string.sq_ar_mi).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_m_area).setText((mi / getString(R.string.sq_m_mi).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_dm_area).setText((mi / getString(R.string.sq_dm_mi).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cm_area).setText((mi / getString(R.string.sq_cm_mi).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mm_area).setText((mi / getString(R.string.sq_mm_mi).toDouble()).toString())

        changedByApp = false
    }
}
