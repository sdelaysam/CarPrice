package org.sdelaysam.carprice.data.model

import org.sdelaysam.carprice.BuildConfig

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

enum class ServiceType {
    Static,
    Predict
}

val ServiceType.baseUrl: String
    get() = when (this) {
        ServiceType.Static -> BuildConfig.API_STATIC
        ServiceType.Predict -> BuildConfig.API_V1
    }