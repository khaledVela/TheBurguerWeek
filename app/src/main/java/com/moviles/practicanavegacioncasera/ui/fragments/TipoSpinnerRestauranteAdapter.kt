package com.moviles.practicanavegacioncasera.ui.fragments

import android.content.Context
import android.widget.ArrayAdapter
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante

class TipoSpinnerRestauranteAdapter (
    context: Context,
    var objects: MutableList<Restaurante>
): ArrayAdapter<Restaurante>(
    context,
    android.R.layout.simple_spinner_item,
    objects
) {
    fun getIndexForRestaurante(idRestaurante: Int): Int {
        val index = objects.indexOfFirst { it.id == idRestaurante }
        return index
    }
}