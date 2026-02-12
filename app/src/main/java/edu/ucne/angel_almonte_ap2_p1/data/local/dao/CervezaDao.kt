package edu.ucne.angel_almonte_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.angel_almonte_ap2_p1.data.local.entities.CervezaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CervezaDao {
    @Upsert
    suspend fun save(entity: CervezaEntity)

    @Query("DELETE FROM Cervezas WHERE cervezaId = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM Cervezas WHERE cervezaId = :id")
    suspend fun find(id: Int): CervezaEntity?

    @Query("""
        SELECT * FROM Cervezas 
        WHERE (:query IS NULL OR nombre LIKE '%' || :query || '%' OR marca LIKE '%' || :query || '%')
    """)
    fun getAll(query: String?): Flow<List<CervezaEntity>>
}