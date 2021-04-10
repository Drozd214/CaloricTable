package lvivpolytechnic.com.example.calorictable.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase
import lvivpolytechnic.com.example.calorictable.models.Product

@Dao
interface UsersProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: UsersProductDatabase)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addProducts(products: List<UsersProductDatabase>)

    @Query("SELECT * FROM ${UsersProductDatabase.TABLE_NAME} WHERE ${UsersProductDatabase.USER_ID} = :userId")
    fun getProducts(userId: Int): List<UsersProductDatabase>

}