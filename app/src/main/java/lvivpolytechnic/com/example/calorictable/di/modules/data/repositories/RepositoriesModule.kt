package lvivpolytechnic.com.example.calorictable.di.modules.data.repositories

import dagger.Module
import dagger.Provides
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepositoryImpl
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepositoryImpl
import lvivpolytechnic.com.example.calorictable.data.stores.user.UserDatabaseStore
import lvivpolytechnic.com.example.calorictable.data.stores.usersproduct.UsersProductDatabaseStore
import javax.inject.Singleton

@Module
object RepositoriesModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDatabaseStore: UserDatabaseStore
    ): UserRepository {
        return UserRepositoryImpl(userDatabaseStore)
    }

    @Singleton
    @Provides
    fun provideUsersProductRepository(
            usersProductDatabaseStore: UsersProductDatabaseStore
    ): UsersProductRepository {
        return UsersProductRepositoryImpl(usersProductDatabaseStore)
    }

}