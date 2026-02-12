package edu.ucne.angel_almonte_ap2_p1.domain.usaCase

import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCervezasUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    operator fun invoke(query: String? = null): Flow<List<Cerveza>> = repository.getAll(query)
}