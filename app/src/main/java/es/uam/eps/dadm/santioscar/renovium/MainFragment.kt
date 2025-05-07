package es.uam.eps.dadm.santioscar.renovium

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.uam.eps.dadm.santioscar.renovium.databinding.FragmentMainBinding

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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToIntroGame() {

        startActivity(Intent(requireActivity(), IntroGame::class.java))
    }
}