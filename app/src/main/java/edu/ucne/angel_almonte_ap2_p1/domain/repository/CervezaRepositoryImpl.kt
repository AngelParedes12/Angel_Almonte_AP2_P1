package edu.ucne.angel_almonte_ap2_p1.domain.repository

import edu.ucne.angel_almonte_ap2_p1.data.local.dao.CervezaDao
import edu.ucne.angel_almonte_ap2_p1.data.local.mapper.toDomain
import edu.ucne.angel_almonte_ap2_p1.data.local.mapper.toEntity
import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CervezaRepositoryImpl @Inject constructor(
    private val dao: CervezaDao
) : CervezaRepository {
    override fun getAll(query: String?): Flow<List<Cerveza>> {
        return dao.getAll(query).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun save(cerveza: Cerveza) {
        dao.save(cerveza.toEntity())
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun find(id: Int): Cerveza? {
        return dao.find(id)?.toDomain()
    }
}