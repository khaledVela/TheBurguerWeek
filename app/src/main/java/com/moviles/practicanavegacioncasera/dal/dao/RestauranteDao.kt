package com.moviles.practicanavegacioncasera.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante

@Dao
interface RestauranteDao {
    @Query("SELECT * FROM restaurante")
    fun getAll(): List<Restaurante>

    @Query("SELECT * FROM restaurante WHERE id = :id")
    fun getById(id: Int): Restaurante?

    @Insert
    fun insert(Restaurante: Restaurante)

    @Update
    fun update(Restaurante: Restaurante)

    @Delete
    fun delete(Restaurante: Restaurante)


}