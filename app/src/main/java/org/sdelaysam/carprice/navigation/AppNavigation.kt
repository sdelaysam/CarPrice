package org.sdelaysam.carprice.navigation

import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.ui.model.ModelListFragmentDirections
import org.sdelaysam.carprice.ui.submodel.SubModelListFragmentDirections
import org.sdelaysam.carprice.util.navigation.NavControllerProvider

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

interface AppNavigation {
    fun openStart(initial: Boolean)
    fun openPrice(initial: Boolean)
    fun openMakeList()
    fun openModelsList(makeId: String)
    fun openSubModelsList(makeId: String, modelId: String)
}

class DefaultAppNavigation(
    private val navControllerProvider: NavControllerProvider
): AppNavigation {

    override fun openStart(initial: Boolean) {
        if (initial) {
            navControllerProvider.navController
                ?.navigate(R.id.splashToStart)
        } else {
            navControllerProvider.navController
                ?.navigate(R.id.openStart)
        }
    }

    override fun openPrice(initial: Boolean) {
        if (initial) {
            navControllerProvider.navController
                ?.navigate(R.id.splashToPrice)
        } else {
            navControllerProvider.navController
                ?.navigate(R.id.openPrice)
        }
    }

    override fun openMakeList() {
        navControllerProvider.navController
            ?.navigate(R.id.openMakeList)
    }

    override fun openModelsList(makeId: String) {
        navControllerProvider.navController
            ?.navigate(ModelListFragmentDirections.openModelsList(makeId))
    }

    override fun openSubModelsList(makeId: String, modelId: String) {
        navControllerProvider.navController
            ?.navigate(SubModelListFragmentDirections.openSubModelsList(makeId, modelId))
    }
}