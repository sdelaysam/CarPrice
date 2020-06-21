package org.sdelaysam.carprice.di

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.koin.dsl.module

/**
 * Created on 6/20/20.
 * @author sdelaysam
 */

val jsonModule = module {
    single {
        Json(
            JsonConfiguration(
                encodeDefaults = false,
                ignoreUnknownKeys = true
            )
        )
    }
}