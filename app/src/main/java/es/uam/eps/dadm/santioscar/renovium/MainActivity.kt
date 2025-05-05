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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Configurar Data Binding con el layout original
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 2. Inicializar ViewModel y configuración existente
        gameViewModel = GameViewModel(this)
        binding.gameView = gameViewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(gameViewModel)

        // 3. Reemplazar el contenido con el fragmento manteniendo la lógica
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance(gameViewModel))
            .addToBackStack(null)
            .commit()

        Timber.tag("LOG").d("Aplicación iniciada")
    }
}