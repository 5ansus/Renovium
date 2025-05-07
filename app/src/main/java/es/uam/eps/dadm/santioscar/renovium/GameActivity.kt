package es.uam.eps.dadm.santioscar.renovium

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var viewModel: GameSessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        // Recibir selecciones de avatar y ciudad
        val avatarId = intent.getIntExtra("avatarId", R.drawable.avatar1)
        val ciudadId = intent.getIntExtra("ciudadId", R.drawable.city1)

        viewModel = ViewModelProvider(this).get(GameSessionViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Configurar vista con las selecciones
        binding.avatarImage.setImageResource(avatarId)
        binding.ciudadBackground.setImageResource(ciudadId)



        // Interaccion simple
        binding.botonAccion.setOnClickListener {
            viewModel.aumentarPuntuacion()
        }
    }
}