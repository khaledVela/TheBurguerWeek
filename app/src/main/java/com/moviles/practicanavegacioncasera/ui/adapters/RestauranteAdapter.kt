package com.moviles.practicanavegacioncasera.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante

class RestauranteAdapter(
    private val restauranteList: ArrayList<Restaurante>,
    private val listener: OnRestauranteClickListener
) : RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>() {

    class RestauranteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.restauranteName)
        val btnEdit: Button = itemView.findViewById(R.id.editarRestaurante)
        val btnDelete: Button = itemView.findViewById(R.id.eliminaRestaurante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.restaurante_item_layout, parent, false)
        return RestauranteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val restaurante = restauranteList[position]
        holder.tvNombre.text = restaurante.nombre
        holder.btnEdit.setOnClickListener {
            listener.onRestauranteEdit(restaurante)
        }
        holder.btnDelete.setOnClickListener {
            listener.onRestauranteDelete(restaurante)
        }

    }

    override fun getItemCount(): Int {
        return restauranteList.size
    }

    interface OnRestauranteClickListener {
        fun onRestauranteEdit(restaurante: Restaurante)
        fun onRestauranteDelete(restaurante: Restaurante)
    }

    fun refreshData(products: List<Restaurante>) {
        restauranteList.clear()
        restauranteList.addAll(products)
        notifyDataSetChanged()
    }

    fun removeItem(producto: Restaurante) {
        val position = restauranteList.indexOf(producto)
        restauranteList.remove(producto)
        notifyItemRemoved(position)
    }
}
