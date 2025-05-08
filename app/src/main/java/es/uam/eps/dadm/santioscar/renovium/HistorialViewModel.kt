package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao
import es.uam.eps.dadm.santioscar.renovium.entities.Partida
import androidx.lifecycle.asLiveData

/**
 * ViewModel para la pantalla de historial de partidas.
 *
 * Proporciona datos sobre las partidas almacenadas y se comunica con el [PartidaDao]
 * para obtener la lista de partidas desde la base de datos.
 *
 * @property partidas LiveData que contiene la lista observable de todas las partidas
 * @param partidaDao Data Access Object para operaciones con partidas
 */
class HistorialViewModel(private val partidaDao: PartidaDao) : ViewModel() {
    val partidas: LiveData<List<Partida>> = partidaDao.getAll().asLiveData()
}