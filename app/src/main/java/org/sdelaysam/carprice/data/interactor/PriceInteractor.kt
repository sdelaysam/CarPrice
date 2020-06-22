package org.sdelaysam.carprice.data.interactor

import io.reactivex.Observable
import org.sdelaysam.carprice.data.api.*
import org.sdelaysam.carprice.data.db.MakeDao
import org.sdelaysam.carprice.data.db.ModelDao
import org.sdelaysam.carprice.data.db.SubModelDao
import org.sdelaysam.carprice.data.storage.AppStorage
import org.sdelaysam.carprice.util.rx.RxSchedulers

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

interface PriceInteractor {
    fun observeMake(): Observable<Make>
    fun observeModel(): Observable<Model>
    fun observeSubModel(): Observable<SubModel>
    fun saveSelection(makeId: String?, modelId: String?, subModelId: String?)
    fun reset()
}

class DefaultPriceInteractor(
    private val appStorage: AppStorage,
    private val makeDao: MakeDao,
    private val modelDao: ModelDao,
    private val subModelDao: SubModelDao
): PriceInteractor {

    override fun observeMake(): Observable<Make> {
        val makeId = appStorage.makeId ?: return Observable.just(EmptyMake)
        return makeDao.getMake(makeId)
            .flatMapObservable { makeDao.observeMake(makeId) }
            .switchIfEmpty(Observable.just(EmptyMake))
            .subscribeOn(RxSchedulers.computation)
    }

    override fun observeModel(): Observable<Model> {
        val modelId = appStorage.modelId ?: return Observable.just(EmptyModel)
        return modelDao.getModel(modelId)
            .flatMapObservable { modelDao.observeModel(modelId) }
            .switchIfEmpty(Observable.just(EmptyModel))
            .subscribeOn(RxSchedulers.computation)
    }

    override fun observeSubModel(): Observable<SubModel> {
        val subModelId = appStorage.subModelId ?: return Observable.just(EmptySubModel)
        return subModelDao.getSubModel(subModelId)
            .flatMapObservable { subModelDao.observeSubModel(subModelId) }
            .switchIfEmpty(Observable.just(EmptySubModel))
            .subscribeOn(RxSchedulers.computation)
    }

    override fun saveSelection(makeId: String?, modelId: String?, subModelId: String?) {
        appStorage.makeId = makeId
        appStorage.modelId = modelId
        appStorage.subModelId = subModelId
    }

    override fun reset() {
        appStorage.makeId = null
        appStorage.modelId = null
        appStorage.subModelId = null
        appStorage.year = null
    }
}