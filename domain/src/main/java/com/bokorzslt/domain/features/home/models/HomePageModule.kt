package com.bokorzslt.domain.features.home.models

import com.bokorzslt.domain.features.details.models.MovieDetails

sealed class HomePageModule {
    data class GalleryModule(val movie: MovieDetails) : HomePageModule()
    data class StripeModule(val stripe: Stripe) : HomePageModule()
}