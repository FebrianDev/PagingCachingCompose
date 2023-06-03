package com.febrian.pagingcachingcompose.data

data class BeerResponse(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String?
)