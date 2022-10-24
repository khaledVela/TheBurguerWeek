package com.moviles.practicanavegacioncasera.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa

@Dao
interface HamburguesaDao {
    @Query("SELECT * FROM hamburguesa")
    fun getAll(): List<Hamburguesa>

    @Query("SELECT * FROM hamburguesa WHERE id = :id")
    fun getById(id: Int): Hamburguesa?

    @Insert
    fun insert(Hamburguesa: Hamburguesa)

    @Update
    fun update(Hamburguesa: Hamburguesa)

    @Delete
    fun delete(Hamburguesa: Hamburguesa)

    @Query("SELECT * FROM hamburguesa WHERE idRestaurante = :idRestaurante")
    fun getByRestaurante(idRestaurante: Int): List<Hamburguesa>
}