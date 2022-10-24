package com.moviles.practicanavegacioncasera.dal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.moviles.practicanavegacioncasera.dal.dao.HamburguesaDao
import com.moviles.practicanavegacioncasera.dal.dao.PersonaDao
import com.moviles.practicanavegacioncasera.dal.dao.RestauranteDao
import com.moviles.practicanavegacioncasera.dal.dao.ReviewDao
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.dal.entities.Persona
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.dal.entities.Review


@Database(entities = [Restaurante::class,Hamburguesa::class,Persona::class,Review::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restauranteDao(): RestauranteDao
    abstract fun hamburguesaDao(): HamburguesaDao
    abstract fun personaDao(): PersonaDao
    abstract fun reviewDao(): ReviewDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "practica_bd_room"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}
