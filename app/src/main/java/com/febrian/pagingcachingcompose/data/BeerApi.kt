package com.febrian.pagingcachingcompose.data

import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<BeerResponse>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}