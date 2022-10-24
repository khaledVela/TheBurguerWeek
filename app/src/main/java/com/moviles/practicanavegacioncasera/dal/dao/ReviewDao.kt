package com.moviles.practicanavegacioncasera.dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.moviles.practicanavegacioncasera.dal.entities.Review

@Dao
interface ReviewDao {
    @Query("SELECT * FROM review")
    fun getAll(): List<Review>

    @Query("SELECT * FROM review WHERE id = :id")
    fun getById(id: Int): Review?

    @Insert
    fun insert(Review: Review)

    @Update
    fun update(Review: Review)

    @Delete
    fun delete(Review: Review)

    @Query("SELECT * FROM review WHERE idHamburguesa = :idHamburguesa")
    fun getReviewsByHamburguesaId(idHamburguesa: Int): List<Review>

    @Query("SELECT * FROM review WHERE idPersona = :idPersona and idHamburguesa = :idHamburguesa")
    fun getReviewsByPersonaId(idPersona: Int,idHamburguesa: Int): List<Review>
}