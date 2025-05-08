package es.uam.eps.dadm.santioscar.renovium.database

import androidx.room.*
import es.uam.eps.dadm.santioscar.renovium.entities.Partida
import kotlinx.coroutines.flow.Flow

// DAO que define operaciones sobre la entidad Partida
@Dao
interface PartidaDao {
    // Inserta una partida en la base de datos y muestra un mensaje
    @Insert
    suspend fun insert(partida: Partida) {
        println("Insertando partida: $partida") // Log de inserción
        _insert(partida)
    }

    // Método interno de inserción que devuelve el ID generado.
    @Insert
    suspend fun _insert(partida: Partida):Long

    // Recupera todas las partidas ordenadas por puntuación descendente en tiempo real (Flow).
    @Query("SELECT * FROM partidas ORDER BY puntos DESC")
    fun getAll(): Flow<List<Partida>>

    // Busca una partida por su ID.
    @Query("SELECT * FROM partidas WHERE id = :id")
    suspend fun getById(id: Int): Partida?
}