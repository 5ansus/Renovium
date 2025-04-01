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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.gameView = GameView(this) // El contexto es una instancia de Activity
        binding.lifecycleOwner = this
        Timber.tag("LOG").d("Aplicación iniciada")

        binding.startButton.setOnClickListener {
            val intent = Intent(this, IntroGame::class.java)
            startActivity(intent)
        }
    }
}