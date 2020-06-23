package org.sdelaysam.carprice.util.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.navigation.NavController
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import org.sdelaysam.carprice.util.navigation.HasNavController
import org.sdelaysam.carprice.util.navigation.NavControllerProvider
import java.lang.ref.WeakReference

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class AppLifecycleCallbacks: Application.ActivityLifecycleCallbacks, NavControllerProvider, AppStateProvider {

    private var navControllerRef = WeakReference<HasNavController>(null)

    private val stateSubject = BehaviorRelay.create<AppState>()

    private var numResumed = 0

    override val navController: NavController?
        get() = navControllerRef.get()?.navController


    override fun observeState(): Observable<AppState> {
        return stateSubject.hide()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity is HasNavController) {
            navControllerRef = WeakReference(activity)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity is HasNavController) {
            navControllerRef = WeakReference(activity)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (numResumed++ == 0) {
            stateSubject.accept(AppState.Foreground)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        numResumed--
    }

    override fun onActivityStopped(activity: Activity) {
        if (numResumed == 0) {
            stateSubject.accept(AppState.Background)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (navControllerRef.get() == activity) {
            navControllerRef.clear()
        }
    }
}