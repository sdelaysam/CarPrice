package org.sdelaysam.carprice.data.db

import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.inject
import org.sdelaysam.carprice.data.api.Model

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class ModelDaoTests : BaseDaoTest() {

    private val modelDao: ModelDao by inject()

    @Test
    fun observeModelsSucceed() {
        val makeId = "1"

        val listObserver = modelDao.observeModels(makeId).test()
        listObserver.assertValueCount(1)
        listObserver.assertValueAt(0) { it.isEmpty() }
        listObserver.assertNotComplete()

        val models = listOf(buildModel("1", makeId, "name", true))
        modelDao.insertModels(models)

        listObserver.assertValueCount(2)
        listObserver.assertValueAt(1) { it == models }
        listObserver.assertNotComplete()
    }

    @Test
    fun filterModelsSucceed() {
        val makeId = "1"
        val otherMakeId = "2"

        val model1 = buildModel(makeId, "1", "name1", false)
        val model2 = buildModel(makeId, "2", "z-model", true)
        val model3 = buildModel(makeId, "3", "a-model", true)
        val model4 = buildModel(otherMakeId, "4", "a-model", true)
        modelDao.insertModels(listOf(model1, model2, model3, model4))
        val listObserver = modelDao.observeModels(makeId).test()
        listObserver.assertValueCount(1)
        listObserver.assertNotComplete()

        val list = listObserver.values().first()
        assertEquals(2, list.size)
        assertEquals(model3, list[0])
        assertEquals(model2, list[1])
    }

    private fun buildModel(makeId: String, id: String, name: String, active: Boolean) = Model(
        id = id,
        makeId = makeId,
        name = name,
        active = active,
        createdAt = DateTime.now(),
        updatedAt = DateTime.now()
    )

}