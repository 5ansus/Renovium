package es.uam.eps.dadm.santioscar.renovium.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "partidas")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val puntos: Int,
    val avatarId: Int,
    val ciudadId: Int
)