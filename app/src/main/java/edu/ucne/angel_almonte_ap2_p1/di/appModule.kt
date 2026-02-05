package edu.ucne.angel_almonte_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.angel_almonte_ap2_p1.data.local.dataBase.appDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {

@Provides
@Singleton
fun  provideExamenDatabase(@ApplicationContext appContext: Context): appDataBase {
    return Room.databaseBuilder(
        appContext,
        appDataBase::class.java,
        "examen.db"
    ).fallbackToDestructiveMigration().build()

}
    @Provides
    fun provideBorraDao(appDataBase: appDataBase) = appDataBase.BorrarDao()

    @Provides
    @Singleton
    fun provideBorraRepository(borraDao: BorrarDao) = BorraRepository(borraDao)


}