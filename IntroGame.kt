package es.uam.eps.dadm.santioscar.renovium

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import es.uam.eps.dadm.santioscar.renovium.R
import timber.log.Timber

/**
 * La vista de inicialización del juego. Contiene la logica para el funcionamento de los botones
 * y el cambio de las imagenes.
 *
 */
class IntroGame : AppCompatActivity() {
    // Aquí cambia los nombres de los archivos a los identificadores de recursos en drawable
    private val avatarImages = arrayOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3)
    private val cityImages = arrayOf(R.drawable.city1, R.drawable.city2, R.drawable.city3)
    private var avatarIndex = 0
    private var cityIndex = 0
    private lateinit var viewModel: IntroGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_game)  // Usamos setContentView como antes

        // Inicializa ViewModel
        viewModel = ViewModelProvider(this).get(IntroGameViewModel::class.java)

        // Restaurar estado si existe
        savedInstanceState?.let {
            avatarIndex = it.getInt("AVATAR_INDEX", 0)
            cityIndex = it.getInt("CITY_INDEX", 0)
        }

        // Observa los LiveData
        viewModel.remainingCities.observe(this) { remaining ->
            findViewById<TextView>(R.id.tvCitiesLeft).text =
                getString(R.string.cities_available, remaining)
        }

        viewModel.remainingAvatars.observe(this) { remaining ->
            findViewById<TextView>(R.id.tvAvatarsLeft).text =
                getString(R.string.avatars_available, remaining)
        }
        initViews()

        updateImages()

    }

    /**
     * Función auxiliar que encapsula el funcionamiento de los botones de la vista.
     *
     */
    private fun initViews(){

        // Encontramos las vistas de manera tradicional usando findViewById
        val avatarImage: ImageView = findViewById(R.id.avatarImage)
        val cityImage: ImageView = findViewById(R.id.cityImage)
        val avatarPrevButton: Button = findViewById(R.id.avatarPrevButton)
        val avatarNextButton: Button = findViewById(R.id.avatarNextButton)
        val cityPrevButton: Button = findViewById(R.id.cityPrevButton)
        val cityNextButton: Button = findViewById(R.id.cityNextButton)
        val continueButton: Button = findViewById(R.id.continueButton)
        val backButton: ImageButton = findViewById(R.id.backButton)

        avatarPrevButton.setOnClickListener { changeImage(-1, true) }
        avatarNextButton.setOnClickListener { changeImage(1, true) }
        cityPrevButton.setOnClickListener { changeImage(-1, false) }
        cityNextButton.setOnClickListener { changeImage(1, false) }
        continueButton.setOnClickListener {
            // A añadir en las próximas vistas
        }
        backButton.setOnClickListener {
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
        if (isAvatar) {
            avatarIndex = (avatarIndex + direction + avatarImages.size) % avatarImages.size
            viewModel.updateSelections(
                viewModel.remainingCities.value ?: 3,
                avatarImages.size - (avatarIndex + 1))

            Timber.d("Avatar index changed to: $avatarIndex")
        } else {
            cityIndex = (cityIndex + direction + cityImages.size) % cityImages.size
            viewModel.updateSelections(
                cityImages.size - (cityIndex + 1),
                viewModel.remainingAvatars.value ?: 3)

            Timber.d("City index changed to: $cityIndex")
        }
        updateImages()
    }

    /**
     * Realiza el cambio de imagenes de la vista, utiliza drawable para realizarlo
     *
     */
    private fun updateImages() {
        val avatarImage: ImageView = findViewById(R.id.avatarImage)
        val cityImage: ImageView = findViewById(R.id.cityImage)

        Timber.d("Loading avatar image: ${avatarImages[avatarIndex]}")
        Timber.d("Loading city image: ${cityImages[cityIndex]}")

        // Cambiamos para cargar las imágenes desde los recursos drawable
        loadDrawableImage(avatarImages[avatarIndex], avatarImage)
        loadDrawableImage(cityImages[cityIndex], cityImage)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("AVATAR_INDEX", avatarIndex)
        outState.putInt("CITY_INDEX", cityIndex)
        Timber.d("Estado guardado: avatar=$avatarIndex, city=$cityIndex")
    }
}




