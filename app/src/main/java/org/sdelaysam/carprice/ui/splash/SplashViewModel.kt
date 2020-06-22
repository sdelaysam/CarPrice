package org.sdelaysam.carprice.ui.splash

import org.sdelaysam.carprice.data.interactor.SplashInteractor
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.util.rx.RxSchedulers
import org.sdelaysam.carprice.util.rx.RxViewModel

/**
 * Created on 6/22/20.
 * @author sdelaysam
 */

class SplashViewModel(
    private val splashInteractor: SplashInteractor,
    private val navigation: AppNavigation
) : RxViewModel() {

    init {
        splashInteractor
            .hasCarSelected()
            .onErrorReturnItem(false)
            .observeOn(RxSchedulers.main)
            .subscribe(::navigate)
            .untilDestroy()
    }

    private fun navigate(hasCarSelected: Boolean) {
        if (hasCarSelected) {
            navigation.openPrice(true)
        } else {
            navigation.openStart(true)
        }
    }

}