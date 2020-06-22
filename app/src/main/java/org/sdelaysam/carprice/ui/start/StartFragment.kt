package org.sdelaysam.carprice.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_start.*
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.util.ui.NoToolbar

/**
 * Created on 6/22/20.
 * @author sdelaysam
 */

class StartFragment : Fragment(), NoToolbar {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start_button.setOnClickListener {
            findNavController().navigate(R.id.makeList)
        }
    }
}