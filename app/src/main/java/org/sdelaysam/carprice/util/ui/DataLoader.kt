package org.sdelaysam.carprice.util.ui

import io.reactivex.Completable
import io.reactivex.Observable
import org.sdelaysam.carprice.data.model.LoadingType
import org.sdelaysam.carprice.util.rx.ActivityIndicator
import org.sdelaysam.carprice.util.rx.RxSchedulers
import org.sdelaysam.carprice.util.rx.trackActivity

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

interface DataLoaderDelegate {
    val localItemsCount: Int
    fun reload(): Completable
}

class DataLoader(private val delegate: DataLoaderDelegate) {

    private val activityIndicator = ActivityIndicator()

    private var isManualRefresh = false

    fun refresh(manual: Boolean): Completable {
        isManualRefresh = manual
        return delegate.reload()
            .trackActivity(activityIndicator)
    }

    fun observeLoadingType(): Observable<LoadingType> {
        return activityIndicator
            .observeOn(RxSchedulers.computation)
            .map {
                if (it) {
                    if (delegate.localItemsCount > 0) {
                        if (isManualRefresh) {
                            LoadingType.RefreshManual
                        } else {
                            LoadingType.RefreshAuto
                        }
                    } else {
                        LoadingType.Initial
                    }
                } else {
                    LoadingType.None
                }
            }
            .distinctUntilChanged()
            .observeOn(RxSchedulers.main)
    }
}