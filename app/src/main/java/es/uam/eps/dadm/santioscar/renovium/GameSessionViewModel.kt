package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uam.eps.dadm.santioscar.renovium.entities.Partida
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao
import es.uam.eps.dadm.santioscar.renovium.database.AppDatabase
import kotlinx.coroutines.launch

/**
 * ViewModel para la sesión de juego actual.
 *
 * Gestiona la lógica del juego y la comunicación con la base de datos:
 * - Mantiene y actualiza la puntuación actual
 * - Guarda los resultados de la partida al finalizar
 * - Proporciona datos observables a la UI
 *
 * @property puntuacion Puntuación actual observable
 * @property partidaGuardada Estado observable del guardado de partida
 * @param partidaDao Data Access Object para operaciones con partidas
 */
class GameSessionViewModel(private val partidaDao: PartidaDao) : ViewModel() {
    private val _puntuacion = MutableLiveData(0)
    val puntuacion: LiveData<Int> = _puntuacion
    private val _partidaGuardada = MutableLiveData<Boolean>()
    val partidaGuardada: LiveData<Boolean> = _partidaGuardada

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
            _partidaGuardada.postValue(true) // Notificar que se guardó
        }
    }
}