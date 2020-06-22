package org.sdelaysam.carprice.ui.price

import io.reactivex.Observable
import org.sdelaysam.carprice.data.api.Make
import org.sdelaysam.carprice.data.api.Model
import org.sdelaysam.carprice.data.api.SubModel
import org.sdelaysam.carprice.data.interactor.PriceInteractor
import org.sdelaysam.carprice.data.storage.AppStorage
import org.sdelaysam.carprice.navigation.AppNavigation
import org.sdelaysam.carprice.util.rx.RxSchedulers
import org.sdelaysam.carprice.util.rx.RxViewModel

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

class PriceViewModel(
    private val appStorage: AppStorage,
    private val priceInteractor: PriceInteractor,
    private val navigation: AppNavigation
) : RxViewModel() {

    fun observeMake(): Observable<Make> {
        return priceInteractor.observeMake()
            .distinctUntilChanged()
            .observeOn(RxSchedulers.main)
    }

    fun observeModel(): Observable<Model> {
        return priceInteractor.observeModel()
            .distinctUntilChanged()
            .observeOn(RxSchedulers.main)
    }

    fun observeSubModel(): Observable<SubModel> {
        return priceInteractor.observeSubModel()
            .distinctUntilChanged()
            .observeOn(RxSchedulers.main)
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

    fun reset() {
        priceInteractor.reset()
        navigation.openStart(false)
    }

}