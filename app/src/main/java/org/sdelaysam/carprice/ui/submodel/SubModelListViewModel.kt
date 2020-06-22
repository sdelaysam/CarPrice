package org.sdelaysam.carprice.ui.submodel

import androidx.paging.DataSource
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.data.api.SubModel
import org.sdelaysam.carprice.data.interactor.SubModelInteractor
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.ui.common.BaseListViewModel
import org.sdelaysam.carprice.ui.common.ButtonItemLayout
import org.sdelaysam.carprice.ui.common.TextItemLayout
import org.sdelaysam.carprice.util.ui.DataLoader
import org.sdelaysam.carprice.util.ui.IdentifiableLayout

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class SubModelListViewModel(
    private val makeId: String,
    private val modelId: String,
    private val subModelInteractor: SubModelInteractor,
    private val navigation: AppNavigation
) : BaseListViewModel() {

    override val dataLoader = DataLoader(subModelInteractor)

    private val buttonItem: IdentifiableLayout = ButtonItemLayout(R.string.all_submodels, ::onAllSubModelsSelected)

    override val customItems = sortedMapOf(0 to buttonItem)

    init {
        refresh(false)
    }

    override fun getPagedDataSource(): DataSource.Factory<Int, IdentifiableLayout> {
        return subModelInteractor.getPagedDataSource(makeId, modelId)
            .map { TextItemLayout(it.id, it.name) { onSubModelSelected(it) } }
    }

    private fun onSubModelSelected(subModel: SubModel) {

    }

    private fun onAllSubModelsSelected() {}
}