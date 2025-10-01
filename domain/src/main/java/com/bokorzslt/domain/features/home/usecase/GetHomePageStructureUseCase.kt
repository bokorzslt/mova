package com.bokorzslt.domain.features.home.usecase

import com.bokorzslt.domain.features.home.models.HomePageModule
import com.bokorzslt.domain.features.home.models.Stripe
import com.bokorzslt.domain.features.home.repository.MovieRepository

class GetHomePageStructureUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(): List<HomePageModule> =
        mutableListOf<HomePageModule>().apply {

            val topRatedMovies = movieRepository.getMoviesByCategory(TOP_RATED_CATEGORY)

            val randomMovieId = topRatedMovies.shuffled().first().id
            add(HomePageModule.GalleryModule(movieRepository.getMovieDetails(randomMovieId)))

            add(
                HomePageModule.StripeModule(
                    Stripe(
                        title = TOP_RATED_STRIPE_TITLE,
                        movies = topRatedMovies
                    )
                )
            )

            add(
                HomePageModule.StripeModule(
                    Stripe(
                        title = POPULAR_STRIPE_TITLE,
                        movies = movieRepository.getMoviesByCategory(POPULAR_CATEGORY)
                    )
                )
            )

            add(
                HomePageModule.StripeModule(
                    Stripe(
                        title = NOW_PLAYING_STRIPE_TITLE,
                        movies = movieRepository.getMoviesByCategory(NOW_PLAYING_CATEGORY)
                            .shuffled()
                    )
                )
            )

            add(
                HomePageModule.StripeModule(
                    Stripe(
                        title = UPCOMING_STRIPE_TITLE,
                        movies = movieRepository.getMoviesByCategory(UPCOMING_CATEGORY)
                    )
                )
            )
        }

    companion object {
        private const val TOP_RATED_STRIPE_TITLE = "Top Rated Movies"
        private const val POPULAR_STRIPE_TITLE = "Popular Movies"
        private const val NOW_PLAYING_STRIPE_TITLE = "Now Playing Movies"
        private const val UPCOMING_STRIPE_TITLE = "Upcoming Movies"
        private const val TOP_RATED_CATEGORY = "top_rated"
        private const val POPULAR_CATEGORY = "popular"
        private const val NOW_PLAYING_CATEGORY = "now_playing"
        private const val UPCOMING_CATEGORY = "upcoming"
    }
}