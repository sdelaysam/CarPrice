package org.sdelaysam.carprice.ui.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_price.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.data.api.isEmpty
import org.sdelaysam.carprice.data.db.isEmpty
import org.sdelaysam.carprice.data.model.LoadingType
import org.sdelaysam.carprice.util.ui.NoToolbar
import org.sdelaysam.carprice.util.ui.RxFragment
import org.sdelaysam.carprice.util.ui.moneyString
import org.sdelaysam.carprice.util.ui.showError

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
        configureView()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    private fun configureView() {
        refresher.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent)
        refresher.setOnRefreshListener { viewModel.refresh(true) }
        reset.setOnClickListener { viewModel.reset() }
        make_button.setOnClickListener { viewModel.selectMake() }
        model_button.setOnClickListener { viewModel.selectModel() }
        submodel_button.setOnClickListener { viewModel.selectSubModel() }
        year_button.setOnClickListener { viewModel.selectYear() }
    }

    private fun observeData() {
        year_button.text = viewModel.getYear().toString()
        viewModel.observePriceView()
            .subscribe {
                if (it.isEmpty) {
                    viewModel.reset()
                } else {
                    make_button.text = it.makeName
                    model_button.text = it.modelName ?: getString(R.string.all)
                    submodel_button.text = it.subModelName ?: getString(R.string.all)
                    submodel_label.isVisible = it.modelName != null
                    submodel_button.isVisible = it.modelName != null
                }
            }
            .untilDestroy()

        viewModel.observeLoading()
            .subscribe {
                when (it) {
                    LoadingType.Initial,
                    LoadingType.RefreshAuto -> showInitialLoading()
                    LoadingType.RefreshManual -> showRefreshing()
                    else -> hideLoading()
                }
            }
            .untilDestroy()

        viewModel.observeErrors()
            .subscribe(::showError)
            .untilDestroy()

        viewModel.observePriceData()
            .subscribe {
                if (it.isEmpty) {
                    price.setText(R.string.no_data)
                    currency.text = ""
                } else {
                    price.text = it.result.moneyString
                    currency.text = it.currency
                }
            }
            .untilDestroy()
    }

    private fun showInitialLoading() {
        progress.isVisible = true
        price.text = ""
        currency.text = ""
    }

    private fun showRefreshing() {
        progress.isVisible = false
        refresher.isRefreshing = true
    }

    private fun hideLoading() {
        progress.isVisible = false
        refresher.isRefreshing = false
    }

}