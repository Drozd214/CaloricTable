package lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct

import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase
import lvivpolytechnic.com.example.calorictable.models.Product

interface UsersProductRepository {

    suspend fun addProduct(product: Product)

    suspend fun addProducts(products: List<Product>)

    suspend fun getProducts(userId: Int): List<Product>

    suspend fun getProductFromId(id: Int): Product

    suspend fun deleteProductFromId(id: Int)
}