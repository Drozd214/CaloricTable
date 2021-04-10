package lvivpolytechnic.com.example.calorictable.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lvivpolytechnic.com.example.calorictable.database.models.UserDatabase
import lvivpolytechnic.com.example.calorictable.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserDatabase)

    @Query("SELECT * FROM ${UserDatabase.TABLE_NAME}")
    fun getUser(): List<UserDatabase>

}