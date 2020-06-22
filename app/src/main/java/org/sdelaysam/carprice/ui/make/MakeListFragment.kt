package org.sdelaysam.carprice.ui.make

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.sdelaysam.carprice.ui.common.BaseListFragment
import org.sdelaysam.carprice.util.ui.AlphabeticAdapter
import org.sdelaysam.carprice.util.ui.IdentifiableLayout

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class MakeListFragment : BaseListFragment<MakeListViewModel>() {

    override val viewModel: MakeListViewModel by viewModel()

    override val adapter = AlphabeticAdapter<IdentifiableLayout>()
}