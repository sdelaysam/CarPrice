package org.sdelaysam.carprice.ui.common

import androidx.paging.DataSource
import androidx.paging.toObservable
import io.reactivex.Observable
import org.sdelaysam.carprice.util.rx.catchErrors
import org.sdelaysam.carprice.data.model.LoadingType
import org.sdelaysam.carprice.util.rx.RxSchedulers
import org.sdelaysam.carprice.util.rx.RxViewModel
import org.sdelaysam.carprice.util.rx.UiSubject
import org.sdelaysam.carprice.util.ui.DataLoader
import org.sdelaysam.carprice.util.ui.IdentifiableLayout
import org.sdelaysam.carprice.util.ui.PagedListData
import java.util.*

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

abstract class BaseListViewModel : RxViewModel() {

    var firstVisiblePosition = 0

    private val errorSubject = UiSubject<Throwable>(pausedObservable)

    abstract val dataLoader: DataLoader

    abstract fun getPagedDataSource(): DataSource.Factory<Int, IdentifiableLayout>

    open val customItems: SortedMap<Int, IdentifiableLayout>? = null

    fun observeList(): Observable<PagedListData<IdentifiableLayout>> {
        return getPagedDataSource()
            .toObservable(
                pageSize = 25,
                initialLoadKey = firstVisiblePosition,
                notifyScheduler = RxSchedulers.main)
            .map {
                PagedListData(it, customItems)
            }
    }

    fun observeLoadingType(): Observable<LoadingType> {
        return dataLoader.observeLoadingType()
    }

    fun observeErrors(): Observable<Throwable> {
        return errorSubject.observable
    }

    fun refresh(manual: Boolean) {
        dataLoader.refresh(manual)
            .catchErrors(errorSubject)
            .subscribe()
            .untilDestroy()
    }
}