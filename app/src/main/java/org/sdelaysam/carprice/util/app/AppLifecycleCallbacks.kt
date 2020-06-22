package org.sdelaysam.carprice.util.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.navigation.NavController
import org.sdelaysam.carprice.util.navigation.HasNavController
import org.sdelaysam.carprice.util.navigation.NavControllerProvider
import java.lang.ref.WeakReference

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class AppLifecycleCallbacks: Application.ActivityLifecycleCallbacks, NavControllerProvider {

    override val navController: NavController?
        get() = navControllerRef.get()?.navController

    private var navControllerRef = WeakReference<HasNavController>(null)

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
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (navControllerRef.get() == activity) {
            navControllerRef.clear()
        }
    }
}