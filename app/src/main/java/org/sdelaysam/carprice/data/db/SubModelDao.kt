package org.sdelaysam.carprice.data.db

import androidx.room.*
import io.reactivex.Observable
import org.sdelaysam.carprice.data.api.SubModel
import org.sdelaysam.carprice.data.api.toModelSubModels

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

@Dao
interface SubModelDao {

    @Query("""
       SELECT sm.* FROM ${AppDatabase.TableSubModel} sm 
            INNER JOIN ${AppDatabase.TableModelSubModel} msm ON sm.id=msm.subModelId 
            WHERE sm.makeId=:makeId AND msm.modelId=:modelId AND sm.active=1 
            ORDER BY sm.name ASC 
    """)
    fun observeSubModels(makeId: String, modelId: String): Observable<List<SubModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubModels(subModels: List<SubModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModelSubModels(modelSubModels: List<ModelSubModel>)

    @Query("DELETE FROM ${AppDatabase.TableSubModel}")
    fun deleteSubModels()

    @Query("DELETE FROM ${AppDatabase.TableModelSubModel}")
    fun deleteModelSubModels()

    @Transaction
    fun insertInTransaction(subModels: List<SubModel>) {
        deleteModelSubModels()
        deleteSubModels()
        insertSubModels(subModels)
        insertModelSubModels(subModels.flatMap { it.toModelSubModels() })
    }
}