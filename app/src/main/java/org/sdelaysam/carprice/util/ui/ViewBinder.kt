package org.sdelaysam.carprice.util.ui

import android.view.View
import io.reactivex.disposables.Disposable

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

interface ViewBinder {
    fun bind(view: View)
}

interface DisposableViewBinder {
    fun bind(view: View): Disposable
}