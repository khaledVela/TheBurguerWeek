package com.moviles.practicanavegacioncasera.ui.principal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.repository.ImageController

class ListHambAdapter(
    val hamburguesaList: ArrayList<Hamburguesa>,
    val listener: OnHamburguesaClickListener
): RecyclerView.Adapter<ListHambAdapter.HamburguesaViewHolder>() {
    class HamburguesaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.nombreProducto)
        val btnver: Button = itemView.findViewById(R.id.verInfo)
        val img: ImageView = itemView.findViewById(R.id.imagenProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HamburguesaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.hamb_list_adapter, parent, false)
        return HamburguesaViewHolder(view)
    }

    override fun onBindViewHolder(holder: HamburguesaViewHolder, position: Int) {
        val hamburguesa = hamburguesaList[position]
        holder.tvNombre.text = hamburguesa.nombre
        //coloco imagen
        val imageUri = ImageController.getImage(holder.img.context, hamburguesa.id)
        holder.img.setImageURI(imageUri)

        holder.btnver.setOnClickListener {
            listener.onHamburguesaVer(hamburguesa)
        }
    }

    override fun getItemCount(): Int {
        return hamburguesaList.size
    }

    interface OnHamburguesaClickListener {
        fun onHamburguesaVer(hamburguesa: Hamburguesa)
    }

}