package org.sdelaysam.carprice.di

import android.app.Application
import org.koin.dsl.module
import org.sdelaysam.carprice.data.storage.AppStorage
import org.sdelaysam.carprice.data.storage.DefaultAppStorage
import org.sdelaysam.carprice.navigation.DefaultAppNavigation
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.util.app.AppLifecycleCallbacks
import org.sdelaysam.carprice.util.app.AppStateProvider

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

val appModule = module {

    val appCallbacks = AppLifecycleCallbacks()

    single<Application.ActivityLifecycleCallbacks> { appCallbacks }

    single<AppStateProvider> { appCallbacks }

    single<AppNavigation> { DefaultAppNavigation(appCallbacks, appCallbacks) }

    single<AppStorage> { DefaultAppStorage(get()) }
}