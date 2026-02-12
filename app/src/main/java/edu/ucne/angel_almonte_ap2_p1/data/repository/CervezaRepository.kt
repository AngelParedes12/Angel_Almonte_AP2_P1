package edu.ucne.angel_almonte_ap2_p1.data.repository

import androidx.constraintlayout.helper.widget.Flow

interface CervezaRepository {
    fun getAll(query: String?): Flow<List<CervezaRepository>>
    suspend fun save(item: CervezaRepository)
    suspend fun delete(item: CervezaRepository)
    suspend fun find(id: Int): CervezaRepository?
}