package lvivpolytechnic.com.example.calorictable.data.stores.user

import lvivpolytechnic.com.example.calorictable.database.daos.UserDao
import lvivpolytechnic.com.example.calorictable.database.models.UserDatabase
import lvivpolytechnic.com.example.calorictable.models.User

class UserDatabaseStoreImpl(
    private val userDao: UserDao
): UserDatabaseStore {

    override suspend fun saveUser(user: User) {
        userDao.saveUser(UserDatabase(
                id = user.id,
                profileImage = user.profileImage,
                sex = user.sex,
                height = user.height,
                weight = user.weight,
                yearOfBirth = user.yearOfBirth,
                goal = user.goal
        ))
    }

    override suspend fun getUser(): User? {
        val databaseUser = userDao.getUser().firstOrNull()
        return if(databaseUser != null) {
            User(
                    id = databaseUser.id,
                    profileImage = databaseUser.profileImage,
                    sex = databaseUser.sex,
                    height = databaseUser.height,
                    weight = databaseUser.weight,
                    yearOfBirth = databaseUser.yearOfBirth,
                    goal = databaseUser.goal,
                    productsList = listOf()
            )
        } else null
    }

}