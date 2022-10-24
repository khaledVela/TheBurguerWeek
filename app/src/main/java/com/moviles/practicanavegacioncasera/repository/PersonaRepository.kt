package com.moviles.practicanavegacioncasera.repository

import android.content.Context
import com.moviles.practicanavegacioncasera.dal.db.AppDatabase
import com.moviles.practicanavegacioncasera.dal.entities.Persona

object PersonaRepository {
    fun getAllPersonas(context: Context): List<Persona> {
        val personaDao = AppDatabase.getDatabase(context).personaDao()
        return personaDao.getAll()
    }

    fun getPersonaById(id: Int, context: Context): Persona? {
        val personaDao = AppDatabase.getDatabase(context).personaDao()
        return personaDao.getById(id)
    }

    fun insert(persona: Persona, context: Context) {
        val personaDao = AppDatabase.getDatabase(context).personaDao()
        personaDao.insert(persona)
    }

    fun update(persona: Persona, context: Context) {
        val personaDao = AppDatabase.getDatabase(context).personaDao()
        personaDao.update(persona)
    }

    fun delete(persona: Persona, context: Context) {
        val personaDao = AppDatabase.getDatabase(context).personaDao()
        personaDao.delete(persona)
    }
}