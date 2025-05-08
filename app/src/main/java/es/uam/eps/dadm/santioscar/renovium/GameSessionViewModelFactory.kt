package es.uam.eps.dadm.santioscar.renovium

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.santioscar.renovium.database.AppDatabase
import es.uam.eps.dadm.santioscar.renovium.database.PartidaDao

/**
 * Factory para crear instancias de [GameSessionViewModel] con un [PartidaDao] dado.
 *
 * Esta factory permite la inyección de dependencias en el ViewModel, específicamente
 * el DAO necesario para interactuar con la base de datos.
 *
 * @param partidaDao El Data Access Object para operaciones con partidas
 */
class GameSessionViewModelFactory(private val partidaDao: PartidaDao) : ViewModelProvider.Factory {
    /**
     * Crea una nueva instancia del ViewModel solicitado.
     *
     * @param modelClass La clase del ViewModel a instanciar
     * @return Una nueva instancia del ViewModel con el DAO proporcionado
     * @throws IllegalArgumentException si se solicita un ViewModel de clase desconocida
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameSessionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameSessionViewModel(partidaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}