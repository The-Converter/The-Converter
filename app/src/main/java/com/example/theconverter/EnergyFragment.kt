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
 * Use the [EnergyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnergyFragment : Fragment() {

    private var changedByApp = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_energy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButton_choose).setOnClickListener {
            val action = EnergyFragmentDirections.actionEnergyFragmentToFirstFragment()
            findNavController().navigate(action)
        }

        view.findViewById<EditText>(R.id.editText_joule_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputJoule(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputKilojoule(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_calorie_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputCalorie(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputKilocalorie(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputWattHour(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputKilowattHour(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputBritishThermalUnit(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).doAfterTextChanged { content ->
            if(!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditText(view)
                else
                    inputFootPound(view, content.toString().toDouble())
            }
        }
    }

    private fun clearEditText(view: View) {

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).getText().clear()

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).getText().clear()

        view.findViewById<EditText>(R.id.editText_calorie_energy).getText().clear()

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).getText().clear()

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).getText().clear()

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).getText().clear()

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).getText().clear()

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).getText().clear()

        changedByApp = false
    }

    private fun inputJoule(view: View, joule: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).setText((joule / getString(R.string.energy_kilo).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_calorie_energy).setText((joule * getString(R.string.energy_joule_calorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).setText((joule * getString(R.string.energy_joule_kilocalorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).setText((joule * getString(R.string.energy_joule_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).setText((joule * getString(R.string.energy_joule_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).setText((joule * getString(R.string.energy_joule_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).setText((joule * getString(R.string.energy_joule_foot_pound).toDouble()).toString())

        changedByApp = false
    }

    private fun inputKilojoule(view: View, kilojoule: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).setText((kilojoule * getString(R.string.energy_kilo).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_calorie_energy).setText((kilojoule * getString(R.string.energy_kilojoule_calorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).setText((kilojoule * getString(R.string.energy_kilojoule_kilocalorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).setText((kilojoule * getString(R.string.energy_kilojoule_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).setText((kilojoule * getString(R.string.energy_kilojoule_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).setText((kilojoule * getString(R.string.energy_kilojoule_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).setText((kilojoule * getString(R.string.energy_kilojoule_foot_pound).toDouble()).toString())

        changedByApp = false
    }

    private fun inputCalorie(view: View, calorie: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).setText((calorie / getString(R.string.energy_joule_calorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).setText((calorie / getString(R.string.energy_kilojoule_calorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).setText((calorie / getString(R.string.energy_kilo).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).setText((calorie * getString(R.string.energy_calorie_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).setText((calorie * getString(R.string.energy_calorie_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).setText((calorie * getString(R.string.energy_calorie_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).setText((calorie * getString(R.string.energy_calorie_foot_pound).toDouble()).toString())

        changedByApp = false
    }

    private fun inputKilocalorie(view: View, kilocalorie: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).setText((kilocalorie / getString(R.string.energy_joule_kilocalorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).setText((kilocalorie / getString(R.string.energy_kilojoule_kilocalorie).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_calorie_energy).setText((kilocalorie * getString(R.string.energy_kilo).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).setText((kilocalorie * getString(R.string.energy_kilocalorie_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).setText((kilocalorie * getString(R.string.energy_kilocalorie_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).setText((kilocalorie * getString(R.string.energy_kilocalorie_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).setText((kilocalorie * getString(R.string.energy_kilocalorie_foot_pound).toDouble()).toString())

        changedByApp = false
    }

    private fun inputWattHour(view: View, wattHour: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).setText((wattHour/ getString(R.string.energy_joule_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).setText((wattHour/ getString(R.string.energy_kilojoule_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_calorie_energy).setText((wattHour/ getString(R.string.energy_calorie_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).setText((wattHour/ getString(R.string.energy_kilocalorie_watt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).setText((wattHour/ getString(R.string.energy_kilo).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).setText((wattHour* getString(R.string.energy_watt_hour_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).setText((wattHour* getString(R.string.energy_watt_hour_foot_pound).toDouble()).toString())

        changedByApp = false
    }

    private fun inputKilowattHour(view: View, kilowattHour: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).setText((kilowattHour/ getString(R.string.energy_joule_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).setText((kilowattHour/ getString(R.string.energy_kilojoule_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_calorie_energy).setText((kilowattHour/ getString(R.string.energy_calorie_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).setText((kilowattHour/ getString(R.string.energy_kilocalorie_kilowatt_hour).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).setText((kilowattHour* getString(R.string.energy_kilo).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).setText((kilowattHour* getString(R.string.energy_kilowatt_hour_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).setText((kilowattHour* getString(R.string.energy_kilowatt_hour_foot_pound).toDouble()).toString())

        changedByApp = false
    }

    private fun inputBritishThermalUnit(view: View, britishThermalUnit: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).setText((britishThermalUnit/ getString(R.string.energy_joule_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).setText((britishThermalUnit/ getString(R.string.energy_kilojoule_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_calorie_energy).setText((britishThermalUnit/ getString(R.string.energy_calorie_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).setText((britishThermalUnit/ getString(R.string.energy_kilocalorie_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).setText((britishThermalUnit/ getString(R.string.energy_watt_hour_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).setText((britishThermalUnit/ getString(R.string.energy_kilowatt_hour_british_thermal_unit).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_foot_pound_energy).setText((britishThermalUnit* getString(R.string.energy_british_thermal_unit_foot_pound).toDouble()).toString())

        changedByApp = false
    }

    private fun inputFootPound(view: View, footPound: Double){

        changedByApp = true

        view.findViewById<EditText>(R.id.editText_joule_energy).setText((footPound/ getString(R.string.energy_joule_foot_pound).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilojoule_energy).setText((footPound/ getString(R.string.energy_kilojoule_foot_pound).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_calorie_energy).setText((footPound/ getString(R.string.energy_calorie_foot_pound).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilocalorie_energy).setText((footPound/ getString(R.string.energy_kilocalorie_foot_pound).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_watt_hour_energy).setText((footPound/ getString(R.string.energy_watt_hour_foot_pound).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kilowatt_hour_energy).setText((footPound/ getString(R.string.energy_kilowatt_hour_foot_pound).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_british_thermal_unit_energy).setText((footPound/ getString(R.string.energy_british_thermal_unit_foot_pound).toDouble()).toString())

        changedByApp = false
    }

}
