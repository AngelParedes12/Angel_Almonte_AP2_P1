package edu.ucne.angel_almonte_ap2_p1.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.angel_almonte_ap2_p1.data.local.entities.BorrameEntity

@Database(
    entities = [BorrameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class appDataBase: RoomDatabase() {
}