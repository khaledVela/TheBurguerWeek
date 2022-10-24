package com.moviles.practicanavegacioncasera.ui.fragments

import android.content.Context
import android.widget.ArrayAdapter
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa

class TipoSpinnerHamburgueraAdapter (
    context: Context,
    var objects: MutableList<Hamburguesa>
): ArrayAdapter<Hamburguesa>(
    context,
    android.R.layout.simple_spinner_item,
    objects
) {
    fun getIndexForHamburguera(idHamburguera: Int): Int {
        val index = objects.indexOfFirst { it.id == idHamburguera }
        return index
    }
}