package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao

class HistorialViewModelFactory(private val partidaDao: PartidaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistorialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistorialViewModel(partidaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}