package org.sdelaysam.carprice

import android.app.Application
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.sdelaysam.carprice.di.*
import org.sdelaysam.carprice.util.app.isDebug
import org.sdelaysam.carprice.util.rx.RxSchedulers
import timber.log.Timber

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

class App: Application() {

    private val callbacks: ActivityLifecycleCallbacks by inject()

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initSchedulers()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                networkModule,
                jsonModule,
                serviceModule,
                dbModule,
                interactorModule,
                vmModule
            )
        }
        registerActivityLifecycleCallbacks(callbacks)
    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.uprootAll()
        unregisterActivityLifecycleCallbacks(callbacks)
    }

    private fun initLogger() {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initSchedulers() {
        RxSchedulers.main = AndroidSchedulers.mainThread()
        RxSchedulers.network = Schedulers.io()
        RxSchedulers.computation = Schedulers.computation()
    }

}