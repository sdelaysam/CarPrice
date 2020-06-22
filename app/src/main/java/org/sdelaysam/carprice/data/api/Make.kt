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

@Entity(tableName = AppDatabase.TableMake)
@TypeConverters(RoomConverters::class)
@Serializable
data class Make(
    @PrimaryKey
    @SerialName("id")
    val id: String,
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

val EmptyMake = Make(id = "_", name = "_", active = false)

val Make.isEmpty: Boolean
    get() = this === EmptyMake