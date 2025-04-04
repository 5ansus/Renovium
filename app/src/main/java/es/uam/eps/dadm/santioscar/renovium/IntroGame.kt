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
    // Aquí cambia los nombres de los archivos a los identificadores de recursos en drawable
    private val avatarImages = arrayOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3)
    private val cityImages = arrayOf(R.drawable.city1, R.drawable.city2, R.drawable.city3)
    private lateinit var binding: ActivityIntroGameBinding
    private lateinit var viewModel: IntroGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_game)  // Usamos setContentView como antes
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro_game)

        // Inicializa ViewModel
        viewModel = ViewModelProvider(this).get(IntroGameViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observadores para actualizar la UI
        viewModel.avatarIndex.observe(this) { index ->
            loadDrawableImage(avatarImages[index], binding.avatarImage)
        }

        viewModel.cityIndex.observe(this) { index ->
            loadDrawableImage(cityImages[index], binding.cityImage)
        }
        viewModel.remainingCities.observe(this) { remaining ->
            binding.tvCitiesLeft.text = getString(R.string.cities_available, remaining)
        }

        viewModel.remainingAvatars.observe(this) { remaining ->
            binding.tvAvatarsLeft.text = getString(R.string.avatars_available, remaining)
        }
        initViews()


    }

    /**
     * Función auxiliar que encapsula el funcionamiento de los botones de la vista.
     *
     */
    private fun initViews(){

        binding.avatarPrevButton.setOnClickListener { changeImage(-1, true) }
        binding.avatarNextButton.setOnClickListener { changeImage(1, true) }
        binding.cityPrevButton.setOnClickListener { changeImage(-1, false) }
        binding.cityNextButton.setOnClickListener { changeImage(1, false) }

        binding.continueButton.setOnClickListener {
            // Lógica para continuar
        }
        binding.backButton.setOnClickListener {
            Timber.d("Botón Atrás pulsado - Finalizando actividad")
            finish() // Cierra la actividad actual y vuelve a la anterior
        }
    }

    /**
     * Realiza el cambio en el array de imagenes de avatar o ciudad, ademas muestra la cantidad
     * de avatares/cuidades restantes en el array de cada uno (esto mediante el uso de LiveData)
     *
     * @param direction int La dirección en el array de la siguiente imagen (1 hacia delante
     * -1 hacia atras)
     * @param isAvatar Boolean Si el array de la imagen que hay que cambiar es el avatar o la ciudad
     */
    private fun changeImage(direction: Int, isAvatar: Boolean) {
        viewModel.updateSelections(
            direction,
            isAvatar,
            if (isAvatar) avatarImages.size else cityImages.size
        )
    }


    /**
     * Realiza la carga de las imagenes nuevas mediante el uso de Drawable
     *
     * @param drawableResId Int el id de la imagen que sustituira a la otra
     * @param imageView ImageView la vista, para asi poder acceder a sus recursos
     */
    private fun loadDrawableImage(drawableResId: Int, imageView: ImageView) {
        val drawable: Drawable? = ContextCompat.getDrawable(this, drawableResId)
        imageView.setImageDrawable(drawable)
        Timber.d("Image loaded from drawable resource ID: $drawableResId")
    }


}



