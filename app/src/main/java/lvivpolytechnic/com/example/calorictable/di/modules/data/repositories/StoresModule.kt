package lvivpolytechnic.com.example.calorictable.di.modules.data.repositories

import dagger.Module
import dagger.Provides
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfoAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearch
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearchAPI
import lvivpolytechnic.com.example.calorictable.data.stores.ingridient.IngredientApiStore
import lvivpolytechnic.com.example.calorictable.data.stores.ingridient.IngredientApiStoreImpl
import lvivpolytechnic.com.example.calorictable.data.stores.user.UserDatabaseStore
import lvivpolytechnic.com.example.calorictable.data.stores.user.UserDatabaseStoreImpl
import lvivpolytechnic.com.example.calorictable.data.stores.usersproduct.UsersProductDatabaseStore
import lvivpolytechnic.com.example.calorictable.data.stores.usersproduct.UsersProductDatabaseStoreImpl
import lvivpolytechnic.com.example.calorictable.database.daos.UserDao
import lvivpolytechnic.com.example.calorictable.database.daos.UsersProductDao
import javax.inject.Singleton

@Module
object StoresModule {

    @Provides
    fun provideUserDatabaseStore(
        userDao: UserDao
    ): UserDatabaseStore {
        return UserDatabaseStoreImpl(userDao)
    }

    @Provides
    fun providesUsersProductsDatabaseStore(
            usersProductDao: UsersProductDao
    ): UsersProductDatabaseStore {
        return UsersProductDatabaseStoreImpl(usersProductDao)
    }

    @Provides
    fun provideIngredientApiStore(
        ingredientSearchAPI: IngredientSearchAPI,
        ingredientInfoAPI: IngredientInfoAPI
    ): IngredientApiStore {
        return IngredientApiStoreImpl(ingredientSearchAPI, ingredientInfoAPI)
    }

}