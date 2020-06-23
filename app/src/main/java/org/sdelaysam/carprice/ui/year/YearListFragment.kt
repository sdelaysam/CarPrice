package org.sdelaysam.carprice.ui.year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.util.ui.AppReloadAdapter
import org.sdelaysam.carprice.util.ui.IdentifiableLayout

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

class YearListFragment : Fragment() {

    private val adapter = AppReloadAdapter<IdentifiableLayout>()

    private val viewModel: YearListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
        adapter.submitList(viewModel.getYearList())
        refresher.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv.adapter = null
    }

}