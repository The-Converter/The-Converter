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
 * Use the [VolumeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VolumeFragment : Fragment() {

    private var changedByApp = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton_choose).setOnClickListener {
            val action = VolumeFragmentDirections.actionVolumeFragmentToFirstFragment()
            findNavController().navigate(action)
        }

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputCubicMeter(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_liter_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputLiter(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputCubicCentimeter(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_milliliter_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputMilliliter(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputCubicFoot(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputCubicInch(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputUsDryGallon(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputUsLiquidGallon(view, content.toString().toDouble())
            }
        }

    }
    private fun clearEditText(view: View) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).getText().clear()

        view.findViewById<EditText>(R.id.editText_liter_volume).getText().clear()

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).getText().clear()

        view.findViewById<EditText>(R.id.editText_milliliter_volume).getText().clear()

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).getText().clear()

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).getText().clear()

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).getText().clear()

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).getText().clear()

        changedByApp = false
    }

    private fun inputCubicMeter(view: View, cubicMeter: Double) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_liter_volume).setText((cubicMeter * getString(R.string.volume_meter_liter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).setText((cubicMeter * getString(R.string.volume_meter_centimeter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_milliliter_volume).setText((cubicMeter * getString(R.string.volume_meter_milliliter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).setText((cubicMeter * getString(R.string.volume_meter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).setText((cubicMeter * getString(R.string.volume_meter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).setText((cubicMeter * getString(R.string.volume_meter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).setText((cubicMeter * getString(R.string.volume_meter_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }

    private fun inputLiter(view: View, liter: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).setText((liter / getString(R.string.volume_meter_liter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).setText((liter * getString(R.string.volume_liter_centimeter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_milliliter_volume).setText((liter * getString(R.string.volume_liter_milliliter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).setText((liter * getString(R.string.volume_liter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).setText((liter * getString(R.string.volume_liter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).setText((liter * getString(R.string.volume_liter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).setText((liter * getString(R.string.volume_liter_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }

    private fun inputCubicCentimeter(view: View, cubicCentiMeter: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).setText((cubicCentiMeter / getString(R.string.volume_meter_centimeter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_liter_volume).setText((cubicCentiMeter / getString(R.string.volume_liter_centimeter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_milliliter_volume).setText((cubicCentiMeter * getString(R.string.volume_centimeter_milliliter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).setText((cubicCentiMeter * getString(R.string.volume_centimeter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).setText((cubicCentiMeter * getString(R.string.volume_centimeter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).setText((cubicCentiMeter * getString(R.string.volume_centimeter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).setText((cubicCentiMeter * getString(R.string.volume_centimeter_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }

    private fun inputMilliliter(view: View,milliliter: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).setText((milliliter / getString(R.string.volume_meter_milliliter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_liter_volume).setText((milliliter / getString(R.string.volume_liter_milliliter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).setText((milliliter / getString(R.string.volume_centimeter_milliliter).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).setText((milliliter * getString(R.string.volume_milliliter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).setText((milliliter * getString(R.string.volume_milliliter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).setText((milliliter * getString(R.string.volume_milliliter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).setText((milliliter * getString(R.string.volume_milliliter_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }

    private fun inputCubicFoot(view: View,cubicFoot: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).setText((cubicFoot / getString(R.string.volume_meter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_liter_volume).setText((cubicFoot / getString(R.string.volume_liter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).setText((cubicFoot / getString(R.string.volume_centimeter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_milliliter_volume).setText((cubicFoot / getString(R.string.volume_milliliter_foot).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).setText((cubicFoot * getString(R.string.volume_foot_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).setText((cubicFoot * getString(R.string.volume_foot_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).setText((cubicFoot * getString(R.string.volume_foot_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }

    private fun inputCubicInch(view: View, cubicInch: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).setText((cubicInch / getString(R.string.volume_meter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_liter_volume).setText((cubicInch / getString(R.string.volume_liter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).setText((cubicInch / getString(R.string.volume_centimeter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_milliliter_volume).setText((cubicInch / getString(R.string.volume_milliliter_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).setText((cubicInch / getString(R.string.volume_foot_inch).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).setText((cubicInch * getString(R.string.volume_inch_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).setText((cubicInch * getString(R.string.volume_inch_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }

    private fun inputUsDryGallon(view: View,usDryGallon: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).setText((usDryGallon / getString(R.string.volume_meter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_liter_volume).setText((usDryGallon / getString(R.string.volume_liter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).setText((usDryGallon / getString(R.string.volume_centimeter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_milliliter_volume).setText((usDryGallon / getString(R.string.volume_milliliter_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).setText((usDryGallon / getString(R.string.volume_foot_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).setText((usDryGallon / getString(R.string.volume_inch_us_dry_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_liquid_gallon_volume).setText((usDryGallon / getString(R.string.volume_us_dry_gallon_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }

    private fun inputUsLiquidGallon(view: View,usLiquidGallon: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_cubic_meter_volume).setText((usLiquidGallon / getString(R.string.volume_meter_us_liquid_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_liter_volume).setText((usLiquidGallon / getString(R.string.volume_liter_us_liquid_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_centimeter_volume).setText((usLiquidGallon / getString(R.string.volume_centimeter_us_liquid_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_milliliter_volume).setText((usLiquidGallon / getString(R.string.volume_milliliter_us_liquid_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_foot_volume).setText((usLiquidGallon / getString(R.string.volume_foot_us_liquid_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_cubic_inch_volume).setText((usLiquidGallon / getString(R.string.volume_inch_us_liquid_gallon).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_us_dry_gallon_volume).setText((usLiquidGallon / getString(R.string.volume_us_dry_gallon_us_liquid_gallon).toDouble()).toString())

        changedByApp = false
    }



}
