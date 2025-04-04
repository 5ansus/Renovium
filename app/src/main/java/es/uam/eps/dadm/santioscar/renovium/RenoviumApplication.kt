package es.uam.eps.dadm.santioscar.renovium
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityMainBinding
import timber.log.Timber

/**
 * Clase de aplicación que sirve para inicializar ciertas utilidades que se usarán como Timber
 *
 * @constructor Crea una instancia de la aplicación.
 */
class RenoviumApplication : Application() {

    /**
     * Método llamado cuando la aplicación se inicia.
     * Inicializa Timber al crear la instancia de la app
     */
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree()) // Inicializar Timber
        Timber.d("App iniciada")
    }
}