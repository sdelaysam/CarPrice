package org.sdelaysam.carprice.data.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.joda.time.DateTime
import org.sdelaysam.carprice.data.db.AppDatabase
import org.sdelaysam.carprice.util.data.DateTimeSerializer
import org.sdelaysam.carprice.util.data.RoomConverters

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

@Entity(tableName = AppDatabase.TableModel)
@TypeConverters(RoomConverters::class)
@Serializable
data class Model(
    @PrimaryKey
    @SerialName("id")
    val id: String,
    @SerialName("make_id")
    val makeId: String,
    @SerialName("name")
    val name: String,
    @SerialName("active")
    val active: Boolean,
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: DateTime? = null,
    @SerialName("updated_at")
    @Serializable(with = DateTimeSerializer::class)
    val updatedAt: DateTime? = null
)