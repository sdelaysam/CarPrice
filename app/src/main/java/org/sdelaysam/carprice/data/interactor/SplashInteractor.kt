package org.sdelaysam.carprice.data.interactor

import io.reactivex.Single
import org.sdelaysam.carprice.data.db.MakeDao
import org.sdelaysam.carprice.data.storage.AppStorage
import org.sdelaysam.carprice.util.rx.RxSchedulers

/**
 * Created on 6/22/20.
 * @author sdelaysam
 */

interface SplashInteractor {
    fun hasCarSelected(): Single<Boolean>
}

class DefaultSplashInteractor(
    private val appStorage: AppStorage,
    private val makeDao: MakeDao
): SplashInteractor {

    override fun hasCarSelected(): Single<Boolean> {
        val makeId = appStorage.makeId ?: return Single.just(false)
        return makeDao.getMake(makeId)
            .subscribeOn(RxSchedulers.computation)
            .isEmpty
            .map { !it }
    }

}