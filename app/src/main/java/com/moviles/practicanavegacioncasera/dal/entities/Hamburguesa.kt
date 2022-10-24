package com.moviles.practicanavegacioncasera.dal.entities

import android.content.Context
import android.net.Uri
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.moviles.practicanavegacioncasera.repository.RestauranteRepository

@Entity(
    foreignKeys = [ForeignKey(
        entity = Restaurante::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idRestaurante"),
        onDelete = CASCADE
    )]
)
data class Hamburguesa(
    val nombre: String,
    val descripcion: String,
    val idRestaurante: Int
) {
    fun getRestaurante(context: Context): Restaurante? {
        return RestauranteRepository.getRestauranteById(idRestaurante, context)
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return nombre
    }
}

