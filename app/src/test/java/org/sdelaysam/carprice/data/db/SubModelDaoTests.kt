package org.sdelaysam.carprice.data.db

import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.inject
import org.sdelaysam.carprice.data.api.SubModel

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

class SubModelDaoTests : BaseDaoTest() {

    private val subModelDao: SubModelDao by inject()

    @Test
    fun observeSubModelsSucceed() {
        val makeId = "1"
        val modelId = "1"
        val listObserver = subModelDao.observeSubModels(makeId, modelId).test()
        listObserver.assertValueCount(1)
        listObserver.assertValueAt(0) { it.isEmpty() }
        listObserver.assertNotComplete()

        val subModels = listOf(buildSubModel(makeId, "1", listOf(modelId, modelId), "name1", true))
        subModelDao.insertInTransaction(subModels)

        listObserver.assertValueCount(2)
        listObserver.assertValueAt(1) { it == subModels }
        listObserver.assertNotComplete()
    }

    @Test
    fun filterSubModelsSucceed() {
        val makeId = "1"
        val otherMakeId = "2"

        val modelId = "1"
        val otherModelId = "2"

        val subModel1 = buildSubModel(makeId, "1", listOf(modelId, otherModelId), "name1", false)
        val subModel2 = buildSubModel(makeId, "2", listOf(modelId, otherModelId), "z-subModel", true)
        val subModel3 = buildSubModel(makeId, "3", listOf(modelId, otherModelId), "a-subModel", true)
        val subModel4 = buildSubModel(makeId, "4", listOf(otherModelId), "name4", true)
        val subModel5 = buildSubModel(otherMakeId, "5", listOf(modelId, otherModelId), "name4", true)

        subModelDao.insertInTransaction(listOf(subModel1, subModel2, subModel3, subModel4, subModel5))
        val listObserver = subModelDao.observeSubModels(makeId, modelId).test()
        listObserver.assertValueCount(1)
        listObserver.assertNotComplete()

        val list = listObserver.values().first()
        assertEquals(2, list.size)
        assertEquals(subModel3, list[0])
        assertEquals(subModel2, list[1])
    }

    private fun buildSubModel(
        makeId: String,
        id: String,
        modelIds: List<String>,
        name: String,
        active: Boolean
    ): SubModel = SubModel(
        id = id,
        makeId = makeId,
        modelIds = modelIds,
        name = name,
        active = active,
        createdAt = DateTime.now(),
        updatedAt = DateTime.now()
    )
}