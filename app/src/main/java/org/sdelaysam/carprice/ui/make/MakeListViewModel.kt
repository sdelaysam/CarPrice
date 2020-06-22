package org.sdelaysam.carprice.ui.make

import androidx.paging.DataSource
import org.sdelaysam.carprice.data.api.Make
import org.sdelaysam.carprice.data.interactor.MakeInteractor
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.ui.common.BaseListViewModel
import org.sdelaysam.carprice.ui.common.TextItemLayout
import org.sdelaysam.carprice.util.ui.DataLoader
import org.sdelaysam.carprice.util.ui.IdentifiableLayout

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class MakeListViewModel(
    private val makeInteractor: MakeInteractor,
    private val navigation: AppNavigation
) : BaseListViewModel() {

    override val dataLoader = DataLoader(makeInteractor)

    init {
        refresh(false)
    }

    override fun getPagedDataSource(): DataSource.Factory<Int, IdentifiableLayout> {
        return makeInteractor.getPagedDataSource()
            .map { TextItemLayout(it.id, it.name) { onMakeSelected(it) } }
    }

    private fun onMakeSelected(make: Make) {
        navigation.openModelsList(make.id)
    }
}