package edu.ucne.angel_almonte_ap2_p1.domain.usaCase

import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import javax.inject.Inject

class DeleteCervezaUseCase @Inject constructor(private val repo: CervezaRepository) {
    suspend operator fun invoke(item: Cerveza) = repo.delete(item)
}