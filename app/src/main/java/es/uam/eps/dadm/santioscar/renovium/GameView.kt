package es.uam.eps.dadm.santioscar.renovium

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import timber.log.Timber

class GameView(private val context: Context) {

    val welcomeMessage = ObservableField<String>(context.getString(R.string.welcome_msg))

    fun handleStartGame() {
        Timber.d("Botón Start pulsado")

        // Muestra el mensaje de inicio del juego
        Toast.makeText(
            context,
            context.getString(R.string.game_start_msg),  // Usa el string correspondiente al idioma
            Toast.LENGTH_SHORT
        ).show()

        // Inicia la actividad IntroGame
        val intent = Intent(context, IntroGame::class.java)
        context.startActivity(intent)
    }
}
