package es.uam.eps.dadm.santioscar.renovium

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityMainBinding
import timber.log.Timber
import es.uam.eps.dadm.santioscar.renovium.IntroGame

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        gameView = GameView(this)
        binding.gameView = gameView
        binding.lifecycleOwner = this
        lifecycle.addObserver(gameView)
        Timber.tag("LOG").d("Aplicación iniciada")
        binding.startButton.setOnClickListener {
            gameView.handleStartGame()
        }
    }
}