package org.sdelaysam.carprice.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.sdelaysam.carprice.data.api.Make

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

@Dao
interface MakeDao {

    @Query("SELECT * FROM ${AppDatabase.TableMake} WHERE active=1 ORDER BY name ASC")
    fun getPagedDataSource(): DataSource.Factory<Int, Make>

    @Query("SELECT * FROM ${AppDatabase.TableMake} WHERE active=1 ORDER BY name ASC")
    fun observeMakes(): Observable<List<Make>>

    @Query("SELECT COUNT(*) FROM ${AppDatabase.TableMake} WHERE active=1")
    fun getMakesCount(): Int

    @Query("SELECT COUNT(*) FROM ${AppDatabase.TableMake} WHERE id=:makeId")
    fun hasMake(makeId: String): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMakes(makes: List<Make>)
}