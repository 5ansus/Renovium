package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel para la pantalla de introducción del juego.
 * Gestiona el número de ciudades y avatares restantes para la selección del usuario.
 *
 * @constructor Crea una instancia de IntroGameViewModel.
 */
class IntroGameViewModel : ViewModel() {

    // LiveData para mostrar el número de ciudad
    private val _remainingCities = MutableLiveData<Int>()
    val remainingCities: LiveData<Int> = _remainingCities

    // LiveData para mostrar el número de avatar
    private val _remainingAvatars = MutableLiveData<Int>()
    val remainingAvatars: LiveData<Int> = _remainingAvatars

    /**
     * Inicializa el número de avatar y ciudad
     */
    init {
        _remainingCities.value = 3 // Ejemplo: city1, city2...
        _remainingAvatars.value = 3 // Ejemplo: avatar1, avatar2...
    }

    /**
     * Actualiza el número de avatar y ciudad
     *
     * @param citiesLeft Número de ciudades restantes después de una selección.
     * @param avatarsLeft Número de avatares restantes después de una selección.
     */
    fun updateSelections(citiesLeft: Int, avatarsLeft: Int) {
        _remainingCities.value = citiesLeft
        _remainingAvatars.value = avatarsLeft
    }
}
