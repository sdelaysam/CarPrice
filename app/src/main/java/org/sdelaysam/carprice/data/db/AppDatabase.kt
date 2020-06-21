package org.sdelaysam.carprice.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sdelaysam.carprice.data.api.Make
import org.sdelaysam.carprice.data.api.Model
import org.sdelaysam.carprice.data.api.SubModel

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

@Database(entities = [
    Make::class,
    Model::class,
    SubModel::class,
    ModelSubModel::class
], version = AppDatabase.Version)
abstract class AppDatabase : RoomDatabase() {

    abstract fun makeDao(): MakeDao
    abstract fun modelDao(): ModelDao
    abstract fun subModelDao(): SubModelDao

    companion object {
        const val TableMake = "Make"
        const val TableModel = "Model"
        const val TableSubModel = "SubModel"
        const val TableModelSubModel = "ModelSubModel"
        const val Version = 1
    }
}