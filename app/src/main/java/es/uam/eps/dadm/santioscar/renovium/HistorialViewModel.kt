package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao
import es.uam.eps.dadm.santioscar.renovium.entities.Partida
import androidx.lifecycle.asLiveData

class HistorialViewModel(private val partidaDao: PartidaDao) : ViewModel() {
    val partidas: LiveData<List<Partida>> = partidaDao.getAll().asLiveData()
}