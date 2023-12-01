package com.bokorzslt.data.di

import com.bokorzslt.data.features.movies.datasource.remote.MovieApiService
import com.bokorzslt.data.features.movies.datasource.remote.RemoteMovieDataSource
import com.bokorzslt.data.features.movies.repository.MovieRepositoryImpl
import com.bokorzslt.data.generic.network.helpers.OkHttpCreator
import com.bokorzslt.data.generic.network.helpers.RetrofitCreator
import com.bokorzslt.domain.features.movies.repository.MovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single { OkHttpCreator.createOkHttpClient(get()) }
    single { RetrofitCreator.createRetrofit(get()) }

    single<MovieApiService> { get<Retrofit>().create(MovieApiService::class.java) }
    single { RemoteMovieDataSource(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}