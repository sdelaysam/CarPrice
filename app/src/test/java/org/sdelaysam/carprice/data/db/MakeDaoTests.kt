package org.sdelaysam.carprice.data.db

import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.inject
import org.sdelaysam.carprice.data.api.Make

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class MakeDaoTests : BaseDaoTest() {

    private val makeDao: MakeDao by inject()

    @Test
    fun observeMakesSucceed() {
        val listObserver = makeDao.observeMakes().test()
        listObserver.assertValueCount(1)
        listObserver.assertValueAt(0) { it.isEmpty() }
        listObserver.assertNotComplete()

        val makes = listOf(buildMake("1", "name", true))
        makeDao.insertMakes(makes)

        listObserver.assertValueCount(2)
        listObserver.assertValueAt(1) { it == makes }
        listObserver.assertNotComplete()
    }

    @Test
    fun filterMakesSucceed() {
        val make1 = buildMake("1", "name1", false)
        val make2 = buildMake("2", "z-make", true)
        val make3 = buildMake("3", "a-make", true)
        makeDao.insertMakes(listOf(make1, make2, make3))
        val listObserver = makeDao.observeMakes().test()
        listObserver.assertValueCount(1)
        listObserver.assertNotComplete()

        val list = listObserver.values().first()
        assertEquals(2, list.size)
        assertEquals(make3, list[0])
        assertEquals(make2, list[1])
    }

    private fun buildMake(id: String, name: String, active: Boolean) = Make(
        id = id,
        name = name,
        active = active,
        createdAt = DateTime.now(),
        updatedAt = DateTime.now()
    )
}