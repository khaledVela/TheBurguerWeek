package com.moviles.practicanavegacioncasera.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Persona

class PersonaAdapter (
    private val personaList: ArrayList<Persona>,
    private val listener: OnPersonaClickListener
) : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.PersonaName)
        val tvContra: TextView = itemView.findViewById(R.id.PersonaContra)
        val btnEdit: Button = itemView.findViewById(R.id.editarPersona)
        val btnDelete: Button = itemView.findViewById(R.id.eliminarPersona)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.persona_item_layout, parent, false)
        return PersonaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personaList[position]
        holder.tvNombre.text = persona.nombre
        holder.tvContra.text = persona.contrasena
        holder.btnEdit.setOnClickListener {
            listener.onPersonaEdit(persona)
        }
        holder.btnDelete.setOnClickListener {
            listener.onPersonaDelete(persona)
        }
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    interface OnPersonaClickListener {
        fun onPersonaEdit(persona: Persona)
        fun onPersonaDelete(persona: Persona)
    }

    fun refreshData(personas: List<Persona>) {
        personaList.clear()
        personaList.addAll(personas)
        notifyDataSetChanged()
    }

    fun removeItem(persona: Persona) {
        val position = personaList.indexOf(persona)
        personaList.remove(persona)
        notifyItemRemoved(position)
    }
}