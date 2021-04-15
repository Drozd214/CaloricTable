package lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct

import lvivpolytechnic.com.example.calorictable.data.stores.usersproduct.UsersProductDatabaseStore
import lvivpolytechnic.com.example.calorictable.data.stores.usersproduct.UsersProductDatabaseStoreImpl
import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase
import lvivpolytechnic.com.example.calorictable.models.Product

class UsersProductRepositoryImpl(
        private val usersProductStore: UsersProductDatabaseStore
): UsersProductRepository {

    override suspend fun addProduct(product: Product) {
        val newProduct = UsersProductDatabase(
                id = if(product.id != 0) product.id else 0,
                userId = product.userId,
                name = product.name,
                imageUrl = product.imageUrl,
                capacityInGrams = product.capacity,
                calories = product.calories,
                carbohydrates = product.carbohydrates,
                protein = product.protein,
                fat = product.fat,
                eatingTime = product.eatingTime
        )
        usersProductStore.addProduct(newProduct)
    }

    override suspend fun addProducts(products: List<Product>) {
        val newProducts = mutableListOf<UsersProductDatabase>()
        for(product in products) {
            newProducts.add(UsersProductDatabase(
                    id = if(product.id != 0) product.id else 0,
                    userId = product.userId,
                    name = product.name,
                    imageUrl = product.imageUrl,
                    capacityInGrams = product.capacity,
                    calories = product.calories,
                    carbohydrates = product.carbohydrates,
                    protein = product.protein,
                    fat = product.fat,
                    eatingTime = product.eatingTime
            ))
        }
        usersProductStore.addProducts(newProducts)
    }

    override suspend fun getProducts(userId: Int): List<Product> {
        val products = usersProductStore.getProducts(userId)
        val gettingProducts = mutableListOf<Product>()
        for (product in products) {
            gettingProducts.add(Product(
                    id = product.id,
                    userId = product.userId,
                    name = product.name,
                    imageUrl = product.imageUrl,
                    capacity = product.capacityInGrams,
                    calories = product.calories,
                    carbohydrates = product.carbohydrates,
                    protein = product.protein,
                    fat = product.fat,
                    eatingTime = product.eatingTime
            ))
        }
        return gettingProducts
    }

    override suspend fun getProductFromId(id: Int): Product {
        val products =  usersProductStore.getProductFromId(id)
        return Product(
            id = products[0].id,
            userId = products[0].userId,
            name = products[0].name,
            imageUrl = products[0].imageUrl,
            capacity = products[0].capacityInGrams,
            calories = products[0].calories,
            carbohydrates = products[0].carbohydrates,
            protein = products[0].protein,
            fat = products[0].fat,
            eatingTime = products[0].eatingTime
        )
    }

    override suspend fun deleteProductFromId(id: Int) {
        usersProductStore.deleteProductFromId(id)
    }

}