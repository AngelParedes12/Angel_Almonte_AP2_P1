package edu.ucne.angel_almonte_ap2_p1.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.angel_almonte_ap2_p1.data.local.dao.CervezaDao
import edu.ucne.angel_almonte_ap2_p1.data.local.entities.CervezaEntity


@Database(
    entities = [CervezaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class appDataBase : RoomDatabase() {
    abstract fun CervezaDao(): CervezaDao
}