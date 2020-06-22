package org.sdelaysam.carprice.util.rx

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

abstract class RxViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    private val pausedSubject = BehaviorRelay.createDefault(true)

    fun onResume() {
        pausedSubject.accept(false)
    }

    fun onPause() {
        pausedSubject.accept(true)
    }

    final override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    protected val pausedObservable: Observable<Boolean>
        get() = pausedSubject.hide()

    protected fun Disposable.untilDestroy() = disposables.add(this)
}