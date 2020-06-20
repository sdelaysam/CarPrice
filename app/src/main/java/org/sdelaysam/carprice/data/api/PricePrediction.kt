package org.sdelaysam.carprice.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

@Serializable
class PricePrediction(
    @SerialName("currency")
    val currency: String,
    @SerialName("result")
    val result: Int
)