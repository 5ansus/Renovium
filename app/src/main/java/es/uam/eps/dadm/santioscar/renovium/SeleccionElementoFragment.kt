package es.uam.eps.dadm.santioscar.renovium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import es.uam.eps.dadm.santioscar.renovium.databinding.FragmentSeleccionElementoBinding

class SeleccionElementoFragment : Fragment() {
    private var _binding: FragmentSeleccionElementoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: IntroGameViewModel

    enum class TipoSeleccion { AVATAR, CIUDAD }

    companion object {
        private const val ARG_TIPO_SELECCION = "tipo_seleccion"

        fun newInstance(tipo: TipoSeleccion): SeleccionElementoFragment {
            return SeleccionElementoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_TIPO_SELECCION, tipo)
                }
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_seleccion_elemento, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(IntroGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val tipoSeleccion = arguments?.getSerializable(ARG_TIPO_SELECCION) as TipoSeleccion

        when(tipoSeleccion) {
            TipoSeleccion.AVATAR -> configurarSeleccionAvatar()
            TipoSeleccion.CIUDAD -> configurarSeleccionCiudad()

        }

    }

    private fun configurarSeleccionAvatar() {
        binding.selectElementoLabel.text = getString(R.string.intro_avatar)

        viewModel.avatarIndex.observe(viewLifecycleOwner) { index ->
            val drawable = ContextCompat.getDrawable(requireContext(), viewModel.avatarImages[index])
            binding.elementoImage.setImageDrawable(drawable)
        }

        viewModel.remainingAvatars.observe(viewLifecycleOwner) { remaining ->
            binding.tvElementosLeft.text = getString(R.string.avatars_available, remaining)
        }

        binding.elementoPrevButton.setOnClickListener {
            viewModel.updateSelections(-1, true, viewModel.avatarImages.size)
        }

        binding.elementoNextButton.setOnClickListener {
            viewModel.updateSelections(1, true, viewModel.avatarImages.size)
        }
    }

    private fun configurarSeleccionCiudad() {
        binding.selectElementoLabel.text = getString(R.string.intro_city)

        viewModel.cityIndex.observe(viewLifecycleOwner) { index ->
            val drawable = ContextCompat.getDrawable(requireContext(), viewModel.cityImages[index])
            binding.elementoImage.setImageDrawable(drawable)
        }

        viewModel.remainingCities.observe(viewLifecycleOwner) { remaining ->
            binding.tvElementosLeft.text = getString(R.string.cities_available, remaining)
        }

        binding.elementoPrevButton.setOnClickListener {
            viewModel.updateSelections(-1, false, viewModel.cityImages.size)
        }

        binding.elementoNextButton.setOnClickListener {
            viewModel.updateSelections(1, false, viewModel.cityImages.size)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}