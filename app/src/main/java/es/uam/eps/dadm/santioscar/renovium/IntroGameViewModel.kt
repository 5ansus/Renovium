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
    // Arrays de recursos
    val avatarImages = arrayOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3)
    val cityImages = arrayOf(R.drawable.city1, R.drawable.city2, R.drawable.city3)
    enum class ScreenType { AVATAR, CITY, START }
    private val _currentScreen = MutableLiveData(ScreenType.AVATAR)
    val currentScreen: LiveData<ScreenType> = _currentScreen

    // LiveData para seguimiento
    private val _avatarIndex = MutableLiveData<Int>(0)
    private val _cityIndex = MutableLiveData<Int>(0)
    private val _remainingCities = MutableLiveData<Int>(cityImages.size)
    private val _remainingAvatars = MutableLiveData<Int>(avatarImages.size)

    // Exponer LiveData
    val avatarIndex: LiveData<Int> = _avatarIndex
    val cityIndex: LiveData<Int> = _cityIndex
    val remainingCities: LiveData<Int> = _remainingCities
    val remainingAvatars: LiveData<Int> = _remainingAvatars

    fun updateSelections(direction: Int, isAvatar: Boolean, totalItems: Int) {
        if (isAvatar) {
            val newIndex = ((_avatarIndex.value ?: 0) + direction + totalItems) % totalItems
            _avatarIndex.value = newIndex
            _remainingAvatars.value = totalItems - (newIndex + 1)
        } else {
            val newIndex = ((_cityIndex.value ?: 0) + direction + totalItems) % totalItems
            _cityIndex.value = newIndex
            _remainingCities.value = totalItems - (newIndex + 1)
        }
    }

    fun navigateToNextScreen() {
        _currentScreen.value = when(_currentScreen.value) {
            ScreenType.AVATAR -> ScreenType.CITY
            ScreenType.CITY -> ScreenType.START
            ScreenType.START -> ScreenType.START
            null -> ScreenType.AVATAR
        }
    }

    fun navigateToPreviousScreen() {
        _currentScreen.value = when(_currentScreen.value) {
            ScreenType.CITY -> ScreenType.AVATAR
            ScreenType.START -> ScreenType.CITY
            else -> ScreenType.AVATAR
        }
    }
}