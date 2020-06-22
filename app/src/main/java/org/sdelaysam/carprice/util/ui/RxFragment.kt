package org.sdelaysam.carprice.util.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

abstract class RxFragment : Fragment() {

    private val disposables = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    protected fun Disposable.untilDestroy() = disposables.add(this)
}