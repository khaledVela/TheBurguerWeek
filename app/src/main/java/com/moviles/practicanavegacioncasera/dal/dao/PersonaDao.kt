package com.moviles.practicanavegacioncasera.dal.dao

import androidx.room.*
import com.moviles.practicanavegacioncasera.dal.entities.Persona

@Dao
interface PersonaDao {
    @Query("SELECT * FROM persona")
    fun getAll(): List<Persona>

    @Query("SELECT * FROM persona WHERE id = :id")
    fun getById(id: Int): Persona?

    @Insert
    fun insert(Persona: Persona)

    @Update
    fun update(Persona: Persona)

    @Delete
    fun delete(Persona: Persona)

}