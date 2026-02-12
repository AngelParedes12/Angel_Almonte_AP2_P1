package edu.ucne.angel_almonte_ap2_p1.domain.usaCase

import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import edu.ucne.angel_almonte_ap2_p1.domain.validation.validatePuntuacion
import edu.ucne.angel_almonte_ap2_p1.domain.validation.validateTexto
import javax.inject.Inject

class UpsertCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza): Result<Unit> {

        val nombreResult = validateTexto(cerveza.nombre)
        if (!nombreResult.isValid) {
            return Result.failure(IllegalArgumentException(nombreResult.error))
        }

        val marcaResult = validateTexto(cerveza.marca)
        if (!marcaResult.isValid) {
            return Result.failure(IllegalArgumentException(marcaResult.error))
        }

        val puntuacionResult = validatePuntuacion(cerveza.puntuacion.toString())
        if (!puntuacionResult.isValid) {
            return Result.failure(IllegalArgumentException(puntuacionResult.error))
        }

        return runCatching { repository.save(cerveza) }
    }
}