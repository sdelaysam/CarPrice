package org.sdelaysam.carprice.data.service

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.koin.test.inject

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

class StaticServiceTests: BaseServiceTest() {

    private val staticService: StaticService by inject()

    @Test
    fun getMakesSucceed() {
        val observer = staticService.getMakes().test()
        assertTrue(observer.awaitTerminalEvent())
        observer.assertNoErrors()
        observer.values().first().forEach {
            assertNotNull(it.active)
            assertNotNull(it.id)
            assertNotNull(it.name)
            assertNotNull(it.createdAt)
            assertNotNull(it.updatedAt)
        }
    }

    @Test
    fun getModelsSucceed() {
        val observer = staticService.getModels().test()
        assertTrue(observer.awaitTerminalEvent())
        observer.assertNoErrors()
        observer.values().first().forEach {
            assertNotNull(it.active)
            assertNotNull(it.id)
            assertNotNull(it.makeId)
            assertNotNull(it.name)
            assertNotNull(it.createdAt)
            assertNotNull(it.updatedAt)
        }
    }

    @Test
    fun getSubModelsSucceed() {
        val observer = staticService.getSubModels().test()
        assertTrue(observer.awaitTerminalEvent())
        observer.assertNoErrors()
        observer.values().first().forEach {
            assertNotNull(it.active)
            assertNotNull(it.id)
            assertTrue(it.makeId != null || !it.active)
            assertNotNull(it.modelIds)
            assertNotNull(it.name)
            assertNotNull(it.createdAt)
            assertNotNull(it.updatedAt)
        }
    }

}