package es.uam.eps.dadm.santioscar.renovium

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import timber.log.Timber

class GameView(private val context: Context) : DefaultLifecycleObserver {

    val welcomeMessage = ObservableField<String>(context.getString(R.string.welcome_msg))

    fun handleStartGame() {
        Timber.d("Botón Start pulsado")

        Toast.makeText(
            context,
            context.getString(R.string.game_start_msg),
            Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(context, IntroGame::class.java)
        context.startActivity(intent)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Timber.d("Llamada a GameView.onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Timber.d("Llamada a GameView.onStop")
    }
}

