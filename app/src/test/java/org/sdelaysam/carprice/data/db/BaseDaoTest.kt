package org.sdelaysam.carprice.data.db

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.sdelaysam.carprice.di.dbModule

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

@RunWith(RobolectricTestRunner::class)
abstract class BaseDaoTest : KoinTest {

    private val db: AppDatabase by inject()

    @Before
    fun setup() {
        startKoin {
            androidContext(RuntimeEnvironment.application)
            modules(dbModule)
        }
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
        stopKoin()
    }

}