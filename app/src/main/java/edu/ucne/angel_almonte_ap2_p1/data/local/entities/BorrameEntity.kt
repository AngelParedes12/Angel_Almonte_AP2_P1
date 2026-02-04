package edu.ucne.angel_almonte_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Borrame")
data class BorrameEntity(
    @PrimaryKey(autoGenerate = true)
    val borraId: Int = 0
)