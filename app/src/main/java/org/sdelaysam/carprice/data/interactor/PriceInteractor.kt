package org.sdelaysam.carprice.data.interactor

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.Single
import org.sdelaysam.carprice.data.api.PricePrediction
import org.sdelaysam.carprice.data.api.defaultYear
import org.sdelaysam.carprice.data.db.EmptyPriceView
import org.sdelaysam.carprice.data.db.PriceDao
import org.sdelaysam.carprice.data.db.PriceView
import org.sdelaysam.carprice.data.db.getQuery
import org.sdelaysam.carprice.data.service.PredictService
import org.sdelaysam.carprice.data.storage.AppStorage

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

interface PriceInteractor {
    fun refresh(): Single<PricePrediction>
    fun observePriceView(): Observable<PriceView>
    fun observeSelectionChanged(): Observable<Unit>
    fun selectCarData(makeId: String?, modelId: String?, subModelId: String?)
    fun selectCarYear(year: Int?)
    fun reset()
}

class DefaultPriceInteractor(
    private val appStorage: AppStorage,
    private val predictService: PredictService,
    private val priceDao: PriceDao
): PriceInteractor {

    private val changedSubject = PublishRelay.create<Unit>()

    override fun refresh(): Single<PricePrediction> {
        val makeId = appStorage.makeId ?: return Single.error(Throwable())
        return predictService.getPrice(makeId = makeId,
            year = appStorage.year ?: defaultYear,
            modelId = appStorage.modelId,
            subModelId = appStorage.subModelId)
    }

    override fun observePriceView(): Observable<PriceView> {
        val makeId = appStorage.makeId ?: return Observable.just(EmptyPriceView)
        val query = PriceView.getQuery(makeId, appStorage.modelId, appStorage.subModelId)
        return priceDao.observePriceView(query)
    }

    override fun observeSelectionChanged(): Observable<Unit> {
        return changedSubject.hide()
    }

    override fun selectCarData(makeId: String?, modelId: String?, subModelId: String?) {
        if (appStorage.makeId != makeId
            || appStorage.modelId != modelId
            || appStorage.subModelId != subModelId) {
            appStorage.makeId = makeId
            appStorage.modelId = modelId
            appStorage.subModelId = subModelId
            changedSubject.accept(Unit)
        }
    }

    override fun selectCarYear(year: Int?) {
        if (appStorage.year != year) {
            appStorage.year = year
            changedSubject.accept(Unit)
        }
    }

    override fun reset() {
        appStorage.makeId = null
        appStorage.modelId = null
        appStorage.subModelId = null
        appStorage.year = null
    }
}