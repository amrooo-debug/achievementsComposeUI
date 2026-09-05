package com.example.achievements14_b

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.achievements14_b.data.model.AchievementsResponseModel
import com.example.achievements14_b.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: Repository) : ViewModel() {


    //Success
    private val _achievementsSuccessLiveData : MutableLiveData<List<AchievementsResponseModel>> = MutableLiveData()
    val achievementsSuccessLiveData : LiveData<List<AchievementsResponseModel>> = _achievementsSuccessLiveData


    //Error
    private val _achievementsErrorLiveData : MutableLiveData<Exception> = MutableLiveData()
    val achievementsErrorLiveData : LiveData<Exception> = _achievementsErrorLiveData

    //Loading
    private val _achievementsLoadingLiveData : MutableLiveData<Boolean> = MutableLiveData()
    val achievementsLoadingLiveData : LiveData<Boolean> = _achievementsLoadingLiveData

    init {
        getAchievements()
    }

    private fun getAchievements() {
        viewModelScope.launch {
            _achievementsLoadingLiveData.postValue(true)
            try {
                val response = repository.getAchievements()
                _achievementsSuccessLiveData.postValue(response)
            } catch (e: Exception){
                _achievementsErrorLiveData.postValue(e)
            }

            _achievementsLoadingLiveData.postValue(false)
        }
    }
}