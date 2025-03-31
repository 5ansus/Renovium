package es.uam.eps.dadm.santioscar.renovium

import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableField
import timber.log.Timber

class GameView(private val context: Context) {

    val welcomeMessage = ObservableField(context.getString(R.string.welcome_msg))

    fun handleStartGame() {
        Timber.d("Botón Start pulsado")
        Toast.makeText(
            context,
            context.getString(R.string.game_start_msg), // Usa el string correspondiente al idioma

            Toast.LENGTH_SHORT
        ).show()
    }
}