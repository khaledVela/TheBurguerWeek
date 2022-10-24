package com.moviles.practicanavegacioncasera.dal.entities

import android.content.Context
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.moviles.practicanavegacioncasera.repository.HamburguesaRepository
import com.moviles.practicanavegacioncasera.repository.PersonaRepository

@Entity(
    foreignKeys = [ForeignKey(
        entity = Persona::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idPersona"),
        onDelete = CASCADE
    ), ForeignKey(
        entity = Hamburguesa::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idHamburguesa"),
        onDelete = CASCADE
    )]
)
data class Review(
    val calificacion: Int,
    val comentario: String,
    val idPersona: Int,
    val idHamburguesa: Int
) {
    fun getPersona(context: Context): Persona? {
        return PersonaRepository.getPersonaById(idPersona, context)
    }

    fun getHamburguesa(context: Context): Hamburguesa? {
        return HamburguesaRepository.getHamburguesaById(idHamburguesa, context)
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

