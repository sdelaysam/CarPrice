package org.sdelaysam.carprice.ui.splash

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.sdelaysam.carprice.data.interactor.SplashInteractor
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.util.rx.RxSchedulers

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

class SplashViewModelTests {

    lateinit var sut: SplashViewModel

    lateinit var interactor: SplashInteractor

    lateinit var navigation: AppNavigation

    @Before
    fun setup() {
        interactor = mock()
        navigation = mock()
        RxSchedulers.main = Schedulers.trampoline()
        RxSchedulers.computation = Schedulers.trampoline()
    }

    @Test
    fun navigateToStartSucceed() {
        whenever(interactor.hasCarSelected()).doReturn(Single.just(false))
        sut = SplashViewModel(interactor, navigation)
        verify(navigation).openStart(true)
    }

}