package org.sdelaysam.carprice.util.rx

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import org.sdelaysam.arch.util.bufferWhile

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class UiSubject<T> constructor(
    pausedObservable: Observable<Boolean>,
    bufferSize: Int? = 1
) {

    private val relay = PublishRelay.create<T>()

    val observable: Observable<T> = relay.bufferWhile(pausedObservable, bufferSize)
        .observeOn(RxSchedulers.main)
        .publish()
        .apply { connect() }

    fun accept(value: T) = relay.accept(value)
}