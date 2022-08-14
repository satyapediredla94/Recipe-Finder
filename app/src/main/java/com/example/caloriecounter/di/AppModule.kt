package com.example.caloriecounter.di

import com.example.caloriecounter.repository.FoodRepository
import com.example.caloriecounter.repository.api.FoodService
import com.example.caloriecounter.repository.remote.FoodRepositoryImpl
import com.example.caloriecounter.utils.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiService(
        retrofit: Retrofit
    ): FoodService = retrofit.create(FoodService::class.java)

    @Provides
    @Singleton
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(ApiUtils.BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesFoodRepo(
        foodService: FoodService
    ) : FoodRepository = FoodRepositoryImpl(foodService)
}