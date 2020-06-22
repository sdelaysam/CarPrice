package org.sdelaysam.carprice.util.ui

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.sdelaysam.carprice.util.error.getHumanReadableMessage

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

fun Fragment.showError(throwable: Throwable) {
    Snackbar.make(requireView(), throwable.getHumanReadableMessage(requireContext()), Snackbar.LENGTH_SHORT).show()
}
