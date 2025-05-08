package es.uam.eps.dadm.santioscar.renovium

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.uam.eps.dadm.santioscar.renovium.databinding.FragmentMainBinding

/**
 * Fragmento principal que actúa como punto de entrada de la aplicación.
 *
 * Muestra el menú principal con opciones para:
 * - Iniciar sesión (loginButton)
 * - Ver historial de partidas (replaysButton)
 * - Comenzar nueva partida (startButton)
 *
 * Utiliza [MainMenuViewModel] para manejar la lógica de presentación.
 */
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(viewModel: MainMenuViewModel): MainFragment {
            return MainFragment().apply {
                this.viewModel = viewModel
            }
        }
    }

    private lateinit var viewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Configura los listeners de los botones y vincula el ViewModel cuando la vista está creada.
     *
     * @param view La vista raíz del fragmento
     * @param savedInstanceState Estado previo de la instancia, si existe
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Configurar listeners para los botones adicionales
        binding.loginButton.setOnClickListener {
            // Lógica para login
            Toast.makeText(context, "Login clicked", Toast.LENGTH_SHORT).show()
        }

        binding.replaysButton.setOnClickListener {
            // Lógica para replays
            Toast.makeText(context, "Replays clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), HistorialActivity::class.java))

        }


        binding.startButton.setOnClickListener {
            navigateToIntroGame()
        }
    }

    /**
     * Para destruir
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Navegacion a la pantalla de IntroGame
     */
    private fun navigateToIntroGame() {

        startActivity(Intent(requireActivity(), IntroGame::class.java))
    }
}