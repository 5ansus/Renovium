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

    private val _avatarIndex = MutableLiveData<Int>(0)
    private val _cityIndex = MutableLiveData<Int>(0)
    private val _remainingCities = MutableLiveData<Int>(3)
    private val _remainingAvatars = MutableLiveData<Int>(3)

    val avatarIndex: LiveData<Int> = _avatarIndex
    val cityIndex: LiveData<Int> = _cityIndex
    val remainingCities: LiveData<Int> = _remainingCities
    val remainingAvatars: LiveData<Int> = _remainingAvatars


    /**
     * Actualiza el número de avatar y ciudad y sus indices
     *
     * @param direction Int la dirección del índice (-1 a la izquierda, 1 a la derecha)
     * @param isAvatar Boolean si hay que actualizar la imagen del Avatar o de Ciudad
     * @totalItems Int Numero total de elementos en el array (es 3 pero por si luego decidimos
     * aumentarlo)
     */
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
}
