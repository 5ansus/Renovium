package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.santioscar.renovium.database.AppDatabase
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao

class GameSessionViewModelFactory(private val partidaDao: PartidaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSessionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameSessionViewModel(partidaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}