package com.bokorzslt.domain.di

import com.bokorzslt.domain.dispatchers.DefaultDispatchersProvider
import com.bokorzslt.domain.dispatchers.DispatchersProvider
import com.bokorzslt.domain.features.details.usecase.GetMovieDetailsUseCase
import com.bokorzslt.domain.features.details.usecase.GetMovieReviewsUseCase
import com.bokorzslt.domain.features.details.usecase.GetMovieTrailersUseCase
import com.bokorzslt.domain.features.details.usecase.GetSimilarMoviesUseCase
import com.bokorzslt.domain.features.home.usecase.GetHomePageStructureUseCase
import org.koin.dsl.module

val domainModule = module {
    single<DispatchersProvider> { DefaultDispatchersProvider() }

    single { GetHomePageStructureUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    single { GetMovieTrailersUseCase(get()) }
    single { GetSimilarMoviesUseCase(get()) }
    single { GetMovieReviewsUseCase(get()) }
}