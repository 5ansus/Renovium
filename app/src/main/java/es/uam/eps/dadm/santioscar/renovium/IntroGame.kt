package es.uam.eps.dadm.santioscar.renovium

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

        // Observar cambios en currentScreen para actualizar el botón
        viewModel.currentScreen.observe(this) { screen ->
            binding.continueButton.text = when(screen) {
                IntroGameViewModel.ScreenType.AVATAR -> "Select Avatar"
                IntroGameViewModel.ScreenType.CITY -> "Select City"
                IntroGameViewModel.ScreenType.START -> "Start Game"
            }
        }

        // Cargar primer fragmento
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SeleccionElementoFragment.newInstance(SeleccionElementoFragment.TipoSeleccion.AVATAR))
            .commit()

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


