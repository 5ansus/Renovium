package es.uam.eps.dadm.santioscar.renovium

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.uam.eps.dadm.santioscar.renovium.database.AppDatabase
import es.uam.eps.dadm.santioscar.renovium.databinding.ActivityHistorialBinding


class HistorialActivity : AppCompatActivity() {
    private lateinit var binding:   ActivityHistorialBinding
    private lateinit var viewModel: HistorialViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val partidaDao = AppDatabase.getDatabase(this).partidaDao()
        viewModel = ViewModelProvider(this, HistorialViewModelFactory(partidaDao))
            .get(HistorialViewModel::class.java)

        setupRecyclerView()

        viewModel.partidas.observe(this) { partidas ->
            (binding.recyclerView.adapter as PartidaAdapter).submitList(partidas)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = PartidaAdapter()
    }
}