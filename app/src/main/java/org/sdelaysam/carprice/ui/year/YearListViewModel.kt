package org.sdelaysam.carprice.ui.year

import androidx.lifecycle.ViewModel
import org.sdelaysam.carprice.data.api.defaultYear
import org.sdelaysam.carprice.data.interactor.PriceInteractor
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.ui.common.TextItemLayout
import org.sdelaysam.carprice.util.ui.IdentifiableLayout

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

class YearListViewModel(
    private val priceInteractor: PriceInteractor,
    private val navigation: AppNavigation
) : ViewModel() {

    fun getYearList(): List<IdentifiableLayout> {
        return (defaultYear downTo defaultYear - 100)
            .map {
                TextItemLayout(id = it.toString(), name = it.toString()) {
                    onYearSelected(it)
                }
            }
    }

    private fun onYearSelected(year: Int) {
        priceInteractor.selectCarYear(year)
        navigation.back()
    }
}