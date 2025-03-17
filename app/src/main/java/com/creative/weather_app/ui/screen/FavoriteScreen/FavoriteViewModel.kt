package com.creative.weather_app.ui.screen.FavoriteScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.weather_app.model.Favorite
import com.creative.weather_app.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository): ViewModel() {
    val _favList = MutableStateFlow<List<Favorite>>(
        emptyList())
    val favList = _favList.asStateFlow()
    init {
        viewModelScope.launch {
            repository.getFavorites().distinctUntilChanged().collect{ favList->
                if(listOf(favList).isEmpty())
                {
                    Log.d("mondher","CurrentList is empty")
                }

                else _favList.value = favList
                Log.d("mondher","List of Fav is ${favList}")
            }
        }
    }

    //ViewModel scope no need to call suspend function
    fun insertFavorite(favorite: Favorite) = viewModelScope.launch { repository.insertFavorite(favorite) }
    fun updateFavorite(favorite: Favorite) = viewModelScope.launch { repository.updateFavorite(favorite) }
    fun getFavById(city:String) = viewModelScope.launch { repository.getFavById(city) }
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch { repository.deleteFavorite(favorite) }

}