package org.sdelaysam.carprice.navigation

import androidx.navigation.NavController
import io.reactivex.disposables.Disposable
import org.sdelaysam.carprice.R
import org.sdelaysam.carprice.ui.model.ModelListFragmentDirections
import org.sdelaysam.carprice.ui.submodel.SubModelListFragmentDirections
import org.sdelaysam.carprice.util.app.AppState
import org.sdelaysam.carprice.util.app.AppStateProvider
import org.sdelaysam.carprice.util.navigation.NavControllerProvider
import org.sdelaysam.carprice.util.rx.UiSubject

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
    fun openYearList()
    fun back()
}

typealias NavigateAction = (NavController) -> Unit

class DefaultAppNavigation(
    appStateProvider: AppStateProvider,
    private val navControllerProvider: NavControllerProvider
): AppNavigation {

    private val navigateSubject: UiSubject<NavigateAction>
    private val disposable: Disposable

    init {
        val pausedObservable = appStateProvider.observeState()
            .map { it == AppState.Background }
            .distinctUntilChanged()
        navigateSubject = UiSubject(pausedObservable, bufferSize = null)
        disposable = navigateSubject.observable
            .subscribe { action ->
                navControllerProvider.navController?.let { action(it) }
            }
    }


    override fun openStart(initial: Boolean) {
        post {
            if (initial) {
                it.navigate(R.id.splashToStart)
            } else {
                it.navigate(R.id.openStart)
            }
        }
    }

    override fun openPrice(initial: Boolean) {
        post {
            if (initial) {
                it.navigate(R.id.splashToPrice)
            } else {
                if (it.popBackStack(R.id.start, true)) {
                    it.navigate(R.id.openPrice)
                } else if (!it.popBackStack(R.id.price, false)) {
                    it.navigate(R.id.openPrice)
                }
            }
        }
    }

    override fun openMakeList() {
        post {
            it.navigate(R.id.openMakeList)
        }
    }

    override fun openModelsList(makeId: String) {
        post {
            it.navigate(ModelListFragmentDirections.openModelsList(makeId))
        }
    }

    override fun openSubModelsList(makeId: String, modelId: String) {
        post {
            it.navigate(SubModelListFragmentDirections.openSubModelsList(makeId, modelId))
        }
    }

    override fun openYearList() {
        post {
            it.navigate(R.id.openYearList)
        }
    }

    override fun back() {
        post {
            it.navigateUp()
        }
    }

    private fun post(action: NavigateAction) {
        navigateSubject.accept(action)
    }

}