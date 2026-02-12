package edu.ucne.angel_almonte_ap2_p1.domain.usaCase

import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import javax.inject.Inject

class GetCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(id: Int): Cerveza? = repository.find(id)
}