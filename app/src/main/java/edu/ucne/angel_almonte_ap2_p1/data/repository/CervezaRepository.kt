package edu.ucne.angel_almonte_ap2_p1.data.repository

import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import kotlinx.coroutines.flow.Flow

interface CervezaRepository {
    fun getAll(query: String?): Flow<List<Cerveza>>
    suspend fun save(cerveza: Cerveza)
    suspend fun deleteById(id: Int)
    suspend fun find(id: Int): Cerveza?
}