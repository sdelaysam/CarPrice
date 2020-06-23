package org.sdelaysam.carprice.data.db

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

data class PriceView(
    val makeName: String?,
    val modelName: String? = null,
    val subModelName: String? = null
) { companion object }

val EmptyPriceView = PriceView(null, null, null)

val PriceView.isEmpty: Boolean
    get() = this === EmptyPriceView

fun PriceView.Companion.getQuery(makeId: String, modelId: String?, subModelId: String?): SupportSQLiteQuery {
    val sb = StringBuilder()
    sb.append("SELECT t1.name as makeName")
    if (modelId != null) {
        sb.append(", t2.name as modelName")
    }
    if (subModelId != null) {
        sb.append(", t3.name as subModelName")
    }
    sb.append(" FROM ${AppDatabase.TableMake} t1")
    if (modelId != null) {
        sb.append(" INNER JOIN ${AppDatabase.TableModel} t2 ON t1.id=t2.makeId")
    }
    if (subModelId != null) {
        sb.append(" INNER JOIN ${AppDatabase.TableSubModel} t3 ON t1.id=t3.makeId")
    }
    sb.append(" WHERE t1.id=$makeId")
    if (modelId != null) {
        sb.append(" AND t2.id=$modelId")
    }
    if (subModelId != null) {
        sb.append(" AND t3.id=$subModelId")
    }
    return SimpleSQLiteQuery(sb.toString())
}