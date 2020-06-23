package org.sdelaysam.carprice.ui.price

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.Observable
import org.sdelaysam.carprice.data.api.EmptyPricePrediction
import org.sdelaysam.carprice.data.api.PricePrediction
import org.sdelaysam.carprice.data.api.defaultYear
import org.sdelaysam.carprice.data.db.PriceView
import org.sdelaysam.carprice.data.interactor.PriceInteractor
import org.sdelaysam.carprice.data.model.LoadingType
import org.sdelaysam.carprice.data.storage.AppStorage
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.util.rx.RxSchedulers
import org.sdelaysam.carprice.util.rx.RxViewModel
import org.sdelaysam.carprice.util.rx.UiSubject
import org.sdelaysam.carprice.util.rx.catchErrors
import org.sdelaysam.carprice.util.ui.DataLoader
import org.sdelaysam.carprice.util.ui.DataLoaderDelegate

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

class PriceViewModel(
    private val appStorage: AppStorage,
    private val priceInteractor: PriceInteractor,
    private val navigation: AppNavigation
) : RxViewModel(), DataLoaderDelegate {

    private val dataLoader = DataLoader(this)

    private val dataSubject = BehaviorRelay.create<PricePrediction>()

    private val errorSubject = UiSubject<Throwable>(pausedObservable)

    init {
        priceInteractor.observeSelectionChanged()
            .startWith(Unit)
            .subscribe { refresh(false) }
            .untilDestroy()
    }

    fun refresh(manual: Boolean) {
        dataLoader.refresh(manual)
            .catchErrors(errorSubject)
            .subscribe()
            .untilDestroy()
    }

    fun observePriceData(): Observable<PricePrediction> {
        return dataSubject
            .distinctUntilChanged()
            .observeOn(RxSchedulers.main)
    }

    fun observePriceView(): Observable<PriceView> {
        return priceInteractor.observePriceView()
            .distinctUntilChanged()
            .observeOn(RxSchedulers.main)
    }

    fun observeErrors(): Observable<Throwable> {
        return errorSubject.observable
    }

    fun observeLoading(): Observable<LoadingType> {
        return dataLoader.observeLoadingType()
    }

    fun getYear(): Int {
        return appStorage.year ?: defaultYear
    }

    fun selectMake() {
        navigation.openMakeList()
    }

    fun selectModel() {
        val makeId = appStorage.makeId ?: return
        navigation.openModelsList(makeId)
    }

    fun selectSubModel() {
        val makeId = appStorage.makeId ?: return
        val modelId = appStorage.modelId ?: return
        navigation.openSubModelsList(makeId, modelId)
    }

    fun selectYear() {
        navigation.openYearList()
    }

    fun reset() {
        priceInteractor.reset()
        navigation.openStart(false)
    }

    override val hasLocalData: Boolean
        get() = dataSubject.hasValue()

    override fun reload(): Completable {
        return priceInteractor.refresh()
            .doOnError { dataSubject.accept(EmptyPricePrediction) }
            .doOnSuccess { dataSubject.accept(it) }
            .ignoreElement()
    }

}