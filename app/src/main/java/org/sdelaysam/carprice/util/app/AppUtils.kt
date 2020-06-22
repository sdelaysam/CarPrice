package org.sdelaysam.carprice.util.app

import org.sdelaysam.carprice.BuildConfig

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

val isDebug = BuildConfig.DEBUG

val isRobolectric: Boolean by lazy {
    try {
        Class.forName("org.robolectric.RobolectricTestRunner")
        true
    } catch (e: ClassNotFoundException) {
        false
    }
}
