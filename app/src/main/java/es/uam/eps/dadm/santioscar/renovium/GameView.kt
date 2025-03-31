package es.uam.eps.dadm.santioscar.renovium

import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableField

class GameView(private val context: Context) {

    val welcomeMessage = ObservableField(context.getString(R.string.welcome_msg))

    fun handleStartGame() {
        Toast.makeText(
            context,
            context.getString(R.string.game_start_msg), // Usa el string correspondiente al idioma

            Toast.LENGTH_SHORT
        ).show()
    }
}