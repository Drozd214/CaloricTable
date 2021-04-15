package lvivpolytechnic.com.example.calorictable.data.stores.usersproduct

import lvivpolytechnic.com.example.calorictable.database.daos.UsersProductDao
import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase

class UsersProductDatabaseStoreImpl(
        private val usersProductDao: UsersProductDao
) : UsersProductDatabaseStore {

    override suspend fun addProduct(product: UsersProductDatabase) {
        usersProductDao.addProduct(product)
    }

    override suspend fun addProducts(products: List<UsersProductDatabase>) {
        usersProductDao.addProducts(products)
    }

    override suspend fun getProducts(userId: Int): List<UsersProductDatabase> {
        return usersProductDao.getProducts(userId)
    }

    override suspend fun getProductFromId(id: Int): List<UsersProductDatabase> {
        return usersProductDao.getProductFromId(id)
    }

    override suspend fun deleteProductFromId(id: Int) {
        usersProductDao.deleteProductFromId(id)
    }
}