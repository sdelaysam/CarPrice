package org.sdelaysam.carprice.data.service

import io.reactivex.Single
import org.sdelaysam.carprice.data.api.PricePrediction
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

interface PredictService {
    @GET("predict/price/")
    fun getPrice(
        @Query("make_id") makeId: String,
        @Query("year") year: Int,
        @Query("model_id") modelId: String? = null,
        @Query("submodel_id") subModelId: String? = null
    ): Single<PricePrediction>
}