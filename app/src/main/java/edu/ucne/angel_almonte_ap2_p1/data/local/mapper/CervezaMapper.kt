package edu.ucne.angel_almonte_ap2_p1.data.local.mapper

import edu.ucne.angel_almonte_ap2_p1.data.local.entities.CervezaEntity
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza

fun CervezaEntity.toDomain() = Cerveza(
    cervezaId = cervezaId,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)

fun Cerveza.toEntity() = CervezaEntity(
    cervezaId = cervezaId,
    nombre = nombre,
    marca = marca,
    puntuacion = puntuacion
)