package com.moviles.practicanavegacioncasera.repository

import android.content.Context
import com.moviles.practicanavegacioncasera.dal.db.AppDatabase
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.dal.entities.Review

object ReviewRepository {
    fun getAllReviews(context: Context): List<Review> {
        val reviewDao = AppDatabase.getDatabase(context).reviewDao()
        return reviewDao.getAll()
    }

    fun getReviewById(id: Int, context: Context): Review? {
        val reviewDao = AppDatabase.getDatabase(context).reviewDao()
        return reviewDao.getById(id)
    }

    fun insert(review: Review, context: Context) {
        val reviewDao = AppDatabase.getDatabase(context).reviewDao()
        reviewDao.insert(review)
    }

    fun update(review: Review, context: Context) {
        val reviewDao = AppDatabase.getDatabase(context).reviewDao()
        reviewDao.update(review)
    }

    fun delete(review: Review, context: Context) {
        val reviewDao = AppDatabase.getDatabase(context).reviewDao()
        reviewDao.delete(review)
    }

    fun getReviewsByHamburguesaId(id: Int, context: Context): List<Review> {
        val reviewDao = AppDatabase.getDatabase(context).reviewDao()
        return reviewDao.getReviewsByHamburguesaId(id)
    }

    fun getReviewsByPersonaId(idPersona: Int, idHamburguesa: Int, context: Context): List<Review> {
        val reviewDao = AppDatabase.getDatabase(context).reviewDao()
        return reviewDao.getReviewsByPersonaId(idPersona,idHamburguesa)
    }
}