package org.sdelaysam.carprice.ui.submodel

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

class SubModelListFragment : BaseListFragment<SubModelListViewModel>() {

    private val args: SubModelListFragmentArgs by navArgs()

    override val viewModel: SubModelListViewModel by viewModel { parametersOf(args.makeId, args.modelId) }

    override val adapter = AppPagedAdapter<IdentifiableLayout>()
}