package com.moviles.practicanavegacioncasera.ui.principal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.dal.entities.Review

class ListRestAdapter(
    private val restauranteList: ArrayList<Restaurante>,
    private val listener: OnRestauranteClickListener
): RecyclerView.Adapter<ListRestAdapter.ListRestViewHolder>() {

    class ListRestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.Restaurantename)
        val ver: Button = itemView.findViewById(R.id.verProductos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_restaurante_item_layout, parent, false)
        return ListRestViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListRestViewHolder, position: Int) {
        val restaurante = restauranteList[position]
        holder.tvNombre.text = restaurante.nombre
        holder.ver.setOnClickListener {
            listener.onRestauranteVer(restaurante)
        }

    }

    override fun getItemCount(): Int {
        return restauranteList.size
    }

    interface OnRestauranteClickListener {
        fun onRestauranteVer(restaurante: Restaurante)
    }

}