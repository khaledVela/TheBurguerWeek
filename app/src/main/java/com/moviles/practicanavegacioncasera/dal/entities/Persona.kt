package com.moviles.practicanavegacioncasera.dal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Persona(
    val nombre: String,
    val contrasena: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    override fun toString(): String {
        return nombre
    }
}

