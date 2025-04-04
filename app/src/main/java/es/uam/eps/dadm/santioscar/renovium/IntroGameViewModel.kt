package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroGameViewModel : ViewModel() {
    private val _remainingCities = MutableLiveData<Int>()
    val remainingCities: LiveData<Int> = _remainingCities

    private val _remainingAvatars = MutableLiveData<Int>()
    val remainingAvatars: LiveData<Int> = _remainingAvatars

    init {
        // Inicializa con el total de imágenes
        _remainingCities.value = 3 // city1, city2...
        _remainingAvatars.value = 3 // avatar1, avatar2...
    }

    fun updateSelections(citiesLeft: Int, avatarsLeft: Int) {
        _remainingCities.value = citiesLeft
        _remainingAvatars.value = avatarsLeft
    }
}