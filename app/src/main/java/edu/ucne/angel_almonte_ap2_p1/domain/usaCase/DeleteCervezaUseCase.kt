package edu.ucne.angel_almonte_ap2_p1.domain.usaCase

import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import javax.inject.Inject

class DeleteCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteById(id)
}