package com.example.caloriecounter.di

import android.content.Context
import androidx.room.Room
import com.example.caloriecounter.db.FoodDatabase
import com.example.caloriecounter.repository.FoodRepository
import com.example.caloriecounter.repository.api.FoodService
import com.example.caloriecounter.repository.db.FoodDao
import com.example.caloriecounter.repository.local.FoodRepositoryImpl
import com.example.caloriecounter.utils.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun providesApiService(
        retrofit: Retrofit
    ): FoodService = retrofit.create(FoodService::class.java)

    @Provides
    @Singleton
    fun retrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiUtils.BASE_API)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesRemoteFoodRepo(
        foodService: FoodService,
        localRepo: FoodRepositoryImpl
    ): FoodRepository =
        com.example.caloriecounter.repository.remote.FoodRepositoryImpl(foodService, localRepo)

    @Provides
    @Singleton
    fun providesLocalFoodDb(
        @ApplicationContext context: Context
    ): FoodDatabase =
        Room.databaseBuilder(
            context,
            FoodDatabase::class.java,
            "Food_DB"
        ).build()

    @Provides
    @Singleton
    fun providesFoodDao(
        database: FoodDatabase
    ): FoodDao = database.foodDao()

    @Provides
    @Singleton
    fun providesLocalFoodRepo(
        foodDao: FoodDao
    ): com.example.caloriecounter.repository.local.FoodRepositoryImpl =
        com.example.caloriecounter.repository.local.FoodRepositoryImpl(foodDao)
}