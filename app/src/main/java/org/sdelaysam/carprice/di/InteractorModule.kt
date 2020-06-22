package org.sdelaysam.carprice.di

import org.koin.dsl.module
import org.sdelaysam.carprice.data.interactor.*

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

val interactorModule = module {
    single<SplashInteractor> { DefaultSplashInteractor(get(), get()) }
    single<MakeInteractor> { DefaultMakeInteractor(get(), get()) }
    single<ModelInteractor> { DefaultModelInteractor(get(), get()) }
    single<SubModelInteractor> { DefaultSubModelInteractor(get(), get()) }
    single<PriceInteractor> { DefaultPriceInteractor(get(), get(), get(), get()) }
}