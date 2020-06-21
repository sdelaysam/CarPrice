package org.sdelaysam.carprice.util.app

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

val isRobolectric: Boolean by lazy {
    try {
        Class.forName("org.robolectric.RobolectricTestRunner")
        true
    } catch (e: ClassNotFoundException) {
        false
    }
}
