package com.moviles.practicanavegacioncasera.repository

import android.content.Context
import com.moviles.practicanavegacioncasera.dal.db.AppDatabase
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa

object HamburguesaRepository {
    fun getAllHamburguesas(context: Context): List<Hamburguesa> {
        val hamburguesaDao = AppDatabase.getDatabase(context).hamburguesaDao()
        return hamburguesaDao.getAll()
    }

    fun getHamburguesaById(id: Int, context: Context): Hamburguesa? {
        val hamburguesaDao = AppDatabase.getDatabase(context).hamburguesaDao()
        return hamburguesaDao.getById(id)
    }

    fun getHamburguesaByRestaurante(idRestaurante: Int, context: Context): List<Hamburguesa> {
        val hamburguesaDao = AppDatabase.getDatabase(context).hamburguesaDao()
        return hamburguesaDao.getByRestaurante(idRestaurante)
    }

    fun insert(hamburguesa: Hamburguesa, context: Context): List<Int> {
        val hamburguesaDao = AppDatabase.getDatabase(context).hamburguesaDao()
        hamburguesaDao.insert(hamburguesa)
        val listhamburguesa = hamburguesaDao.getAll()
        val listId = ArrayList<Int>()
        for (hamburguesa in listhamburguesa) {
            listId.add(hamburguesa.id)
        }
        return listId
    }

    fun update(hamburguesa: Hamburguesa, context: Context) {
        val hamburguesaDao = AppDatabase.getDatabase(context).hamburguesaDao()
        hamburguesaDao.update(hamburguesa)
    }

    fun delete(hamburguesa: Hamburguesa, context: Context) {
        val hamburguesaDao = AppDatabase.getDatabase(context).hamburguesaDao()
        hamburguesaDao.delete(hamburguesa)
    }
}