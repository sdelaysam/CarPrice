package org.sdelaysam.carprice.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.sdelaysam.carprice.ui.make.MakeListViewModel
import org.sdelaysam.carprice.ui.model.ModelListViewModel
import org.sdelaysam.carprice.ui.price.PriceViewModel
import org.sdelaysam.carprice.ui.splash.SplashViewModel
import org.sdelaysam.carprice.ui.submodel.SubModelListViewModel

/**
 * Created on 6/21/20.
 * @author sdelaysam
 */

val vmModule = module {

    viewModel {
        SplashViewModel(get(), get())
    }
    viewModel {
        MakeListViewModel(get(), get())
    }
    viewModel {
        val makeId = it.get<String>(0)
        ModelListViewModel(makeId, get(), get(), get())
    }
    viewModel {
        val makeId = it.get<String>(0)
        val modelId = it.get<String>(1)
        SubModelListViewModel(makeId, modelId, get(), get(), get())
    }
    viewModel {
        PriceViewModel(get(), get(), get())
    }
}