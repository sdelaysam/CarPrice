package org.sdelaysam.carprice.data.interactor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.sdelaysam.carprice.data.db.MakeDao
import org.sdelaysam.carprice.data.storage.AppStorage
import org.sdelaysam.carprice.util.rx.RxSchedulers

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

class SplashInteractorTests {

    private lateinit var sut: DefaultSplashInteractor

    private lateinit var appStorage: AppStorage

    private lateinit var makeDao: MakeDao

    @Before
    fun setup() {
        appStorage = mock()
        makeDao = mock()
        sut = DefaultSplashInteractor(appStorage, makeDao)
        RxSchedulers.main = Schedulers.trampoline()
        RxSchedulers.computation = Schedulers.trampoline()
    }

    @Test
    fun testLoggedIn() {
        whenever(appStorage.makeId).thenReturn("id")
        whenever(makeDao.hasMake(any())).thenReturn(Single.just(1))
        assertTrue(sut.hasCarSelected().blockingGet())
    }
}