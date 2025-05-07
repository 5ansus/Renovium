package es.uam.eps.dadm.santioscar.renovium

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityIntroGameBinding
/**
 * La vista de inicialización del juego. Contiene la logica para el funcionamento de los botones
 * y el cambio de las imagenes.
 *
 */
class IntroGame : AppCompatActivity() {
    private lateinit var binding: ActivityIntroGameBinding
    private lateinit var viewModel: IntroGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro_game)


        viewModel = ViewModelProvider(this).get(IntroGameViewModel::class.java)
        binding.lifecycleOwner = this

        viewModel.isFabVisible.observe(this) { isVisible ->
            binding.fabStartGame.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
        binding.backButton.setOnClickListener {
            Timber.d("Botón Atrás pulsado")
            when(viewModel.currentScreen.value) {
                IntroGameViewModel.ScreenType.CITY -> {
                    // Volver a selección de avatar
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer,
                            SeleccionElementoFragment.newInstance(SeleccionElementoFragment.TipoSeleccion.AVATAR))
                        .commit()
                    viewModel.navigateToPreviousScreen()
                }
                IntroGameViewModel.ScreenType.START -> {
                    // Volver a selección de ciudad
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer,
                            SeleccionElementoFragment.newInstance(SeleccionElementoFragment.TipoSeleccion.CIUDAD))
                        .commit()
                    viewModel.navigateToPreviousScreen()
                }
                else -> finish() // Si está en avatar, cerrar actividad
            }
        }


        viewModel.currentScreen.observe(this) { screen ->
            binding.continueButton.text = when(screen) {
                IntroGameViewModel.ScreenType.AVATAR -> "Select Avatar"
                IntroGameViewModel.ScreenType.CITY -> "Select City"
                IntroGameViewModel.ScreenType.START -> "Start Game"
            }

            // Ocultar el botón continuar y mostrar el FAB cuando estemos en START
            if (screen == IntroGameViewModel.ScreenType.START) {
                binding.continueButton.visibility = View.GONE
                binding.fabStartGame.visibility = View.VISIBLE
            } else {
                binding.continueButton.visibility = View.VISIBLE
                binding.fabStartGame.visibility = View.GONE
            }
        }

        // Cargar primer fragmento
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SeleccionElementoFragment.newInstance(SeleccionElementoFragment.TipoSeleccion.AVATAR))
            .commit()

        binding.fabStartGame.setOnClickListener { view ->
            // Muestra Snackbar con Material Design
            Snackbar.make(
                view,  // Vista para CoordinatorLayout
                R.string.game_started,
                Snackbar.LENGTH_SHORT
            ).show()
            val intent = Intent(this, GameActivity::class.java).apply {
                putExtra("avatarId", viewModel.avatarImages[viewModel.avatarIndex.value ?: 0])
                putExtra("ciudadId", viewModel.cityImages[viewModel.cityIndex.value ?: 0])
            }
            startActivity(intent)
        }


        binding.continueButton.setOnClickListener {
            when(viewModel.currentScreen.value) {
                IntroGameViewModel.ScreenType.AVATAR -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, SeleccionElementoFragment.newInstance(SeleccionElementoFragment.TipoSeleccion.CIUDAD))
                        .commit()
                    viewModel.navigateToNextScreen()
                }
                IntroGameViewModel.ScreenType.CITY -> {
                    viewModel.navigateToNextScreen()
                    // Lógica para iniciar el juego
                }
                else -> {}
            }
        }
    }
}


