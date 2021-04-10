package lvivpolytechnic.com.example.calorictable.data.repositories.user

import lvivpolytechnic.com.example.calorictable.models.User

interface UserRepository {

    suspend fun saveUser(user: User)

    suspend fun getUser(): User?

}