package org.sdelaysam.carprice.ui.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_price.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.data.api.isEmpty
import org.sdelaysam.carprice.util.ui.NoToolbar
import org.sdelaysam.carprice.util.ui.RxFragment

/**
 * Created on 6/22/20.
 * @author sdelaysam
 */

class PriceFragment : RxFragment(), NoToolbar {

    private val viewModel: PriceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_price, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reset.setOnClickListener { viewModel.reset() }
        make_button.setOnClickListener { viewModel.selectMake() }
        model_button.setOnClickListener { viewModel.selectModel() }
        submodel_button.setOnClickListener { viewModel.selectSubModel() }

        viewModel.observeMake()
            .subscribe {
                if (it.isEmpty) {
                    viewModel.reset()
                } else {
                    make_button.text = it.name
                }
            }
            .untilDestroy()

        viewModel.observeModel()
            .map { if (it.isEmpty) getString(R.string.all) else it.name  }
            .subscribe { model_button.text = it }
            .untilDestroy()

        viewModel.observeSubModel()
            .map { if (it.isEmpty) getString(R.string.all) else it.name  }
            .subscribe { submodel_button.text = it }
            .untilDestroy()

    }

}