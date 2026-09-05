package com.example.achievements14_b.data.api

import com.example.achievements14_b.data.model.WrappedAchievementsResponseModel
import retrofit2.http.GET

interface ProjectApi {
    @GET("/achievements")
    suspend fun getAchievements(): WrappedAchievementsResponseModel


}