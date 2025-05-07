package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameSessionViewModel : ViewModel() {
    private val _puntuacion = MutableLiveData(0)
    val puntuacion: LiveData<Int> = _puntuacion


    fun aumentarPuntuacion() {
        _puntuacion.value = (_puntuacion.value ?: 0) + 1
    }



}