package lvivpolytechnic.com.example.calorictable.di.modules.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import dagger.Module
import dagger.Provides
import lvivpolytechnic.com.example.calorictable.database.CaloricTableDatabase
import javax.inject.Singleton

@Module(
    includes = [
        DaosModule::class
    ]
)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        context: Context
    ): CaloricTableDatabase {
        return Room.databaseBuilder(
            context,
            CaloricTableDatabase::class.java,
            CaloricTableDatabase.NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

}