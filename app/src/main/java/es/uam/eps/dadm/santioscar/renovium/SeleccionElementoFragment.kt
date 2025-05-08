package es.uam.eps.dadm.santioscar.renovium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import es.uam.eps.dadm.santioscar.renovium.databinding.FragmentSeleccionElementoBinding

/**
 * Fragmento reutilizable para seleccionar avatares o ciudades al inicio del juego.
 *
 * Puede configurarse en dos modos mediante [TipoSeleccion]:
 * - AVATAR: Para selección de personaje
 * - CIUDAD: Para selección de escenario
 *
 * Utiliza [IntroGameViewModel] para mantener el estado de las selecciones.
 */
class SeleccionElementoFragment : Fragment() {
    private var _binding: FragmentSeleccionElementoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: IntroGameViewModel

    enum class TipoSeleccion { AVATAR, CIUDAD }

    companion object {
        private const val ARG_TIPO_SELECCION = "tipo_seleccion"

        /**
         * Crea una nueva instancia del fragmento configurada para un tipo de selección específico.
         *
         * @param tipo El tipo de elementos a seleccionar (AVATAR o CIUDAD)
         * @return Nueva instancia del fragmento configurada
         */
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

        when (tipoSeleccion) {
            TipoSeleccion.AVATAR -> {
                configurarSeleccionAvatar()
                setupRecyclerView(viewModel.avatarImages.toList()) { pos ->
                    viewModel.setAvatarIndex(pos)
                }
            }
            TipoSeleccion.CIUDAD -> {
                configurarSeleccionCiudad()
                setupRecyclerView(viewModel.cityImages.toList()) { pos ->
                    viewModel.setCityIndex(pos)
                }
            }
        }
    }


    /**
     * Configura el fragmento para modo selección de avatar.
     * Actualiza la UI y configura listeners específicos para avatares.
     */
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

    /**
     * Configura el fragmento para modo selección de ciudad.
     * Actualiza la UI y configura listeners específicos para ciudades.
     */
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
    /**
     * Configura el RecyclerView para mostrar las opciones de selección.
     *
     * @param images Lista de recursos drawable a mostrar
     * @param onItemClick Callback para manejar la selección de un ítem
     */
    private fun setupRecyclerView(images: List<Int>, onItemClick: (Int) -> Unit) {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3) // 3 columnas
        binding.recyclerView.adapter = SeleccionAdapter(images) { position ->
            onItemClick(position)
            // Actualiza la imagen principal al seleccionar
            binding.elementoImage.setImageResource(images[position])
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}