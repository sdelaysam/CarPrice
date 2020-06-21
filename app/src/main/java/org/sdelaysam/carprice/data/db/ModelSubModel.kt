package org.sdelaysam.carprice.data.db

import androidx.room.Entity

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

@Entity(
    tableName = AppDatabase.TableModelSubModel,
    primaryKeys = ["modelId", "subModelId"]
)
data class ModelSubModel(
    val modelId: String,
    val subModelId: String
)