package com.bokorzslt.ui.di

import com.bokorzslt.ui.features.details.DetailsViewModel
import com.bokorzslt.ui.features.details.tabs.similar_movies.SimilarMoviesViewModel
import com.bokorzslt.ui.features.details.tabs.trailers.TrailersViewModel
import com.bokorzslt.ui.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get(), get(), get()) }
    viewModel { TrailersViewModel(get(), get(), get()) }
    viewModel { SimilarMoviesViewModel(get(), get(), get()) }
}