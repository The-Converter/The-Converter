package com.example.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.fragment_speed.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [Speed.newInstance] factory method to
 * create an instance of this fragment.
 */
class Speed : Fragment() {

    private var changedByApp: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<EditText>(R.id.editText_kmh).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditTextViews(view)
                else
                    setFromKmh(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_ms).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditTextViews(view)
                else
                    setFromMs(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_mph).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditTextViews(view)
                else
                    setFromMph(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_fts).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditTextViews(view)
                else
                    setFromFts(view, content.toString().toDouble())
            }
        }

        view.findViewById<EditText>(R.id.editText_kn).doAfterTextChanged { content ->
            if (!changedByApp) {
                if (content.isNullOrEmpty())
                    clearEditTextViews(view)
                else
                    setFromKn(view, content.toString().toDouble())
            }
        }
    }

    private fun setFromKmh(view:View, kmh: Double) {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_ms).setText((kmh / getString(R.string.ms_kmh).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mph).setText((kmh * getString(R.string.kmh_mph).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_fts).setText((kmh * getString(R.string.kmh_fts).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kn).setText((kmh * getString(R.string.kmh_kn).toDouble()).toString())

        changedByApp = false
    }

    private fun setFromMs(view: View, ms: Double) {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_kmh).setText((ms * getString(R.string.ms_kmh).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mph).setText((ms * getString(R.string.ms_mph).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_fts).setText((ms * getString(R.string.ms_fts).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kn).setText((ms * getString(R.string.ms_kn).toDouble()).toString())

        changedByApp = false
    }

    private fun setFromMph(view: View, mph: Double) {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_kmh).setText((mph / getString(R.string.kmh_mph).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_ms).setText((mph / getString(R.string.ms_mph).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_fts).setText((mph * getString(R.string.mph_fts).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kn).setText((mph * getString(R.string.mph_kn).toDouble()).toString())

        changedByApp = false
    }

    private fun setFromFts(view: View, fts: Double) {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_kmh).setText((fts / getString(R.string.kmh_fts).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_ms).setText((fts / getString(R.string.ms_fts).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mph).setText((fts / getString(R.string.mph_fts).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_kn).setText((fts * getString(R.string.fts_kn).toDouble()).toString())

        changedByApp = false
    }

    private fun setFromKn(view: View, kn: Double) {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_kmh).setText((kn / getString(R.string.kmh_kn).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_ms).setText((kn / getString(R.string.ms_kn).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_mph).setText((kn / getString(R.string.mph_kn).toDouble()).toString())

        view.findViewById<EditText>(R.id.editText_fts).setText((kn / getString(R.string.fts_kn).toDouble()).toString())

        changedByApp = false
    }

    private fun clearEditTextViews(view: View) {
        changedByApp = true

        view.findViewById<EditText>(R.id.editText_kmh).getText().clear()

        view.findViewById<EditText>(R.id.editText_ms).getText().clear()

        view.findViewById<EditText>(R.id.editText_mph).getText().clear()

        view.findViewById<EditText>(R.id.editText_fts).getText().clear()

        view.findViewById<EditText>(R.id.editText_kn).getText().clear()

        changedByApp = false
    }
}
