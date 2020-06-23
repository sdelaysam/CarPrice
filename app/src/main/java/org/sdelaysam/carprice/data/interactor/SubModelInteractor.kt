package org.sdelaysam.carprice.data.interactor

import androidx.paging.DataSource
import io.reactivex.Completable
import org.sdelaysam.carprice.data.api.SubModel
import org.sdelaysam.carprice.data.db.SubModelDao
import org.sdelaysam.carprice.data.service.StaticService
import org.sdelaysam.carprice.util.ui.DataLoaderDelegate

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

interface SubModelInteractor: DataLoaderDelegate {
    fun getPagedDataSource(makeId: String, modelId: String): DataSource.Factory<Int, SubModel>
}

class DefaultSubModelInteractor(
    private val staticService: StaticService,
    private val subModelDao: SubModelDao
): SubModelInteractor {

    override val hasLocalData: Boolean
        get() = subModelDao.getSubModelsCount() > 0

    override fun getPagedDataSource(
        makeId: String,
        modelId: String
    ): DataSource.Factory<Int, SubModel> {
        return subModelDao.getPagedDataSource(makeId, modelId)
    }

    override fun reload(): Completable {
        return staticService.getSubModels()
            .doAfterSuccess {
                subModelDao.insertInTransaction(it)
            }
            .ignoreElement()
    }
}