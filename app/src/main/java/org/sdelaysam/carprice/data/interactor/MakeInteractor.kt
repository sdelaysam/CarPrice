package org.sdelaysam.carprice.data.interactor

import androidx.paging.DataSource
import io.reactivex.Completable
import org.sdelaysam.carprice.data.api.Make
import org.sdelaysam.carprice.data.db.MakeDao
import org.sdelaysam.carprice.data.service.StaticService
import org.sdelaysam.carprice.data.storage.AppStorage
import org.sdelaysam.carprice.util.ui.DataLoaderDelegate

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

interface MakeInteractor: DataLoaderDelegate {
    fun getPagedDataSource(): DataSource.Factory<Int, Make>
}

class DefaultMakeInteractor(
    private val staticService: StaticService,
    private val makeDao: MakeDao
): MakeInteractor {

    override val localItemsCount: Int
        get() = makeDao.getMakesCount()

    override fun getPagedDataSource(): DataSource.Factory<Int, Make> {
        return makeDao.getPagedDataSource()
    }

    override fun reload(): Completable {
        return staticService.getMakes()
            .doAfterSuccess { makeDao.insertMakes(it) }
            .ignoreElement()
    }
}

