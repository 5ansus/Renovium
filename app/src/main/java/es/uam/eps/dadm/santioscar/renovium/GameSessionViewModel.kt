package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uam.eps.dadm.santioscar.renovium.entities.Partida
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao
import es.uam.eps.dadm.santioscar.renovium.database.AppDatabase
import kotlinx.coroutines.launch

class GameSessionViewModel(private val partidaDao: PartidaDao) : ViewModel() {
    private val _puntuacion = MutableLiveData(0)
    val puntuacion: LiveData<Int> = _puntuacion

    fun aumentarPuntuacion() {
        _puntuacion.value = (_puntuacion.value ?: 0) + 1
    }

    fun guardarPartida(avatarId: Int, ciudadId: Int) {
        println("Guardando partida - AvatarID: $avatarId, CiudadID: $ciudadId")
        viewModelScope.launch {
            partidaDao.insert(
                Partida(
                    puntos = _puntuacion.value ?: 0,
                    avatarId = avatarId,
                    ciudadId = ciudadId
                )
            )
        }
    }
}