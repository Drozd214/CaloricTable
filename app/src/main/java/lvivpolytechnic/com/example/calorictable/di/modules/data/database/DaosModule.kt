package lvivpolytechnic.com.example.calorictable.di.modules.data.database

import dagger.Module
import dagger.Provides
import lvivpolytechnic.com.example.calorictable.database.CaloricTableDatabase
import lvivpolytechnic.com.example.calorictable.database.daos.UserDao
import lvivpolytechnic.com.example.calorictable.database.daos.UsersProductDao
import javax.inject.Singleton

@Module
object DaosModule {

    @Singleton
    @Provides
    fun provideUserDao(roomDatabase: CaloricTableDatabase): UserDao {
        return roomDatabase.userDao
    }

    @Singleton
    @Provides
    fun provideUsersProductDao(roomDatabase: CaloricTableDatabase): UsersProductDao {
        return roomDatabase.usersProductDao
    }

}