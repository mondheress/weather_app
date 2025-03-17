package com.creative.weather_app.ui.screen.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.weather_app.model.Setting_Unit
import com.creative.weather_app.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherDbRepository): ViewModel() {
    val _settingList = MutableStateFlow<List<Setting_Unit>>(
        emptyList())
    val settingList = _settingList.asStateFlow()
    init {
        viewModelScope.launch {
            repository.getUnit().distinctUntilChanged().collect{ favList->
                if(listOf(favList).isEmpty())
                {
                    Log.d("mondher","CurrentList is empty")
                }

                else _settingList.value = favList
                Log.d("mondher","List of Fav is ${favList}")
            }
        }
    }

    //ViewModel scope no need to call suspend function
    fun insertUnit(favorite: Setting_Unit) = viewModelScope.launch { repository.insertUnit(favorite) }
    fun updateUnit(favorite: Setting_Unit) = viewModelScope.launch { repository.updateUnit(favorite) }
    fun deleteUnit() = viewModelScope.launch { repository.deleteAllUnits() }

}