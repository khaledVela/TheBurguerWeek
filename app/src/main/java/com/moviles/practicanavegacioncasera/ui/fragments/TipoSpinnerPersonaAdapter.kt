package com.moviles.practicanavegacioncasera.ui.fragments

import android.content.Context
import android.widget.ArrayAdapter
import com.moviles.practicanavegacioncasera.dal.entities.Persona

class TipoSpinnerPersonaAdapter (
    context: Context,
    var objects: MutableList<Persona>
): ArrayAdapter<Persona>(
    context,
    android.R.layout.simple_spinner_item,
    objects
) {
    fun getIndexForPersona(idPersona: Int): Int {
        val index = objects.indexOfFirst { it.id == idPersona }
        return index
    }
}