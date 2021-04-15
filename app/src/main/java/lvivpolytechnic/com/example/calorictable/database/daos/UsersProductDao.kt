package lvivpolytechnic.com.example.calorictable.database.daos

import androidx.room.*
import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase
import lvivpolytechnic.com.example.calorictable.models.Product
import retrofit2.http.DELETE

@Dao
interface UsersProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: UsersProductDatabase)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addProducts(products: List<UsersProductDatabase>)

    @Query("SELECT * FROM ${UsersProductDatabase.TABLE_NAME} WHERE ${UsersProductDatabase.USER_ID} = :userId")
    fun getProducts(userId: Int): List<UsersProductDatabase>

    @Query("SELECT * FROM ${UsersProductDatabase.TABLE_NAME} WHERE ${UsersProductDatabase.ID} = :id")
    fun getProductFromId(id: Int): List<UsersProductDatabase>

    @Query("DELETE FROM ${UsersProductDatabase.TABLE_NAME} WHERE ${UsersProductDatabase.ID} = :id")
    fun deleteProductFromId(id: Int)
}