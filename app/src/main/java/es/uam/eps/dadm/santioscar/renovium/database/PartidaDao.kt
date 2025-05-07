package es.uam.eps.dadm.santioscar.renovium.database

import androidx.room.*
import es.uam.eps.dadm.santioscar.renovium.entities.Partida
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidaDao {
    @Insert
    suspend fun insert(partida: Partida) {
        println("Insertando partida: $partida") // Log de inserción
        _insert(partida)
    }

    @Insert
    suspend fun _insert(partida: Partida):Long

    @Query("SELECT * FROM partidas ORDER BY puntos DESC")
    fun getAll(): Flow<List<Partida>>

    @Query("SELECT * FROM partidas WHERE id = :id")
    suspend fun getById(id: Int): Partida?
}