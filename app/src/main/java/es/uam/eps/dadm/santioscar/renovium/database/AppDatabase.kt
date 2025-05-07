package es.uam.eps.dadm.santioscar.renovium.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import es.uam.eps.dadm.santioscar.renovium.entities.Partida

@Database(entities = [Partida::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun partidaDao(): PartidaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "renovium-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}