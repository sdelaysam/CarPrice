package org.sdelaysam.carprice.util.rx

import io.reactivex.Completable
import io.reactivex.Observable
import org.sdelaysam.arch.util.BufferSingleValueWhileIdleOperator
import org.sdelaysam.arch.util.BufferWhileIdleOperator

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

fun Completable.catchErrors(uiSubject: UiSubject<Throwable>): Completable = onErrorComplete {
    uiSubject.accept(it)
    true
}