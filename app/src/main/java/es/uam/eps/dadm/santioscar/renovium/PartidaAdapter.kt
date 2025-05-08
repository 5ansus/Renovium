package es.uam.eps.dadm.santioscar.renovium

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.uam.eps.dadm.santioscar.renovium.databinding.ItemPartidaBinding

import es.uam.eps.dadm.santioscar.renovium.entities.Partida

/**
 * Adaptador para mostrar la lista de partidas en un RecyclerView.
 *
 * Utiliza [ListAdapter] con [PartidaDiffCallback] para actualizaciones eficientes
 * cuando cambian los datos. Muestra cada partida usando [ItemPartidaBinding].
 */
class PartidaAdapter : ListAdapter<Partida, PartidaAdapter.PartidaViewHolder>(PartidaDiffCallback()) {

    /**
     * Crea nuevos ViewHolders cuando el RecyclerView los necesita.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidaViewHolder {
        val binding = ItemPartidaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PartidaViewHolder(binding)
    }

    /**
     * Vincula los datos de una partida específica con las vistas del ViewHolder.
     */
    override fun onBindViewHolder(holder: PartidaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    /**
     * ViewHolder que representa un ítem individual de partida en la lista.
     *
     * @property binding El binding que contiene las vistas para mostrar los datos de la partida
     */
    class PartidaViewHolder(private val binding: ItemPartidaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Vincula los datos de la partida con las vistas usando Data Binding.
         *
         * @param partida La partida cuyos datos se deben mostrar
         */
        fun bind(partida: Partida) {
            binding.partida = partida
            binding.executePendingBindings()
        }
    }

    /**
     * Callback para calcular diferencias entre listas de partidas y actualizar eficientemente.
     */
    class PartidaDiffCallback : DiffUtil.ItemCallback<Partida>() {

        /**
         * Determina si dos ítems representan la misma partida (por ID).
         */
        override fun areItemsTheSame(oldItem: Partida, newItem: Partida): Boolean {
            return oldItem.id == newItem.id
        }

        /**
         * Determina si los contenidos de dos partidas son iguales.
         */
        override fun areContentsTheSame(oldItem: Partida, newItem: Partida): Boolean {
            return oldItem == newItem
        }
    }
}