package org.sdelaysam.carprice.data.service

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.koin.test.inject

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

class PredictServiceTests : BaseServiceTest() {

    private val predictService: PredictService by inject()

    @Test
    fun getPriceSucceed() {
        val observer = predictService.getPrice(makeId = "1", year = 2020).test()
        assertTrue(observer.awaitTerminalEvent())
        observer.assertNoErrors()
        observer.values().first().let {
            assertNotNull(it.currency)
            assertNotNull(it.result)
        }
    }

}