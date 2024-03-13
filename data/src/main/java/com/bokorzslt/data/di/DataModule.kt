package com.bokorzslt.data.di

import com.bokorzslt.data.features.home.datasource.remote.GenreApiService
import com.bokorzslt.data.features.home.datasource.remote.MovieApiService
import com.bokorzslt.data.features.home.datasource.remote.RemoteGenreDataSource
import com.bokorzslt.data.features.home.datasource.remote.RemoteMovieDataSource
import com.bokorzslt.data.features.home.mapper.GenreMapper
import com.bokorzslt.data.features.home.repository.GenreRepositoryImpl
import com.bokorzslt.data.features.home.repository.MovieRepositoryImpl
import com.bokorzslt.data.generic.network.helpers.OkHttpCreator
import com.bokorzslt.data.generic.network.helpers.RetrofitCreator
import com.bokorzslt.domain.features.home.repository.GenreRepository
import com.bokorzslt.domain.features.home.repository.MovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single { OkHttpCreator.createOkHttpClient(get()) }
    single { RetrofitCreator.createRetrofit(get()) }

    single<MovieApiService> { get<Retrofit>().create(MovieApiService::class.java) }
    single { RemoteMovieDataSource(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

    single<GenreApiService> { get<Retrofit>().create(GenreApiService::class.java) }
    single { RemoteGenreDataSource(get()) }
    single { GenreMapper(get()) }
    single<GenreRepository> { GenreRepositoryImpl(get()) }
}