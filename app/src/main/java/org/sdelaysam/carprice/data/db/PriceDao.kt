package org.sdelaysam.carprice.data.db

import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import io.reactivex.Observable
import org.sdelaysam.carprice.data.api.Make
import org.sdelaysam.carprice.data.api.Model
import org.sdelaysam.carprice.data.api.SubModel

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

@Dao
interface PriceDao {

    @RawQuery(observedEntities = [Make::class, Model::class, SubModel::class])
    fun observePriceView(query: SupportSQLiteQuery): Observable<PriceView>

}