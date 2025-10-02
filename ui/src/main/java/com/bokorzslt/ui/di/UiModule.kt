package com.bokorzslt.ui.di

import com.bokorzslt.ui.features.details.DetailsViewModel
import com.bokorzslt.ui.features.details.tabs.comments.CommentsViewModel
import com.bokorzslt.ui.features.details.tabs.similar_movies.SimilarMoviesViewModel
import com.bokorzslt.ui.features.details.tabs.trailers.TrailersViewModel
import com.bokorzslt.ui.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel { HomeViewModel(get(), get()) }
    viewModel { (movieId: Long) -> DetailsViewModel(movieId, get(), get()) }
    viewModel { (movieId: Long) -> TrailersViewModel(movieId, get(), get()) }
    viewModel { (movieId: Long) -> SimilarMoviesViewModel(movieId, get(), get()) }
    viewModel { (movieId: Long) -> CommentsViewModel(movieId, get(), get()) }
}