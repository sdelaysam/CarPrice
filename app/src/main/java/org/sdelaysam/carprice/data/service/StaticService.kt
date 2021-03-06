package org.sdelaysam.carprice.data.service

import io.reactivex.Single
import org.sdelaysam.carprice.data.api.Make
import org.sdelaysam.carprice.data.api.Model
import org.sdelaysam.carprice.data.api.SubModel
import retrofit2.http.GET

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

interface StaticService {
    @GET("makes/")
    fun getMakes(): Single<List<Make>>

    @GET("models/")
    fun getModels(): Single<List<Model>>

    @GET("submodels/")
    fun getSubModels(): Single<List<SubModel>>
}