package com.moviles.practicanavegacioncasera.ui.principal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Review
import com.moviles.practicanavegacioncasera.ui.adapters.ReviewAdapter

class ListReseñaAdapter(
    private val reviewList: ArrayList<Review>,
) : RecyclerView.Adapter<ListReseñaAdapter.ListReseñaViewHolder>(){
    class ListReseñaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPersona: TextView = itemView.findViewById(R.id.personaReseñaname)
        val tvPuntuacion: TextView = itemView.findViewById(R.id.puntuacionReseña)
        val tvComentario: TextView = itemView.findViewById(R.id.descripcionReseña)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListReseñaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_resena_item_layout, parent, false)
        return ListReseñaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListReseñaViewHolder, position: Int) {
        val review = reviewList[position]
        holder.tvPersona.text = review.getPersona(holder.tvPersona.context)?.nombre
        holder.tvPuntuacion.text = review.calificacion.toString()+"/5"
        holder.tvComentario.text = review.comentario
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

}

