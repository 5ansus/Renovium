package es.uam.eps.dadm.santioscar.renovium

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.santioscar.renovium.database.AppDatabase
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityGameBinding

/**
 * Actividad principal del juego donde se desarrolla la partida.
 *
 * Maneja:
 * - Recepción de los parámetros de inicio (avatar y ciudad seleccionados)
 * - Configuración del ViewModel con acceso a la base de datos
 * - Visualización de los elementos del juego
 * - Interacción del usuario y actualización de puntuación
 * - Persistencia de los resultados al finalizar
 *
 * @property avatarId Resource ID del avatar seleccionado
 * @property ciudadId Resource ID de la ciudad seleccionada
 */
class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var viewModel: GameSessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        // Recibir selecciones de avatar y ciudad
        val avatarId = intent.getIntExtra("avatarId", R.drawable.avatar1)
        val ciudadId = intent.getIntExtra("ciudadId", R.drawable.city1)

        val partidaDao = AppDatabase.getDatabase(this).partidaDao()
        viewModel = ViewModelProvider(
            this,
            GameSessionViewModelFactory(partidaDao)
        ).get(GameSessionViewModel::class.java)
        binding.viewModel = viewModel
        binding.avatarId = avatarId
        binding.ciudadId = ciudadId
        binding.lifecycleOwner = this

        // Configurar vista con las selecciones
        binding.avatarImage.setImageResource(avatarId)
        binding.ciudadBackground.setImageResource(ciudadId)


        viewModel.partidaGuardada.observe(this) { guardado ->
            if (guardado) {
                Toast.makeText(
                    this,
                    "Partida guardada correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }}
        // Interaccion simple
        binding.botonAccion.setOnClickListener {
            viewModel.aumentarPuntuacion()
        }
    }
}