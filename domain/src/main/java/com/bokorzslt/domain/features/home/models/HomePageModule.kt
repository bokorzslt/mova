package com.bokorzslt.domain.features.home.models

sealed class HomePageModule {
    data class GalleryModule(val movie: Movie) : HomePageModule()
    data class StripeModule(val stripe: Stripe) : HomePageModule()
}