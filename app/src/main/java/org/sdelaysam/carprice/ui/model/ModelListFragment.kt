package org.sdelaysam.carprice.ui.model

import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.sdelaysam.carprice.ui.common.BaseListFragment
import org.sdelaysam.carprice.util.ui.AppPagedAdapter
import org.sdelaysam.carprice.util.ui.IdentifiableLayout

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class ModelListFragment : BaseListFragment<ModelListViewModel>() {

    private val args: ModelListFragmentArgs by navArgs()

    override val viewModel: ModelListViewModel by viewModel { parametersOf(args.makeId) }

    override val adapter = AppPagedAdapter<IdentifiableLayout>()
}