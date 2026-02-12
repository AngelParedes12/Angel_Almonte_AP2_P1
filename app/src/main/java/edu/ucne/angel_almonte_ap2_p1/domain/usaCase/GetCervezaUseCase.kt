package edu.ucne.angel_almonte_ap2_p1.domain.usaCase

import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import javax.inject.Inject

class GetCervezaUseCase @Inject constructor(private val repo: CervezaRepository) {
    suspend operator fun invoke(id: Int) = repo.find(id)
}