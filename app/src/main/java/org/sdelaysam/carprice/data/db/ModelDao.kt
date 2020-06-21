package org.sdelaysam.carprice.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import org.sdelaysam.carprice.data.api.Model

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

@Dao
interface ModelDao {

    @Query("SELECT * FROM ${AppDatabase.TableModel} WHERE makeId=:makeId AND active=1 ORDER BY name ASC")
    fun observeModels(makeId: String): Observable<List<Model>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModels(models: List<Model>)
}