package es.uam.eps.dadm.santioscar.renovium

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityMainBinding
import timber.log.Timber

/**
 * Actividad de la vista inicial de la app
 * Inicializa la vista e inicia el LifeCicle
 *
 * @constructor Crea una instancia de MainActivity.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var gameViewModel: GameViewModel

    /**
     * Método llamado cuando la actividad se crea.
     * Configura el enlace de datos, inicializa la vista del juego y la asocia al ciclo de vida.
     *
     * @param savedInstanceState Estado previo de la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar enlace de datos con el diseño XML
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Inicializar la vista del juego y añadirlo al lifecycle
        gameViewModel = GameViewModel(this)
        binding.gameView = gameViewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(gameViewModel) // Asociar la vista del juego al ciclo de vida de la actividad
        Timber.tag("LOG").d("Aplicación iniciada")
    }
}