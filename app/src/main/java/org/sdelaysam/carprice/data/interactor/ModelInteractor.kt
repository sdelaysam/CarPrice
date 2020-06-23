package org.sdelaysam.carprice.data.interactor

import androidx.paging.DataSource
import io.reactivex.Completable
import org.sdelaysam.carprice.data.api.Model
import org.sdelaysam.carprice.data.db.ModelDao
import org.sdelaysam.carprice.data.service.StaticService
import org.sdelaysam.carprice.util.ui.DataLoaderDelegate

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

interface ModelInteractor: DataLoaderDelegate {
    fun getPagedDataSource(makeId: String): DataSource.Factory<Int, Model>
}

class DefaultModelInteractor(
    private val staticService: StaticService,
    private val modelDao: ModelDao
): ModelInteractor {

    override val hasLocalData: Boolean
        get() = modelDao.getModelsCount() > 0

    override fun getPagedDataSource(makeId: String): DataSource.Factory<Int, Model> {
        return modelDao.getPagedDataSource(makeId)
    }

    override fun reload(): Completable {
        return staticService.getModels()
            .doAfterSuccess { modelDao.insertModels(it) }
            .ignoreElement()
    }
}
