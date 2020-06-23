package org.sdelaysam.carprice.di

import androidx.room.Room
import org.koin.dsl.module
import org.sdelaysam.carprice.data.db.AppDatabase
import org.sdelaysam.carprice.util.app.isRobolectric

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

val dbModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "AppDatabase")
            .also {
                if (isRobolectric) {
                    it.allowMainThreadQueries()
                    it.setQueryExecutor(Runnable::run)
                }
            }
            .build()
    }

    factory { get<AppDatabase>().makeDao() }
    factory { get<AppDatabase>().modelDao() }
    factory { get<AppDatabase>().subModelDao() }
    factory { get<AppDatabase>().priceDao() }
}