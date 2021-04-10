package lvivpolytechnic.com.example.calorictable.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import lvivpolytechnic.com.example.calorictable.database.CaloricTableDatabase.Companion.VERSION
import lvivpolytechnic.com.example.calorictable.database.daos.UserDao
import lvivpolytechnic.com.example.calorictable.database.daos.UsersProductDao
import lvivpolytechnic.com.example.calorictable.database.models.UserDatabase
import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase

@Database(
    entities = [
        UserDatabase::class,
        UsersProductDatabase::class
    ],
    version = VERSION
)
@TypeConverters(Converters::class)
abstract class CaloricTableDatabase : RoomDatabase() {

    companion object {
        const val NAME = "calorictable_database.db"
        const val VERSION = 1
    }

    abstract val userDao: UserDao
    abstract val usersProductDao: UsersProductDao

}