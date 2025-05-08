package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao

/**
 * Factory para crear instancias de [HistorialViewModel] con las dependencias necesarias.
 *
 * Implementa [ViewModelProvider.Factory] para permitir la creación de ViewModels
 * con parámetros en el constructor (en este caso, un [PartidaDao] para acceso a la base de datos).
 *
 * @param partidaDao El Data Access Object para operaciones con partidas en la base de datos
 */
class HistorialViewModelFactory(private val partidaDao: PartidaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistorialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistorialViewModel(partidaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}