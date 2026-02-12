package edu.ucne.angel_almonte_ap2_p1.domain.usaCase

import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import javax.inject.Inject

class GetAllCervezaUseCase @Inject constructor(private val repo: CervezaRepository) {
    operator fun invoke() = repo.getAll()
}