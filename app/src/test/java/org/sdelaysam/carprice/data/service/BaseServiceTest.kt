package org.sdelaysam.carprice.data.service

import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.sdelaysam.carprice.di.jsonModule
import org.sdelaysam.carprice.di.networkModule
import org.sdelaysam.carprice.di.serviceModule
import org.sdelaysam.carprice.util.log.TimberPrintTree
import org.sdelaysam.carprice.util.rx.Schedulers
import timber.log.Timber

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

open class BaseServiceTest : KoinTest {

    @Before
    fun setup() {
        Timber.plant(TimberPrintTree())
        Schedulers.main = io.reactivex.schedulers.Schedulers.trampoline()
        Schedulers.network = io.reactivex.schedulers.Schedulers.trampoline()
        startKoin {
            modules(networkModule, jsonModule, serviceModule)
        }
    }

    @After
    fun teardown() {
        stopKoin()
        Timber.uprootAll()
    }
}