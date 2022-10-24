package com.moviles.practicanavegacioncasera.ui.adapters

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

class HamburguesaAdapter(
    val hamburguesaList: ArrayList<Hamburguesa>,
    val listener: OnHamburguesaClickListener
) :
    RecyclerView.Adapter<HamburguesaAdapter.HamburguesaViewHolder>() {
    class HamburguesaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.hamburguesaName)
        val btnEdit: Button = itemView.findViewById(R.id.editarHamburguesa)
        val btnDelete: Button = itemView.findViewById(R.id.eliminaHamburguesa)
        val tvRest: TextView = itemView.findViewById(R.id.RestaurantePertenece)
        val img: ImageView = itemView.findViewById(R.id.imagenHamburguesa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HamburguesaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.hamburguesa_item_layout, parent, false)
        return HamburguesaViewHolder(view)
    }

    override fun onBindViewHolder(holder: HamburguesaViewHolder, position: Int) {
        val hamburguesa = hamburguesaList[position]
        holder.tvNombre.text = hamburguesa.nombre
        holder.tvRest.text = hamburguesa.getRestaurante(holder.tvRest.context)?.nombre
        //coloco imagen
        val imageUri = ImageController.getImage(holder.img.context, hamburguesa.id)
        holder.img.setImageURI(imageUri)

        holder.btnEdit.setOnClickListener {
            listener.onHamburguesaEdit(hamburguesa)
        }
        holder.btnDelete.setOnClickListener {
            listener.onHamburguesaDelete(hamburguesa)
        }
    }

    override fun getItemCount(): Int {
        return hamburguesaList.size
    }

    interface OnHamburguesaClickListener {
        fun onHamburguesaEdit(hamburguesa: Hamburguesa)
        fun onHamburguesaDelete(hamburguesa: Hamburguesa)
    }

    fun refreshData(products: List<Hamburguesa>) {
        hamburguesaList.clear()
        hamburguesaList.addAll(products)
        notifyDataSetChanged()
    }

    fun removeItem(producto: Hamburguesa) {
        val position = hamburguesaList.indexOf(producto)
        hamburguesaList.remove(producto)
        notifyItemRemoved(position)
    }
}

