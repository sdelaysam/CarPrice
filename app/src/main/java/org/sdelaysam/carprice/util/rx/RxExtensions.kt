package org.sdelaysam.arch.util

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.sdelaysam.carprice.util.rx.UiSubject

/**
 * Created on 4/13/20.
 * @author sdelaysam
 */

fun <T> Observable<T>.bufferWhile(
    paused: Observable<Boolean>,
    bufferSize: Int? = 1
): Observable<T> {
    return lift(
        if (bufferSize == 1) {
            BufferSingleValueWhileIdleOperator(paused)
        } else {
            BufferWhileIdleOperator(paused, bufferSize)
        }
    )
}

fun Completable.catchErrors(uiSubject: UiSubject<Throwable>) = onErrorComplete {
    uiSubject.accept(it)
    true
}