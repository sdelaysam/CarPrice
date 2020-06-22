package org.sdelaysam.carprice.util.ui

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

abstract class RxActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    protected fun Disposable.untilDestroy() = disposables.add(this)
}