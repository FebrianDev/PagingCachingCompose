package com.febrian.pagingcachingcompose.data.mapping

import com.febrian.pagingcachingcompose.data.BeerResponse
import com.febrian.pagingcachingcompose.data.database.BeerEntity
import com.febrian.pagingcachingcompose.domain.Beer

fun BeerResponse.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}