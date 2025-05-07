package es.uam.eps.dadm.santioscar.renovium

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.uam.eps.dadm.santioscar.renovium.databinding.ItemPartidaBinding

import es.uam.eps.dadm.santioscar.renovium.entities.Partida

class PartidaAdapter : ListAdapter<Partida, PartidaAdapter.PartidaViewHolder>(PartidaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidaViewHolder {
        val binding = ItemPartidaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PartidaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartidaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class PartidaViewHolder(private val binding: ItemPartidaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(partida: Partida) {
            binding.partida = partida
            binding.executePendingBindings()
        }
    }

    class PartidaDiffCallback : DiffUtil.ItemCallback<Partida>() {
        override fun areItemsTheSame(oldItem: Partida, newItem: Partida): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Partida, newItem: Partida): Boolean {
            return oldItem == newItem
        }
    }
}