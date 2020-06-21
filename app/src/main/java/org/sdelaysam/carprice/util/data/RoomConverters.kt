package org.sdelaysam.carprice.util.data

import androidx.room.TypeConverter
import org.joda.time.DateTime

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

object RoomConverters {

    @TypeConverter
    @JvmStatic
    fun toDateTime(value: Long?): DateTime? {
        return value?.let { DateTime(it) }
    }

    @TypeConverter
    @JvmStatic
    fun fromDateTime(value: DateTime?): Long? {
        return value?.millis
    }

    @TypeConverter
    @JvmStatic
    fun toStringList(value: String?): List<String>? {
        return value?.split("\t")
    }

    @TypeConverter
    @JvmStatic
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString("\t")
    }

}