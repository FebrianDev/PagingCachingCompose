package com.febrian.pagingcachingcompose.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.febrian.pagingcachingcompose.data.BeerApi
import com.febrian.pagingcachingcompose.data.BeerRemoteMediator
import com.febrian.pagingcachingcompose.data.database.BeerDatabase
import com.febrian.pagingcachingcompose.data.database.BeerEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): BeerApi {
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerPager(beerDb: BeerDatabase, beerApi: BeerApi): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb = beerDb,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }
}