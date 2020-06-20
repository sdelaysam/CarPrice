package org.sdelaysam.carprice.di

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.sdelaysam.carprice.data.model.ServiceType
import org.sdelaysam.carprice.data.service.PredictService
import org.sdelaysam.carprice.data.service.StaticService
import retrofit2.Retrofit

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

val serviceModule = module {

    factory<StaticService> {
        val retrofit = get<Retrofit> { parametersOf(ServiceType.Static) }
        retrofit.create(StaticService::class.java)
    }

    factory<PredictService> {
        val retrofit = get<Retrofit> { parametersOf(ServiceType.Predict) }
        retrofit.create(PredictService::class.java)
    }

}