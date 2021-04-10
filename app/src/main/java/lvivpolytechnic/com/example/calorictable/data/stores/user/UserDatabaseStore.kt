package lvivpolytechnic.com.example.calorictable.data.stores.user

import lvivpolytechnic.com.example.calorictable.models.User

interface UserDatabaseStore {

    suspend fun saveUser(user: User)

    suspend fun getUser(): User?
}