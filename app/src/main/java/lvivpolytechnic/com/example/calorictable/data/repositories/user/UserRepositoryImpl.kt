package lvivpolytechnic.com.example.calorictable.data.repositories.user

import lvivpolytechnic.com.example.calorictable.data.stores.user.UserDatabaseStore
import lvivpolytechnic.com.example.calorictable.models.User

class UserRepositoryImpl(
    private val userDatabaseStore: UserDatabaseStore
): UserRepository {

    override suspend fun saveUser(user: User) {
        userDatabaseStore.saveUser(user)
    }

    override suspend fun getUser(): User? {
        return userDatabaseStore.getUser()
    }

}