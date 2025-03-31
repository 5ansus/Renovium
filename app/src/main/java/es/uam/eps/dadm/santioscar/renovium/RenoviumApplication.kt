package es.uam.eps.dadm.santioscar.renovium
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityMainBinding
import timber.log.Timber

class RenoviumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("App iniciada")
    }
}