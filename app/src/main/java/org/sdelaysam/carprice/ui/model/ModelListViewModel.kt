package org.sdelaysam.carprice.ui.model

import androidx.paging.DataSource
import io.reactivex.Observable
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.data.api.Model
import org.sdelaysam.carprice.data.interactor.ModelInteractor
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.ui.common.BaseListViewModel
import org.sdelaysam.carprice.ui.common.ButtonItemLayout
import org.sdelaysam.carprice.ui.common.TextItemLayout
import org.sdelaysam.carprice.util.ui.AlphabeticLayout
import org.sdelaysam.carprice.util.ui.DataLoader
import org.sdelaysam.carprice.util.ui.IdentifiableLayout
import java.util.*

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class ModelListViewModel(
    private val makeId: String,
    private val modelInteractor: ModelInteractor,
    private val navigation: AppNavigation
) : BaseListViewModel() {

    override val dataLoader = DataLoader(modelInteractor)

    private val buttonItem: IdentifiableLayout = ButtonItemLayout(R.string.all_models, ::onAllModelsSelected)

    override val customItems = sortedMapOf(0 to buttonItem)

    init {
        refresh(false)
    }

    override fun getPagedDataSource(): DataSource.Factory<Int, IdentifiableLayout> {
        return modelInteractor.getPagedDataSource(makeId)
            .map { TextItemLayout(it.id, it.name) { onModelSelected(it) } }
    }

    private fun onModelSelected(model: Model) {
        navigation.openSubModelsList(makeId, model.id)
    }

    private fun onAllModelsSelected() {}
}