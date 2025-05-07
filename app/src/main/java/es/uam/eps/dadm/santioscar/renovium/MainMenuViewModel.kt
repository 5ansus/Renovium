package es.uam.eps.dadm.santioscar.renovium

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import timber.log.Timber

/**
 * Vista que encapsula los datos de la vista principal (de Inicio).
 * Contiene el mensaje de bienvenida y un Toast.
 * Tambien contiene la función handleStartGame, que es la que realiza el Intent a la siguiente vista
 *
 * @param context Context La vista
 */
class MainMenuViewModel(private val context: Context) : DefaultLifecycleObserver {

    val welcomeMessage = ObservableField<String>(context.getString(R.string.welcome_msg))

    /**
     * Muestra un Toast con un mensaje de que el juego ya va a comenzar y
     * realiza el Intent hacia la siguiente vista, en este caso IntroGame.
     *
     */
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

    /**
     * Se llama cuando el ciclo de vida del propietario entra en el estado "ON_START".
     *
     * @param owner El LifecycleOwner asociado al ciclo de vida observado.
     */
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Timber.d("Llamada a GameView.onStart")
    }

    /**
     * Se llama cuando el ciclo de vida del propietario entra en el estado "ON_STOP".
     *
     * @param owner El LifecycleOwner asociado al ciclo de vida observado.
     */
    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Timber.d("Llamada a GameView.onStop")
    }

}
