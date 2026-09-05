package com.example.achievements14_b.data.repository

import com.example.achievements14_b.data.api.ProjectApi
import com.example.achievements14_b.data.model.AchievementsResponseModel

class Repository(val projectApi: ProjectApi) {

    suspend fun getAchievements(): List<AchievementsResponseModel> {
        return projectApi.getAchievements().data
    }
}