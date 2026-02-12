package edu.ucne.angel_almonte_ap2_p1.domain.repository

import edu.ucne.angel_almonte_ap2_p1.data.local.dao.CervezaDao
import edu.ucne.angel_almonte_ap2_p1.data.local.entities.CervezaEntity
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import edu.ucne.angel_almonte_ap2_p1.data.repository.CervezaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CervezaRepositoryImpl @Inject constructor(
    private val dao: CervezaDao
) : CervezaRepository {
    override fun getAll(query: String?): Flow<List<Cerveza>> {
        return dao.getAll(query).map { list ->
            list.map { entity ->
                Cerveza(entity.cervezaId, entity.nombre, entity.marca, entity.puntuacion)
            }
        }
    }

    override suspend fun save(cerveza: Cerveza) {
        dao.save(
            CervezaEntity(cerveza.cervezaId, cerveza.nombre, cerveza.marca, cerveza.puntuacion)
        )
    }

    override suspend fun delete(cerveza: Cerveza) {
        dao.delete(
            CervezaEntity(cerveza.cervezaId, cerveza.nombre, cerveza.marca, cerveza.puntuacion)
        )
    }

    override suspend fun find(id: Int): Cerveza? {
        return dao.find(id)?.let { entity ->
            Cerveza(entity.cervezaId, entity.nombre, entity.marca, entity.puntuacion)
        }
    }
}