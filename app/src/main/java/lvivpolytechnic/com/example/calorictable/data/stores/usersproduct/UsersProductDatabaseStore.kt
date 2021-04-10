package lvivpolytechnic.com.example.calorictable.data.stores.usersproduct

import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase

interface UsersProductDatabaseStore {

    suspend fun addProduct(product: UsersProductDatabase)

    suspend fun addProducts(products: List<UsersProductDatabase>)

    suspend fun getProducts(userId: Int): List<UsersProductDatabase>
}