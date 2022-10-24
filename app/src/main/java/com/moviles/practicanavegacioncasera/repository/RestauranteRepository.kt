package com.moviles.practicanavegacioncasera.repository

import android.content.Context
import com.moviles.practicanavegacioncasera.dal.db.AppDatabase
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.ui.fragments.RestauranteFragment

object RestauranteRepository {

    fun getAllRestaurantes(context: Context): List<Restaurante> {
        val restauranteDao = AppDatabase.getDatabase(context).restauranteDao()
        return restauranteDao.getAll()
    }

    fun getRestauranteById(id: Int, context: Context): Restaurante? {
        val restauranteDao = AppDatabase.getDatabase(context).restauranteDao()
        return restauranteDao.getById(id)
    }

    fun insert(restaurante: Restaurante, context: Context) {
        val restauranteDao = AppDatabase.getDatabase(context).restauranteDao()
        restauranteDao.insert(restaurante)
    }

    fun update(restaurante: Restaurante, context: Context) {
        val restauranteDao = AppDatabase.getDatabase(context).restauranteDao()
        restauranteDao.update(restaurante)
    }

    fun delete(restaurante: Restaurante, context: Context) {
        val restauranteDao = AppDatabase.getDatabase(context).restauranteDao()
        restauranteDao.delete(restaurante)
    }
}