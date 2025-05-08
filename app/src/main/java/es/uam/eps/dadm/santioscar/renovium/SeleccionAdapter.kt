package es.uam.eps.dadm.santioscar.renovium

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adaptador para mostrar una cuadrícula de elementos seleccionables (avatares o ciudades).
 *
 * @param items Lista de IDs de recursos drawable (R.drawable.*) a mostrar
 * @param onItemClick Callback que se ejecuta cuando se selecciona un ítem
 */
class SeleccionAdapter(
    private val items: List<Int>, // Lista de recursos drawable (avatarImages o cityImages)
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<SeleccionAdapter.ItemViewHolder>() {

    /**
     * ViewHolder para cada elemento de la cuadrícula
     */
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.itemImage)

        init {
            view.setOnClickListener { onItemClick(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_elemento, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.image.setImageResource(items[position])
    }

    override fun getItemCount() = items.size
}